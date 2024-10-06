package com.languages.tutordebug.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Screen(val route: String) : Parcelable {

    @Parcelize
    object AboutAppFirst : Screen("aboutAppFirst")

    @Parcelize
    object AboutAppSecond : Screen("aboutAppSecond")

    @Parcelize
    object UserName : Screen("userName")

    @Parcelize
    object NativeLanguageScreen : Screen("nativeLanguageScreen")

    @Parcelize
    object DesiredLanguageScreen : Screen("desiredLanguageScreen")

    @Parcelize
    object LanguageLevelScreen : Screen("languageLevelScreen")
}