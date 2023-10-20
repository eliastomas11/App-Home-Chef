package com.example.chefgram.common.di

import android.app.Application
import com.example.chefgram.data.repository.notifications.RecipeNotificationService
import com.example.chefgram.data.repository.notifications.RecipeNotificationServiceImpl
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication() : Application(){

    @Inject lateinit var recipeNotificationServiceImpl: RecipeNotificationService
    override fun onCreate() {
        super.onCreate()
        recipeNotificationServiceImpl.createNotificationChannel()
        recipeNotificationServiceImpl.scheduleNotification()
    }


}