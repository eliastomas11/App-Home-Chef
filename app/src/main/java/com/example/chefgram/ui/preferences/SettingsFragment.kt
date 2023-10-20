package com.example.chefgram.ui.preferences

import android.app.UiModeManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.example.chefgram.R
import com.example.chefgram.common.StringUtils
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    private val viewModel by viewModels<PreferencesViewModel>()
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initUI()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun initUI(){
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        findPreference<Preference>(getString(R.string.privacy_pref_key))?.setOnPreferenceClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_privacyScreen)
            true
        }

        findPreference<Preference>(getString(R.string.about_pref_key))?.setOnPreferenceClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_aboutScreen)
            true
        }

        findPreference<SwitchPreferenceCompat>(getString(R.string.notifications_enabled_key))?.setOnPreferenceChangeListener { _, newValue ->
            viewModel.notificationsChanged(newValue as Boolean)
            true
        }

        findPreference<SwitchPreferenceCompat>(getString(R.string.theme_night_enabled_key))?.setOnPreferenceChangeListener { _, newValue ->
            viewModel.themePrefChanged(newValue as Boolean)
            true
        }
    }

    private fun initObservers() {
        viewModel.themePrefEnabled.observe(viewLifecycleOwner){
            findPreference<SwitchPreferenceCompat>(getString(R.string.theme_night_enabled_key))?.isChecked = it
        }
        viewModel.notificationsEnabled.observe(viewLifecycleOwner) {
            findPreference<SwitchPreferenceCompat>(getString(R.string.notifications_enabled_key))?.isChecked = it
        }

        viewModel.prefsError.observe(viewLifecycleOwner){ error ->
            Snackbar.make(requireContext(),requireView(), StringUtils.getErrorString(requireContext(),error), Snackbar.LENGTH_SHORT).show()
        }

    }

}