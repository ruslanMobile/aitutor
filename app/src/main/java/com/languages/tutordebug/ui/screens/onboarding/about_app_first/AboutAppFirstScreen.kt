package com.languages.tutordebug.ui.screens.onboarding.about_app_first

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.languages.tutordebug.R
import com.languages.tutordebug.ui.LocalNavController
import com.languages.tutordebug.ui.model.Screen.AboutAppSecond
import com.languages.tutordebug.utils.fontDimensionResource

@Composable
fun AboutAppFirstScreen() {
    val navController = LocalNavController.current
    AboutAppFirstContent {
        navController.navigate(AboutAppSecond.route)
    }
}

@Composable
fun AboutAppFirstContent(
    funcNextScreen: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
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
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.offset_16))
                    .weight(2f),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(id = R.string.label_number_out_of_number, "1"),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Image(
                painter = rememberDrawablePainter(
                    drawable = ContextCompat.getDrawable(
                        LocalContext.current,
                        R.drawable.photo_about_app_1
                    )
                ),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(0.75f)
                    .weight(9f)
                    .wrapContentHeight(Alignment.Bottom),
            )

            Text(
                text = stringResource(id = R.string.label_customized_speaking_pronunciation_coaching),
                style = TextStyle(
                    fontSize = fontDimensionResource(id = R.dimen.text_26),
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = FontFamily(Font(R.font.lato_bold)),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .weight(3f)
                    .wrapContentHeight(Alignment.Bottom)
            )

            Text(
                text = stringResource(id = R.string.text_onboarding_screen_1),
                style = TextStyle(
                    fontSize = fontDimensionResource(id = R.dimen.text_18),
                    color = MaterialTheme.colorScheme.secondary,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(top = dimensionResource(id = R.dimen.offset_12))
                    .padding(
                        horizontal = dimensionResource(
                            id = R.dimen.offset_26
                        )
                    )
            )
            Spacer(modifier = Modifier.weight(2.5f))
            Button(
                onClick = {
                    funcNextScreen.invoke()
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                shape = RoundedCornerShape(
                    dimensionResource(id = R.dimen.offset_16)
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Text(
                    text = stringResource(id = R.string.btn_continue),
                    modifier = Modifier.padding(
                        vertical = dimensionResource(
                            id = R.dimen.offset_8
                        )
                    ),
                    style = TextStyle(
                        fontSize = fontDimensionResource(id = R.dimen.text_14),
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                        textAlign = TextAlign.Center
                    )
                )
            }
            Spacer(modifier = Modifier.weight(2f))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AboutAppFirstPreview() {
    AboutAppFirstContent {}
}