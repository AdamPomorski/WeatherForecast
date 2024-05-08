package com.plcoding.weatherapp.presentation

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.plcoding.weatherapp.domain.repository.WeatherRepository
import com.plcoding.weatherapp.presentation.ui.theme.DarkBlue
import com.plcoding.weatherapp.presentation.ui.theme.DeepBlue
import com.plcoding.weatherapp.presentation.ui.theme.LightBlue
import com.plcoding.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
        }

        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        setContent {
            WeatherAppTheme {
                val color = remember {
                    mutableStateOf(DarkBlue)
                }

                Box(modifier = Modifier.fillMaxSize().background(color.value)) {
                    WeatherScreen(viewModel = viewModel, color)
                }
            }
        }
    }
}
@Composable
fun WeatherScreen(viewModel: WeatherViewModel, color: MutableState<Color>) {



    val isRefreshing by viewModel.isRefreshing.collectAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = {
            viewModel.loadWeatherInfo()
        }
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color.value)
                    ) {
                        WeatherCard(state = viewModel.state, backgroundColor = DeepBlue)

                        Spacer(modifier = Modifier.height(16.dp))

                        WeatherForecastHourly(state = viewModel.state) {
                            color.value = it
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        WeatherForecastDaily(state = viewModel.state)

                        if (viewModel.state.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                        viewModel.state.error?.let { error ->
                            Text(
                                text = error,
                                color = Color.Red,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }

            }
        }
    }
}
