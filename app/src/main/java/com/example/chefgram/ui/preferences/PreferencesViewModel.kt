package com.example.chefgram.ui.preferences

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chefgram.common.errorhandling.CustomErrors
import com.example.chefgram.data.repository.local.prefs.PreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PreferencesViewModel @Inject constructor(private val prefsRepository: PreferencesRepository) :
    ViewModel() {

    private val _notificationsEnabled = MutableLiveData<Boolean>(true)
    val notificationsEnabled: LiveData<Boolean> get() = _notificationsEnabled

    private val _themePrefEnabled = MutableLiveData<Boolean>(true)
    val themePrefEnabled: LiveData<Boolean> get() = _themePrefEnabled

    private val _prefsError = MutableLiveData<CustomErrors>()
    val prefsError: LiveData<CustomErrors> get() = _prefsError

    init {
        viewModelScope.launch {
            try {
                _notificationsEnabled.value = prefsRepository.getNotificationsPref()
                _themePrefEnabled.value = prefsRepository.getThemePref()
            } catch (e: Exception) {
                _prefsError.value = CustomErrors.GeneralError
            }
        }
    }

    fun notificationsChanged(notificationsEnabled: Boolean) {
        try {
            viewModelScope.launch {
                prefsRepository.saveNotificationsPref(notificationsEnabled)
                _notificationsEnabled.value = notificationsEnabled
            }
        } catch (e: Exception) {
            _prefsError.value = CustomErrors.GeneralError
        }

    }

    fun themePrefChanged(nightThemeEnabled: Boolean) {
        try {
            viewModelScope.launch {
                prefsRepository.saveThemePref(nightThemeEnabled)
                _themePrefEnabled.value = nightThemeEnabled
            }
        } catch (e: Exception) {
            _prefsError.value = CustomErrors.GeneralError
        }
    }

}


