package com.example.chefgram.data.repository.reciperepo.recipe.local.prefs

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.ContextCompat.getString
import com.example.chefgram.R
import javax.inject.Inject

class PreferencesSourceImpl @Inject constructor(private val preferenceService: SharedPreferences,private val context: Context)  : PreferencesSource {
    override suspend fun getNotificationsPref(): Boolean {
        return preferenceService.getBoolean(getString(context,R.string.notifications_enabled_key), true)
    }

    override suspend fun getThemePref(): Boolean {
        return preferenceService.getBoolean(getString(context,R.string.theme_night_enabled_key), true)
    }

    override suspend fun saveNotificationsPref(notificationEnabled: Boolean) {
        preferenceService.let {
            it.edit().putBoolean(getString(context,R.string.notifications_enabled_key), notificationEnabled).apply()
        }
    }

    override suspend fun saveThemePref(nightThemeEnabled: Boolean) {
        preferenceService.let {
            it.edit().putBoolean(getString(context,R.string.theme_night_enabled_key), nightThemeEnabled).apply()
        }
    }


}