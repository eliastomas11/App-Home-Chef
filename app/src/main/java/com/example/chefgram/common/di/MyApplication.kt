package com.example.chefgram.common.di

import android.app.Application
import android.app.UiModeManager
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.example.chefgram.data.repository.notifications.RecipeNotificationService
import com.example.chefgram.data.repository.reciperepo.recipe.local.prefs.PreferencesRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MyApplication() : Application() {

    private val exceptionHandler = CoroutineExceptionHandler{ _,_ ->

    }
    private val scope = CoroutineScope(Job() + Dispatchers.IO + exceptionHandler)

    @Inject
    lateinit var recipeNotificationServiceImpl: RecipeNotificationService

    @Inject
    lateinit var userPrefs: PreferencesRepository
    override fun onCreate() {
        super.onCreate()
        scope.launch {
            try {
            val notificationEnabled = userPrefs.getNotificationsPref()
            val themeEnabled = userPrefs.getThemePref()
            if (notificationEnabled == true) {
                recipeNotificationServiceImpl.createNotificationChannel()
                recipeNotificationServiceImpl.scheduleNotification()
            }
            if(themeEnabled == true){
                changeTheme(isNight = true)
            }else{
                changeTheme(isNight = false)
            }
            }catch (e: Exception){

            }
        }
    }

    private fun changeTheme(isNight: Boolean) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
            if (isNight) {
                UiModeManager.MODE_NIGHT_YES

            } else {
                UiModeManager.MODE_NIGHT_NO
            }
        } else {
            if (isNight) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

}