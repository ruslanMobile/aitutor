package com.languages.tutordebug

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.languages.tutordebug.di.PreferenceDataStoreManagerEntryPoint
import com.languages.tutordebug.ui.LocalNavController
import com.languages.tutordebug.ui.model.Navigation.*
import com.languages.tutordebug.ui.model.Screen.*
import com.languages.tutordebug.ui.model.enums.UsersLanguageEnum
import com.languages.tutordebug.ui.screens.onboarding.about_app_first.AboutAppFirstScreen
import com.languages.tutordebug.ui.screens.onboarding.about_app_second.AboutAppSecondScreen
import com.languages.tutordebug.ui.screens.onboarding.creating_personal_coach.CreatingPersonalCoachScreen
import com.languages.tutordebug.ui.screens.onboarding.desired_lanaguage.DesiredLanguageScreen
import com.languages.tutordebug.ui.screens.onboarding.language_level.LanguageLevelScreen
import com.languages.tutordebug.ui.screens.onboarding.native_language.NativeLanguageScreen
import com.languages.tutordebug.ui.screens.onboarding.user_name.UserNameScreen
import com.languages.tutordebug.ui.theme.AiTutorTheme
import com.languages.tutordebug.utils.LocaleHelper
import com.languages.tutordebug.utils.PreferenceDataStoreManager.Keys.USER_LANGUAGE_KEY
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.runBlocking
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainVM by viewModels<MainVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AiTutorTheme {
                val navController = rememberNavController()

                CompositionLocalProvider(
                    value = LocalNavController provides navController
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = OnboardingNavigation.route
                    ) {
                        navigation(
                            route = OnboardingNavigation.route,
                            startDestination = AboutAppFirst.route
                        ) {
                            composable(AboutAppFirst.route) {
                                AboutAppFirstScreen()
                            }
                            composable(AboutAppSecond.route) {
                                AboutAppSecondScreen()
                            }
                            composable(UserName.route) {
                                UserNameScreen()
                            }
                            composable(NativeLanguageScreen.route) {
                                NativeLanguageScreen()
                            }
                            composable(DesiredLanguageScreen.route) {
                                DesiredLanguageScreen()
                            }
                            composable(LanguageLevelScreen.route) {
                                LanguageLevelScreen()
                            }
                            composable(CreatingPersonalCoachScreen.route) {
                                CreatingPersonalCoachScreen()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        val newContext: Context? = setLocale(newBase)
        super.attachBaseContext(newContext)
    }

    fun setLocale(newBase: Context?): Context? {
        return runBlocking {
            return@runBlocking newBase?.let {
                val wrapper = EntryPointAccessors.fromApplication(
                    newBase,
                    PreferenceDataStoreManagerEntryPoint::class.java
                )
                LocaleHelper.onAttach(
                    newBase,
                    Locale(
                        wrapper.preferenceDataStoreManager.readStringBlocking(USER_LANGUAGE_KEY)
                            ?: UsersLanguageEnum.ENGLISH.lCode
                    )
                )
            }
        }
    }
}