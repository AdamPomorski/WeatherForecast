package com.plcoding.weatherapp.data.remote

import com.squareup.moshi.Json

data class DailyDataDto(
    @field: Json(name = "time")
    val days: List<String>,
    @field: Json(name = "temperature_2m_max")
    val temperaturesMax: List<Double>,
    @field: Json(name = "temperature_2m_min")
    val temperaturesMin: List<Double>,
    @field: Json(name = "weathercode")
    val weatherCodes: List<Int>,

)
