package com.languages.tutordebug.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class BottomNavItem(val route: String, val icon: ImageVector) {
    Chat(Screen.HomeScreen.route, Icons.Default.Home),
    Settings(Screen.SettingsScreen.route, Icons.Default.Settings)
}