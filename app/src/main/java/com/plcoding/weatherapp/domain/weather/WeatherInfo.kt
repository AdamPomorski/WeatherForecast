package com.plcoding.weatherapp.domain.weather

data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<HourlyWeatherData>>,
    val currentWeatherData: HourlyWeatherData?,
    val weatherDailyData: Map<Int, List<DailyWeatherData>>
)
