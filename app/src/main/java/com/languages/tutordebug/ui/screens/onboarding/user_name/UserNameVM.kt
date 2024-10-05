package com.languages.tutordebug.ui.screens.onboarding.user_name

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.languages.tutordebug.utils.PreferenceDataStoreManager
import com.languages.tutordebug.utils.PreferenceDataStoreManager.Keys.USER_NAME_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserNameVM @Inject constructor(
    private val dataStoreManager: PreferenceDataStoreManager
) : ViewModel() {

    fun saveUserName(name: String) = viewModelScope.launch {
        dataStoreManager.saveString(USER_NAME_KEY, name)
    }
}