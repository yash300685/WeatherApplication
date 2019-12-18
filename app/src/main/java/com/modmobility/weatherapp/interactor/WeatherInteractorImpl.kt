package com.modmobility.weatherapp.interactor

import com.modmobility.weatherapp.models.WeatherModel
import com.modmobility.weatherapp.repository.MainRepository
import com.modmobility.weatherapp.usecase.UseCaseResult

/**
 * Pure Kotlin Interactor/Use Case class converting Data Model to Business/App specific data
 */
class WeatherInteractorImpl(private val mainRepository: MainRepository):weatherInteractor
{
    override suspend fun getWeather(city:String): UseCaseResult<String> {
        return try {
            val result:WeatherModel =mainRepository.getWeather(city)

            val temprature=result.main.temp

            UseCaseResult.Success(temprature)
        }

        catch(ex:Exception)
        {
             UseCaseResult.Error(ex)
        }


    }



}