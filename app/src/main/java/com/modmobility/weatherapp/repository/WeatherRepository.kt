package com.modmobility.weatherapp.repository

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.modmobility.weatherapp.api.WeatherAPI
import com.modmobility.weatherapp.models.WeatherModel

/**
 * Implementation of repository interface which interacts with Api layer to get weather data
 */
class WeatherRepository(val api:WeatherAPI,val context:Context):MainRepository {
    override suspend fun getWeather(city: String): WeatherModel {

        val weather= api.getWeather(city,"da9afc35268032c26ae0033db8647f33").await()
       val prefs= context.getSharedPreferences("weathermodel",0)

        val gson=Gson()
        prefs.edit {putString("weatherModel",gson.toJson(weather)) }
        return weather
        }
    }

