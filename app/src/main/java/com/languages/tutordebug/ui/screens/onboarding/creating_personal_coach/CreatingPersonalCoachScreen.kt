package com.languages.tutordebug.ui.screens.onboarding.creating_personal_coach

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.languages.tutordebug.R
import com.languages.tutordebug.ui.LocalNavController
import com.languages.tutordebug.ui.custom_ui.AnimatedLinearProgressIndicator
import com.languages.tutordebug.ui.custom_ui.UserFeedbackView
import com.languages.tutordebug.ui.model.Navigation.MainNavigation
import com.languages.tutordebug.utils.fontDimensionResource
import com.languages.tutordebug.utils.userFeedbackList

@Composable
fun CreatingPersonalCoachScreen() {
    val navController = LocalNavController.current
    val viewModel: CreatingPersonalCoachVM = hiltViewModel()
    val progress = viewModel.progressState.collectAsStateWithLifecycle()
    BackHandler(enabled = true) {}

    CreatingPersonalCoachContent {
        val value = progress.value
        if (value < 1f)
            value
        else {
            viewModel.onboardingDone()
            navController.navigate(MainNavigation.route)
            value
        }
    }
}

@Composable
fun CreatingPersonalCoachContent(
    progressFunc: () -> Float
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.surface,
                            MaterialTheme.colorScheme.surface,
                            MaterialTheme.colorScheme.inverseSurface
                        )
                    )
                ), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.label_creating_your_personal_language_coach),
                style = TextStyle(
                    fontSize = fontDimensionResource(id = R.dimen.text_26),
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = FontFamily(Font(R.font.lato_bold)),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .wrapContentHeight(Alignment.Bottom)
                    .padding(horizontal = dimensionResource(id = R.dimen.offset_26))
                    .padding(top = dimensionResource(id = R.dimen.offset_62))
            )

            Spacer(modifier = Modifier.weight(0.5f))

            userFeedbackList.forEach { userFeedbackModel ->
                UserFeedbackView(
                    title = stringResource(id = userFeedbackModel.title),
                    text = stringResource(id = userFeedbackModel.description),
                    modifier = Modifier.padding(top = dimensionResource(id = R.dimen.offset_20))
                )
            }

            Spacer(modifier = Modifier.weight(2f))

            AnimatedLinearProgressIndicator(
                indicatorProgress = progressFunc.invoke(),
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.offset_20))
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun CreatingPersonalCoachPreview() {
    CreatingPersonalCoachContent { 0.5f }
}