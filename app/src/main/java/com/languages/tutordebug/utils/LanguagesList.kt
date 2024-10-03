package com.languages.tutordebug.utils

import com.languages.tutordebug.R
import com.languages.tutordebug.ui.model.UserLanguage
import com.languages.tutordebug.ui.model.enums.UsersLanguageEnum

val languagesList = listOf(
    UserLanguage(
        languageCode = UsersLanguageEnum.ENGLISH,
        icon = R.drawable.ic_united_kindom
    ),
    UserLanguage(
        languageCode = UsersLanguageEnum.SPANISH,
        icon = R.drawable.ic_spain
    ),
    UserLanguage(
        languageCode = UsersLanguageEnum.GERMAN,
        icon = R.drawable.ic_germany
    ),
    UserLanguage(
        languageCode = UsersLanguageEnum.PORTUGUESE,
        icon = R.drawable.ic_portugal
    ),
    UserLanguage(
        languageCode = UsersLanguageEnum.TURKISH,
        icon = R.drawable.ic_turkey
    )
)