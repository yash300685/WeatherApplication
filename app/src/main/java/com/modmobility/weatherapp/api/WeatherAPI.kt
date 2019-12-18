package com.modmobility.weatherapp.api

import com.modmobility.weatherapp.models.WeatherModel
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit Interface
 */
interface WeatherAPI {

        @GET("weather")
        fun getWeather(@Query("q")city:String,@Query("appid")apiKey:String): Deferred<WeatherModel>

    }
