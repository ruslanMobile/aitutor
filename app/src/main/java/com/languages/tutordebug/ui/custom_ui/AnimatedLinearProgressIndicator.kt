package com.languages.tutordebug.ui.custom_ui

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.languages.tutordebug.R
import com.languages.tutordebug.ui.screens.onboarding.creating_personal_coach.CreatingPersonalCoachVM.Companion.PROGRESS_STEP_DELAY
import com.languages.tutordebug.utils.fontDimensionResource

@Composable
fun AnimatedLinearProgressIndicator(
    indicatorProgress: Float,
    modifier: Modifier = Modifier
) {
    var progress by remember { mutableStateOf(0F) }
    val progressAnimDuration = PROGRESS_STEP_DELAY.toInt()
    val progressAnimation by animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing),
    )
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(
                id = R.string.label_percents,
                (progressAnimation * 100).toInt().toString()
            ),
            style = TextStyle(
                fontSize = fontDimensionResource(id = R.dimen.text_14),
                color = MaterialTheme.colorScheme.primary,
                fontFamily = FontFamily(Font(R.font.lato_bold)),
                textAlign = TextAlign.Center
            )
        )
        LinearProgressIndicator(
            progress = { progressAnimation },
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.offset_4))
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.offset_16))
                .clip(RoundedCornerShape(dimensionResource(id = R.dimen.offset_16))),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.secondary
        )
    }
    SideEffect {
        progress = indicatorProgress
    }
}

@Preview
@Composable
fun AnimatedLinearProgressIndicatorPreview() {
    AnimatedLinearProgressIndicator(0.5f)
}