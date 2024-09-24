package com.languages.tutordebug.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Screen(val route: String) : Parcelable {

    @Parcelize
    object AboutAppFirst : Screen("aboutAppFirst")
}