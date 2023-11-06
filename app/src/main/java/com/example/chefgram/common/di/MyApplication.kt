package com.example.chefgram.common.di

import android.app.Application
import android.content.res.Configuration
import com.example.chefgram.data.repository.local.prefs.PreferencesRepository
import com.example.chefgram.data.repository.notifications.RecipeNotificationService
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MyApplication() : Application() {

    @Inject
    lateinit var recipeNotificationServiceImpl: RecipeNotificationService

    @Inject
    lateinit var userPrefs: PreferencesRepository
    override fun onCreate() {
        super.onCreate()
        GlobalScope.launch {
            val notificationEnabled = userPrefs.getNotificationsPref()
            val themeEnabled = userPrefs.getThemePref()
            if (notificationEnabled == true) {
                recipeNotificationServiceImpl.createNotificationChannel()
                recipeNotificationServiceImpl.scheduleNotification()
            }
            if(themeEnabled == true){
                val nightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
                nightMode == Configuration.UI_MODE_NIGHT_YES
            }
        }
    }


}