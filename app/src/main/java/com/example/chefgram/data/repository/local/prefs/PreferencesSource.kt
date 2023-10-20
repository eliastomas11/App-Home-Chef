package com.example.chefgram.data.repository.local.prefs

interface PreferencesSource {

    suspend fun getNotificationsPref(): Boolean

    suspend fun getThemePref(): Boolean

    suspend fun saveNotificationsPref(newValue: Boolean)

    suspend fun saveThemePref(newValue: Boolean)
}