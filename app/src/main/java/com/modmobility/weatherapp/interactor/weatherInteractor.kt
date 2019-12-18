package com.modmobility.weatherapp.interactor

import com.modmobility.weatherapp.usecase.UseCaseResult

interface weatherInteractor {
    suspend fun getWeather( city:String): UseCaseResult<String>
}