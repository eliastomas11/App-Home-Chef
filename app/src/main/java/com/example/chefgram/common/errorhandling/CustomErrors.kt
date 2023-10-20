package com.example.chefgram.common.errorhandling


sealed class CustomErrors(val msg: String? = null) {
    data object NetworkError : CustomErrors()
    data object UnknownError : CustomErrors()
    data class GeneralError(val message: String?) : CustomErrors()
    data object NoNetworkConnection : CustomErrors()
    data class LocalDatabaseError(val message: String?) : CustomErrors()
    data object RecipeNotFound : CustomErrors()
    data object TimeOutError : CustomErrors()

    sealed class ResponseError(val code: Int,val errorMessage: String? = null) : CustomErrors(errorMessage) {
        data class Unauthorized(val message: String? = null) : ResponseError(code = 401,errorMessage = message)
        data class Forbidden(val message: String? = null) : ResponseError(code = 403,errorMessage = message)
        data class NotFound(val message: String? = null) : ResponseError(code = 404,errorMessage = message)
        data class BadRequest(val message: String? = null) : ResponseError(code = 400,errorMessage = message)
        data class InternalServerError(val message: String? = null) : ResponseError(code = 500,errorMessage = message)
    }

    companion object {
        fun mapToResponseError(code: Int): CustomErrors {
            return when (code) {
                401 -> ResponseError.Unauthorized()
                403 -> ResponseError.Forbidden()
                404 -> ResponseError.NotFound()
                400 -> ResponseError.BadRequest()
                500 -> ResponseError.InternalServerError()
                else -> CustomErrors.NetworkError
            }
        }

    }


}