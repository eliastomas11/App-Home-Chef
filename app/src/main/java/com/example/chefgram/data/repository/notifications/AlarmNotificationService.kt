package com.example.chefgram.data.repository.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlarmNotificationService @Inject constructor() : BroadcastReceiver(){

    @Inject lateinit var recipeNotificationServiceImpl: RecipeNotificationService
    override fun onReceive(context: Context?, intent: Intent?) {
        recipeNotificationServiceImpl.createNotification(context!!)
    }


}