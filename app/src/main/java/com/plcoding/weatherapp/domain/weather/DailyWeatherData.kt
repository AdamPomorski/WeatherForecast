package com.plcoding.weatherapp.domain.weather

import java.time.LocalDate

data class DailyWeatherData(
    val day: LocalDate,
    val tempMax: Double,
    val tempMin: Double,
    val weatherType: WeatherType
)
