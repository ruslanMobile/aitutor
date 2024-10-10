package com.languages.tutordebug.ui.screens.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.languages.tutordebug.MainActivity

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    BackHandler(enabled = true) {
        ActivityCompat.finishAffinity(context as MainActivity)
    }
    SettingsContent()
}

@Composable
fun SettingsContent() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .background(Color.Blue.copy(0.3f))
        ) {

        }
    }
}

@Preview
@Composable
fun SettingsPreview() {
    SettingsContent()
}