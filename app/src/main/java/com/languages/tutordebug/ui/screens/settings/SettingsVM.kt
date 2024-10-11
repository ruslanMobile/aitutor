package com.languages.tutordebug.ui.screens.settings

import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import com.languages.tutordebug.utils.PreferenceDataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsVM @Inject constructor(
    private val preferenceDataStoreManager: PreferenceDataStoreManager
) : ViewModel() {

    fun readStringFromPreferences(key: Preferences.Key<String>) = preferenceDataStoreManager.readStringBlocking(key)

    fun readIntFromPreferences(key: Preferences.Key<Int>) = preferenceDataStoreManager.readIntBlocking(key)
}

sealed class SettingItem {
    object NativeLanguageItem : SettingItem()
    object YourNameItem : SettingItem()
    object TargetLanguageItem : SettingItem()
    object TargetLanguageLevelItem : SettingItem()
}