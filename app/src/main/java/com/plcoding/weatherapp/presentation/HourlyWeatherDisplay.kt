package com.plcoding.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.plcoding.weatherapp.domain.weather.HourlyWeatherData
import com.plcoding.weatherapp.presentation.ui.theme.LightBlue
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun HourlyWeatherDisplay(
    weatherData: HourlyWeatherData,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,

    ) {
    val formattedTime = remember(weatherData) {
        weatherData.time.format(DateTimeFormatter.ofPattern("HH:mm"))
    }


        Card(
            backgroundColor = LightBlue,
            shape = RoundedCornerShape(15.dp)
        ) {

            Column(
                modifier = modifier.padding(vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = formattedTime,
                    color = Color.LightGray
                )
                Image(
                    painter = painterResource(id = weatherData.weatherType.iconRes),
                    contentDescription = null,
                    modifier = Modifier.width(40.dp)
                )
                Text(
                    text = "${weatherData.temperatureC.roundToInt()}°C",
                    color = textColor,
                    fontWeight = FontWeight.Bold
                )

            }
        }

}