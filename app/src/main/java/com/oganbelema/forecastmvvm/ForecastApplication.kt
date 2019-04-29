package com.oganbelema.forecastmvvm

import android.app.Application
import android.preference.PreferenceManager
import com.jakewharton.threetenabp.AndroidThreeTen
import com.oganbelema.forecastmvvm.data.database.ForecastDatabase
import com.oganbelema.forecastmvvm.data.network.*
import com.oganbelema.forecastmvvm.data.provider.UnitProvider
import com.oganbelema.forecastmvvm.data.provider.UnitProviderImpl
import com.oganbelema.forecastmvvm.data.repository.ForecastRepository
import com.oganbelema.forecastmvvm.data.repository.ForecastRepositoryImpl
import com.oganbelema.forecastmvvm.ui.weather.current.CurrentWeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecastApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForecastDatabase(instance()) }
        bind() from singleton { instance<ForecastDatabase>().currentWeatherDoa() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApixuWeatherApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        //sets the values for the shared preferences to default but on;y if user has not selected a value
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }
}