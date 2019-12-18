package com.modmobility.weatherapp.di

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.modmobility.weatherapp.api.WeatherAPI
import com.modmobility.weatherapp.fragment.WeatherViewModel
import com.modmobility.weatherapp.interactor.WeatherInteractorImpl
import com.modmobility.weatherapp.interactor.weatherInteractor
import com.modmobility.weatherapp.repository.MainRepository
import com.modmobility.weatherapp.repository.WeatherRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val API_BASE_URL = "https://api.openweathermap.org/data/2.5/"

/**
 * Koin DI module to inject constructor dependency in various components
 */
val weatherModule = module {
    viewModel { WeatherViewModel(get()) }
    factory<weatherInteractor> { WeatherInteractorImpl(get()) }
    factory<MainRepository> { WeatherRepository(get(),androidContext()) }
    single {
        createWebService<WeatherAPI>(
            okHttpClient = createHttpClient(),
            factory = RxJava2CallAdapterFactory.create(),
            baseUrl = API_BASE_URL
        )
    }


}


/* Returns a custom OkHttpClient instance with interceptor. Used for building Retrofit service */
fun createHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
    client.readTimeout(5 * 60, TimeUnit.SECONDS)
    return client.addInterceptor {
        val original = it.request()
        val requestBuilder = original.newBuilder()
        requestBuilder.header("Content-Type", "application/json")
        val request = requestBuilder.method(original.method(), original.body()).build()
        return@addInterceptor it.proceed(request)
    }.build()
}

/* function to build our Retrofit service */
inline fun <reified T> createWebService(
    okHttpClient: OkHttpClient,
    factory: CallAdapter.Factory, baseUrl: String
): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addCallAdapterFactory(factory)
        .client(okHttpClient)
        .build()
    return retrofit.create(T::class.java)
}
