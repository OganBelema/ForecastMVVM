package com.oganbelema.forecastmvvm.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oganbelema.forecastmvvm.data.database.entity.CURRENT_WEATHER_ID
import com.oganbelema.forecastmvvm.data.database.entity.CurrentWeatherEntry
import com.oganbelema.forecastmvvm.data.database.unitlocalized.ImperialCurrentWeatherEntry
import com.oganbelema.forecastmvvm.data.database.unitlocalized.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntry)

    @Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry>

    @Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEntry>
}