package com.oganbelema.forecastmvvm.data.provider

import com.oganbelema.forecastmvvm.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem(): UnitSystem
}