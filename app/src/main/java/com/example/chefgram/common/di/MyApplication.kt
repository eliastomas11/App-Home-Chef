package com.example.chefgram.common.di

import android.app.Application
import android.app.UiModeManager
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import com.example.chefgram.data.repository.local.prefs.PreferencesRepository
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication @Inject constructor()  : Application(){

    override fun onCreate() {
        super.onCreate()
    }


}