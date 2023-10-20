package com.example.chefgram.data.repository.notifications

import android.content.Context

interface RecipeNotificationService {

    fun scheduleNotification()

    fun createNotificationChannel()

    fun createNotification(context: Context)

}