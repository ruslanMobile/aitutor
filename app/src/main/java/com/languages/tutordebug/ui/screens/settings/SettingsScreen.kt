package com.languages.tutordebug.ui.screens.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.languages.tutordebug.MainActivity
import com.languages.tutordebug.R
import com.languages.tutordebug.ui.LocalNavController
import com.languages.tutordebug.ui.custom_ui.SettingItem
import com.languages.tutordebug.ui.model.Screen
import com.languages.tutordebug.ui.model.Screen.UserName
import com.languages.tutordebug.utils.PreferenceDataStoreManager.Keys.DESIRED_LANGUAGE_KEY
import com.languages.tutordebug.utils.PreferenceDataStoreManager.Keys.LANGUAGE_LEVEL_KEY
import com.languages.tutordebug.utils.PreferenceDataStoreManager.Keys.USER_LANGUAGE_KEY
import com.languages.tutordebug.utils.PreferenceDataStoreManager.Keys.USER_NAME_KEY
import com.languages.tutordebug.utils.fontDimensionResource
import com.languages.tutordebug.utils.languageLevelList

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val viewModel: SettingsVM = hiltViewModel()
    val navController = LocalNavController.current
    BackHandler(enabled = true) {
        ActivityCompat.finishAffinity(context as MainActivity)
    }
    SettingsContent(
        viewModel.readStringFromPreferences(USER_LANGUAGE_KEY),
        viewModel.readStringFromPreferences(USER_NAME_KEY),
        viewModel.readStringFromPreferences(DESIRED_LANGUAGE_KEY),
        viewModel.readIntFromPreferences(LANGUAGE_LEVEL_KEY)
    ) { settingItem ->
        when (settingItem) {
            is SettingItem.NativeLanguageItem -> navController.navigate(Screen.NativeLanguageScreen.route)
            is SettingItem.YourNameItem -> navController.navigate(Screen.UserName.route)
            is SettingItem.TargetLanguageItem -> navController.navigate(Screen.DesiredLanguageScreen.route)
            is SettingItem.TargetLanguageLevelItem -> navController.navigate(Screen.LanguageLevelScreen.route)
        }
    }
}

@Composable
fun SettingsContent(
    nativeLanguage: String?,
    userName: String?,
    targetLanguage: String?,
    languageLevel: Int,
    openSettingScreenFunc: (SettingItem) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceContainerLow),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.label_settings),
                style = TextStyle(
                    fontSize = fontDimensionResource(id = R.dimen.text_26),
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = FontFamily(Font(R.font.lato_bold)),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            0f,
                            0f,
                            dimensionResource(id = R.dimen.offset_40).value,
                            dimensionResource(id = R.dimen.offset_40).value
                        )
                    )
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.offset_26))
                    .padding(
                        top = dimensionResource(id = R.dimen.offset_62),
                        bottom = dimensionResource(id = R.dimen.offset_12)
                    )
            )

            Text(
                text = stringResource(id = R.string.text_settings_sescription),
                style = TextStyle(
                    fontSize = fontDimensionResource(id = R.dimen.text_14),
                    color = MaterialTheme.colorScheme.secondary,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.offset_26))
                    .padding(top = dimensionResource(id = R.dimen.offset_8))
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = dimensionResource(id = R.dimen.offset_40))
            ) {
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    SettingItem(
                        R.drawable.ic_baby,
                        R.string.label_native_language_2,
                        nativeLanguage,
                    ) {
                        openSettingScreenFunc.invoke(SettingItem.NativeLanguageItem)
                    }

                    SettingItem(
                        R.drawable.ic_id_card,
                        R.string.label_your_name,
                        userName
                    ) {
                        openSettingScreenFunc.invoke(SettingItem.YourNameItem)
                    }

                    SettingItem(
                        R.drawable.ic_language,
                        R.string.label_target_language,
                        targetLanguage
                    ) {
                        openSettingScreenFunc.invoke(SettingItem.TargetLanguageItem)
                    }

                    SettingItem(
                        R.drawable.ic_rating,
                        R.string.label_target_language_level,
                        stringResource(id = languageLevelList[languageLevel].levelName),
                        false,
                    ) {
                        openSettingScreenFunc.invoke(SettingItem.TargetLanguageLevelItem)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingsPreview() {
    SettingsContent(
        "", "", "", 0
    ) {}
}