package com.languages.tutordebug

import androidx.lifecycle.ViewModel
import com.languages.tutordebug.utils.PreferenceDataStoreManager
import com.languages.tutordebug.utils.PreferenceDataStoreManager.Keys.IS_ONBOARDING_DONE_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val dataStoreManager: PreferenceDataStoreManager
) : ViewModel() {

    fun isOnboardingDone() = dataStoreManager.readBooleanBlocking(IS_ONBOARDING_DONE_KEY)
}