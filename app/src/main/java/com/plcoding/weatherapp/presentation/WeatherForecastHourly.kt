package com.plcoding.weatherapp.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDateTime
import kotlin.random.Random


@Composable
fun WeatherForecastHourly(
    state: WeatherState,
    modifier: Modifier = Modifier,
    updateColor: (Color) -> Unit
) {


    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Today",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier
                    .clickable {
                        updateColor(
                            Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(),1f)
                        )

                    }

            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(content = {
                val now = LocalDateTime.now()
                val futureData = data.filter { it.time.isAfter(now) }
                items(futureData) { weatherData ->

                    HourlyWeatherDisplay(
                        weatherData = weatherData,
                        modifier = Modifier
                            .height(100.dp)
                            .padding(horizontal = 16.dp,)
                    )

                }
            },
                contentPadding = PaddingValues(horizontal = 0.dp, vertical = 0.dp),
                // Add horizontal padding between items
                horizontalArrangement = Arrangement.spacedBy(8.dp)

            )

        }
    }


}