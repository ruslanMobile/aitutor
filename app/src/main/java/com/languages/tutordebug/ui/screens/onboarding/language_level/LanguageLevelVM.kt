package com.languages.tutordebug.ui.screens.onboarding.language_level

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.languages.tutordebug.utils.PreferenceDataStoreManager
import com.languages.tutordebug.utils.PreferenceDataStoreManager.Keys.DESIRED_LANGUAGE_KEY
import com.languages.tutordebug.utils.PreferenceDataStoreManager.Keys.LANGUAGE_LEVEL_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageLevelVM @Inject constructor(
    private val dataStoreManager: PreferenceDataStoreManager
) : ViewModel() {

    fun storeLanguageLevel(languageLevel: Int) = viewModelScope.launch {
        dataStoreManager.saveInt(LANGUAGE_LEVEL_KEY, languageLevel)
    }
}