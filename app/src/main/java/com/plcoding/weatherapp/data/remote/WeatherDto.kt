package com.plcoding.weatherapp.data.remote

import com.squareup.moshi.Json

data class WeatherDto(
    @field: Json(name="hourly")
    val hourlyData: HourlyDataDto,
    @field: Json(name="daily")
    val dailyData: DailyDataDto
)