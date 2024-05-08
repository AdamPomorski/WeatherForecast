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
import com.plcoding.weatherapp.domain.weather.DailyWeatherData
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.random.Random

@Composable
fun WeatherForecastDaily(
    state: WeatherState,
    modifier: Modifier = Modifier
) {


    state.weatherInfo?.weatherDailyData?.let { data ->
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "7 days",
                fontSize = 20.sp,
                color = Color.White

            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(content = {
                val now = LocalDate.now()
                val filteredData: List<DailyWeatherData> = data.flatMap { it.value } // Flatten the map values into a single list
                    .filter { it.day.isAfter(now) }
                items(filteredData) { weatherDailyData ->

                    DailyWeatherDisplay(
                        weatherData = weatherDailyData,
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