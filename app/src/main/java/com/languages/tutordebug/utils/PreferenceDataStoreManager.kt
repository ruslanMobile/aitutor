package com.languages.tutordebug.utils

import androidx.datastore.preferences.core.*
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class PreferenceDataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun saveBoolean(key: Preferences.Key<Boolean>, value: Boolean) {
        dataStore.edit { settings ->
            settings[key] = value
        }
    }

    suspend fun readBoolean(key: Preferences.Key<Boolean>): Boolean? {
        val prefs = dataStore.data.first()
        return prefs[key]
    }

    fun readBooleanBlocking(key: Preferences.Key<Boolean>): Boolean {
        var value: Boolean?
        runBlocking {
            value = readBoolean(key)
        }
        return value ?: false
    }

    fun readNullableBooleanBlocking(key: Preferences.Key<Boolean>): Boolean? {
        var value: Boolean?
        runBlocking {
            value = readBoolean(key)
        }
        return value
    }

    suspend fun saveString(key: Preferences.Key<String>, value: String) {
        dataStore.edit { settings ->
            settings[key] = value
        }
    }

    suspend fun readString(key: Preferences.Key<String>): String? {
        val prefs = dataStore.data.first()
        return prefs[key]
    }

    fun readStringLiveData(key: Preferences.Key<String>): Flow<String> {
        return dataStore.data
            .map { prefs ->
                prefs[key] ?: ""
            }.distinctUntilChanged().asLiveData().asFlow()
    }

    fun readStringBlocking(key: Preferences.Key<String>): String? {
        var value: String?
        runBlocking {
            value = readString(key)
        }
        return value
    }

    suspend fun removeString(key: Preferences.Key<String>) {
        dataStore.edit { settings ->
            settings.remove(key)
        }
    }

    suspend fun removeBoolean(key: Preferences.Key<Boolean>) {
        dataStore.edit { settings ->
            settings.remove(key)
        }
    }


    companion object Keys {
        const val PREF_NAME = "tutor_data"
        val USER_LANGUAGE_KEY = stringPreferencesKey("user_language")
    }
}