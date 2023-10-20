package com.example.chefgram.common.errorhandling


sealed class CustomErrors(val msg: String? = null) {
    data object NetworkError : CustomErrors()
    data object UnknownError : CustomErrors()
    data object GeneralError : CustomErrors()
    data object NoNetworkConnection : CustomErrors()
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
                401 -> ResponseError.Unauthorized("Unauthorized")
                403 -> ResponseError.Forbidden("Forbidden")
                404 -> ResponseError.NotFound("Not Found")
                400 -> ResponseError.BadRequest("Bad Request")
                500 -> ResponseError.InternalServerError("Internal Server Error")
                else -> CustomErrors.NetworkError
            }
        }

    }


}