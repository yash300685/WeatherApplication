package com.modmobility.weatherapp.repository

import com.modmobility.weatherapp.models.WeatherModel

interface MainRepository {
    suspend fun getWeather(city:String):WeatherModel
}