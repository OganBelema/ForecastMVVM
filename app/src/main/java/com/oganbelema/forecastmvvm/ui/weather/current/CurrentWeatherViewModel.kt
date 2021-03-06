package com.oganbelema.forecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel
import com.oganbelema.forecastmvvm.data.provider.UnitProvider
import com.oganbelema.forecastmvvm.data.repository.ForecastRepository
import com.oganbelema.forecastmvvm.internal.UnitSystem
import com.oganbelema.forecastmvvm.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {
    private val unitSystem = unitProvider.getUnitSystem()

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }
}
