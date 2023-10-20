package com.example.chefgram.common

import android.content.Context
import androidx.core.content.ContextCompat.getString
import com.example.chefgram.R
import com.example.chefgram.common.errorhandling.CustomErrors

class StringUtils() {

    companion object {
        fun getErrorString(context: Context,error: CustomErrors): String {
            return when (error) {
                CustomErrors.GeneralError -> getString(context,R.string.general_error)
                CustomErrors.NetworkError -> getString(context,R.string.network_error)
                CustomErrors.NoNetworkConnection -> getString(context,R.string.no_network_connection_error)
                CustomErrors.RecipeNotFound -> getString(context,R.string.recipe_not_found_error)
                CustomErrors.TimeOutError -> getString(context,R.string.time_out_error)
                else -> getString(context,R.string.unknown_error)
            }
        }

    }

}