package com.plcoding.weatherapp.domain.weather

import java.time.LocalDateTime

data class HourlyWeatherData(
    val time: LocalDateTime,
    val temperatureC: Double,
    val pressurehPa: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType
)
