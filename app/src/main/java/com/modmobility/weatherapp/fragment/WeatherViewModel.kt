package com.modmobility.weatherapp.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.modmobility.weatherapp.interactor.weatherInteractor
import com.modmobility.weatherapp.usecase.UseCaseResult
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * View Model class containing Live Data as data Binding framework to communicate data back to fragment
 */
class WeatherViewModel(val interactor:weatherInteractor) : ViewModel(),CoroutineScope {
    val job= Job()
    override val coroutineContext: CoroutineContext=job+Dispatchers.Main

       val loading= MutableLiveData<Boolean>()
        val tempData=MutableLiveData<String>()
        val error=MutableLiveData<String>()

        fun getWeather(city:String)

        {
            loading.value=true

            launch {
                // Switching from MAIN to IO thread for API operations
                // Update our data list with the new one from API
                val result = withContext(Dispatchers.IO) { interactor.getWeather(city) }
                // Hide progressBar once the operation is done on the MAIN (default) thread
                loading.value = false
                when (result) {

                    is UseCaseResult.Success -> tempData.value = result.data
                    is UseCaseResult.Error -> error .value = result.exception.message

                }
            }

        }


}
