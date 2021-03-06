package com.oganbelema.forecastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.oganbelema.forecastmvvm.data.database.unitlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
}