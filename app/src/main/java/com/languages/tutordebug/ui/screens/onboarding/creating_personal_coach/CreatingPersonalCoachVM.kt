package com.languages.tutordebug.ui.screens.onboarding.creating_personal_coach

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CreatingPersonalCoachVM @Inject constructor(

) : ViewModel() {

    val progressState = MutableStateFlow(0f)

    init {
        viewModelScope.launch {
            while (progressState.value < MAX_PROGRESS) {
                progressState.value += getRandomValue()
                delay(PROGRESS_STEP_DELAY)
            }
        }
    }

    private fun getRandomValue(): Float {
        return Random.nextFloat() * (MAX_PROGRESS_STEP - MIN_PROGRESS_STEP) + MIN_PROGRESS_STEP
    }

    companion object {
        const val MAX_PROGRESS = 1f
        const val PROGRESS_STEP_DELAY = 1200L
        const val MIN_PROGRESS_STEP = 0.07f
        const val MAX_PROGRESS_STEP = 0.15f
    }
}