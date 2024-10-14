package com.languages.tutordebug.ui.screens.onboarding.desired_lanaguage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.languages.tutordebug.utils.PreferenceDataStoreManager
import com.languages.tutordebug.utils.PreferenceDataStoreManager.Keys.DESIRED_LANGUAGE_KEY
import com.languages.tutordebug.utils.PreferenceDataStoreManager.Keys.IS_ONBOARDING_DONE_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DesiredLanguageVM @Inject constructor(
    private val dataStoreManager: PreferenceDataStoreManager
) : ViewModel() {

    fun storeDesiredLanguage(language: String, resultFunc: () -> Unit) = viewModelScope.launch {
        dataStoreManager.saveString(DESIRED_LANGUAGE_KEY, language)
        resultFunc.invoke()
    }

    fun isOnboardingDone() = dataStoreManager.readBooleanBlocking(IS_ONBOARDING_DONE_KEY)

}