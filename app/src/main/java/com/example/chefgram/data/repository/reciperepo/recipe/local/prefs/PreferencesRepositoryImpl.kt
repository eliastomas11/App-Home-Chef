package com.example.chefgram.data.repository.reciperepo.recipe.local.prefs

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(private val preferencesSource: PreferencesSource) :
    PreferencesRepository {
    override suspend fun getNotificationsPref(): Boolean {
        return withContext(Dispatchers.IO) {
            return@withContext preferencesSource.getNotificationsPref()
        }
    }

    override suspend fun getThemePref(): Boolean {
        return withContext(Dispatchers.IO) {
            return@withContext preferencesSource.getThemePref()
        }
    }

    override suspend fun saveNotificationsPref(notificationsEnabled: Boolean) {
        withContext(Dispatchers.IO) {
            preferencesSource.saveNotificationsPref(notificationsEnabled)
        }
    }

    override suspend fun saveThemePref(nightThemeEnabled: Boolean) {
        withContext(Dispatchers.IO) {
            preferencesSource.saveThemePref(nightThemeEnabled)

        }
    }


}