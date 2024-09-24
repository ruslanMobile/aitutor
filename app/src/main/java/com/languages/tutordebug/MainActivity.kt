package com.languages.tutordebug

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.languages.tutordebug.ui.LocalNavController
import com.languages.tutordebug.ui.model.Navigation.*
import com.languages.tutordebug.ui.model.Screen.*
import com.languages.tutordebug.ui.screens.onboarding.about_app_first.AboutAppFirstScreen
import com.languages.tutordebug.ui.theme.AiTutorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
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
                        }
                    }
                }
            }
        }
    }
}