package com.example.chefgram.ui.preferences

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.chefgram.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}