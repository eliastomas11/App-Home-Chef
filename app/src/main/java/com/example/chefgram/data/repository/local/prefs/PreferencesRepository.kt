package com.example.chefgram.data.repository.local.prefs

interface PreferencesRepository {

    suspend fun getNotificationsPref() : Boolean?

    suspend fun getThemePref(): Boolean?

    suspend fun saveNotificationsPref(notificationsEnabled: Boolean)

    suspend fun saveThemePref(nightThemeEnabled: Boolean)
}