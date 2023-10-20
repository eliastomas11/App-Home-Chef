package com.example.chefgram.common.errorhandling

import android.database.SQLException

sealed class CustomException(message: String? = null) : Exception(message){
    data object NoNetworkConnection: CustomException("No network connection")
    data object Unknown: CustomException("Unknown error")
    data class ApiError(val code: Int): CustomException()
    data object RecipeNotFoundException: CustomException("Recipe not found")
    data object TimeOutException: CustomException("Time Out")
    fun mapToCustomError(): CustomErrors {
        return when(this) {
            is NoNetworkConnection -> CustomErrors.NoNetworkConnection
            is Unknown -> CustomErrors.GeneralError
            is ApiError -> CustomErrors.mapToResponseError(code)
            is RecipeNotFoundException -> CustomErrors.RecipeNotFound
            TimeOutException -> CustomErrors.TimeOutError
            else -> CustomErrors.UnknownError
        }
    }


}

