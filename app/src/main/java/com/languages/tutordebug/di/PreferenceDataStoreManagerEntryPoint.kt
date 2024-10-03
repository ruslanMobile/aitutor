package com.languages.tutordebug.di

import android.content.Context
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.preferencesDataStore
import com.languages.tutordebug.utils.PreferenceDataStoreManager
import com.languages.tutordebug.utils.PreferenceDataStoreManager.Keys.PREF_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@EntryPoint
@InstallIn(SingletonComponent::class)
interface PreferenceDataStoreManagerEntryPoint {
    val preferenceDataStoreManager: PreferenceDataStoreManager
}

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    val Context.datastore by preferencesDataStore(
        name = PREF_NAME,
        produceMigrations = { context ->
            listOf(
                SharedPreferencesMigration(
                    context,
                    PREF_NAME
                )
            )
        }
    )

    @Singleton
    @Provides
    fun providePreferencesStorage(@ApplicationContext context: Context) =
        PreferenceDataStoreManager(context.datastore)
}