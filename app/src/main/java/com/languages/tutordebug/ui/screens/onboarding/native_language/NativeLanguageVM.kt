package com.languages.tutordebug.ui.screens.onboarding.native_language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.languages.tutordebug.ui.model.enums.UsersLanguageEnum
import com.languages.tutordebug.utils.PreferenceDataStoreManager
import com.languages.tutordebug.utils.PreferenceDataStoreManager.Keys.USER_LANGUAGE_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NativeLanguageVM @Inject constructor(
    private val dataStoreManager: PreferenceDataStoreManager
) : ViewModel() {

    fun storeUserNativeLanguage(model: UsersLanguageEnum?) = viewModelScope.launch {
        dataStoreManager.saveString(USER_LANGUAGE_KEY, model?.lCode ?: return@launch)
    }
}