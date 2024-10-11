package com.languages.tutordebug

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.languages.tutordebug.di.PreferenceDataStoreManagerEntryPoint
import com.languages.tutordebug.ui.LocalNavController
import com.languages.tutordebug.ui.model.BottomNavItem
import com.languages.tutordebug.ui.model.Navigation.*
import com.languages.tutordebug.ui.model.Screen.*
import com.languages.tutordebug.ui.model.enums.UsersLanguageEnum
import com.languages.tutordebug.ui.screens.home.HomeScreen
import com.languages.tutordebug.ui.screens.onboarding.about_app_first.AboutAppFirstScreen
import com.languages.tutordebug.ui.screens.onboarding.about_app_second.AboutAppSecondScreen
import com.languages.tutordebug.ui.screens.onboarding.creating_personal_coach.CreatingPersonalCoachScreen
import com.languages.tutordebug.ui.screens.onboarding.desired_lanaguage.DesiredLanguageScreen
import com.languages.tutordebug.ui.screens.onboarding.language_level.LanguageLevelScreen
import com.languages.tutordebug.ui.screens.onboarding.native_language.NativeLanguageScreen
import com.languages.tutordebug.ui.screens.onboarding.user_name.UserNameScreen
import com.languages.tutordebug.ui.screens.settings.SettingsScreen
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
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                CompositionLocalProvider(
                    value = LocalNavController provides navController
                ) {
                    var selectedIndex = remember {
                        mutableIntStateOf(0)
                    }
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        containerColor = Color.Transparent,
                        bottomBar = {
                            val mainRoute = navBackStackEntry?.destination?.parent?.route
                            if (mainRoute == MainNavigation.route) {
                                AnimatedNavigationBar(
                                    modifier = Modifier
                                        .height(dimensionResource(id = R.dimen.offset_62)),
                                    selectedIndex = selectedIndex.value,
                                    barColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                                    ballColor = MaterialTheme.colorScheme.surfaceContainerHigh
                                ) {
                                    BottomNavItem.values().forEach { item ->
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(
                                                    if (selectedIndex.value == item.ordinal) MaterialTheme.colorScheme.surfaceContainerHighest else MaterialTheme.colorScheme.surfaceContainerHigh
                                                )
                                                .clickable {
                                                    selectedIndex.value = item.ordinal
                                                    navController.navigate(item.route)
                                                },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                contentDescription = null,
                                                imageVector = item.icon,
                                                tint = if (selectedIndex.value == item.ordinal) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSecondaryContainer.copy(
                                                    0.5f
                                                ),
                                                modifier = Modifier.size(dimensionResource(id = R.dimen.offset_26))
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    ) {
                        Surface(
                            modifier = Modifier.padding(
                                bottom = it.calculateBottomPadding()
                            )
                        ) {
                            NavHost(
                                navController = navController,
                                startDestination = if (viewModel.isOnboardingDone()) MainNavigation.route else OnboardingNavigation.route
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
                                navigation(
                                    route = MainNavigation.route,
                                    startDestination = HomeScreen.route
                                ) {
                                    composable(HomeScreen.route) {
                                        HomeScreen()
                                    }

                                    composable(SettingsScreen.route) {
                                        SettingsScreen()
                                    }
                                }
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