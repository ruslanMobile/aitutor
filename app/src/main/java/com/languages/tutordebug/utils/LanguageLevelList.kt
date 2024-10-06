package com.languages.tutordebug.utils

import com.languages.tutordebug.R
import com.languages.tutordebug.ui.model.LanguageLevelModel

val languageLevelList = listOf(
    LanguageLevelModel(
        levelName = R.string.label_beginner,
        levelCode = R.string.label_a1_a2
    ),
    LanguageLevelModel(
        levelName = R.string.label_intermediate,
        levelCode = R.string.label_b1_b2
    ),
    LanguageLevelModel(
        levelName = R.string.label_advanced,
        levelCode = R.string.label_c1_c2
    )
)