package com.oganbelema.forecastmvvm.data.network.response

import com.google.gson.annotations.SerializedName
import com.oganbelema.forecastmvvm.data.database.entity.CurrentWeatherEntry
import com.oganbelema.forecastmvvm.data.database.entity.Location

data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location
)