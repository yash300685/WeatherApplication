package com.modmobility.weatherapp

import android.app.Application
import com.modmobility.weatherapp.di.weatherModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module

/**
 * Application class which also acts a purpose of providing dependencies throught application using
 * Koin
 */
class WeatherApplication: Application() {

    override fun onCreate() {
        super.onCreate()

       val list =arrayListOf<Module>()
               list.add(weatherModule)
               // Adding Koin modules to our application
               startKoin {
                   androidContext(this@WeatherApplication)
                   modules(list)
               }
    }
}