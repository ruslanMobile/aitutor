package com.languages.tutordebug.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Navigation(val route: String): Parcelable {


    @Parcelize
    object OnboardingNavigation : Navigation("onboarding")
}