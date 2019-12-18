package com.modmobility.weatherapp.usecase

/**
 * Kotlin Sealed class which acts like a ENUM to hold Success and Error Objects
 */
sealed class UseCaseResult<out T : Any> {
    class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    class Error(val exception: Throwable) : UseCaseResult<Nothing>()
}