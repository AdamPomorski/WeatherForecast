package com.plcoding.weatherapp.data.mappers

import com.plcoding.weatherapp.data.remote.DailyDataDto
import com.plcoding.weatherapp.data.remote.HourlyDataDto
import com.plcoding.weatherapp.data.remote.WeatherDto
import com.plcoding.weatherapp.domain.weather.DailyWeatherData
import com.plcoding.weatherapp.domain.weather.HourlyWeatherData
import com.plcoding.weatherapp.domain.weather.WeatherInfo
import com.plcoding.weatherapp.domain.weather.WeatherType
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


private data class IndexedHourlyWeatherData(
    val index: Int,
    val data: HourlyWeatherData
)
private data class IndexedDailyWeatherData(
    val index: Int,
    val data: DailyWeatherData
)

fun HourlyDataDto.toWeatherDataMap(): Map<Int, List<HourlyWeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedHourlyWeatherData(
            index = index,
            data = HourlyWeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureC = temperature,
                pressurehPa = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }

}


fun DailyDataDto.toWeatherDataMap(): Map<Int, List<DailyWeatherData>>{

    return days.mapIndexed{index, days ->
        val tempMax = temperaturesMax[index]
        val tempMin = temperaturesMin[index]
        val weatherCode = weatherCodes[index]
        IndexedDailyWeatherData(
            index = index,
            data = DailyWeatherData(
                day = LocalDate.parse(days),
                tempMax = tempMax,
                tempMin = tempMin,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy{
        it.index
    }.mapValues {
        it.value.map{it.data}
    }

    }


fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherHourlyDataMap = hourlyData.toWeatherDataMap()
    val weatherDailyDataMap = dailyData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherHourlyDataMap[0]?.find {
        val hour = if(now.minute<30) now.hour else now.hour+1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherHourlyDataMap,
        currentWeatherData = currentWeatherData,
        weatherDailyData = weatherDailyDataMap
    )

}