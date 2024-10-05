package com.languages.tutordebug.ui.screens.onboarding.user_name

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.languages.tutordebug.R
import com.languages.tutordebug.ui.LocalNavController
import com.languages.tutordebug.ui.custom_ui.BasicAuthTextField
import com.languages.tutordebug.ui.model.Screen
import com.languages.tutordebug.utils.fontDimensionResource

@Composable
fun UserNameScreen() {
    val navController = LocalNavController.current
    val viewModel: UserNameVM = hiltViewModel()
    UserNameContent({ isSkip, value ->
        if (!isSkip)
            viewModel.saveUserName(value)
        navController.navigate(Screen.DesiredLanguageScreen.route)
    }, {
        navController.popBackStack()
    })
}

@Composable
fun UserNameContent(
    funcNextScreen: (Boolean, String) -> Unit,
    funcBack: () -> Unit
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
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    modifier = Modifier
                        .size(dimensionResource(id = R.dimen.offset_32)),
                    onClick = {
                        funcBack.invoke()
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        tint = MaterialTheme.colorScheme.onSurface,
                        contentDescription = ""
                    )
                }

                Text(
                    text = stringResource(id = R.string.label_number_out_of_number, "4", "3"),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                text = stringResource(id = R.string.label_whats_your_name),
                style = TextStyle(
                    fontSize = fontDimensionResource(id = R.dimen.text_26),
                    color = MaterialTheme.colorScheme.primary,
                    fontFamily = FontFamily(Font(R.font.lato_bold)),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .weight(2.5f)
                    .wrapContentHeight(Alignment.Bottom)
                    .padding(horizontal = dimensionResource(id = R.dimen.offset_26))
            )
            val nameState = remember {
                mutableStateOf("")
            }
            BasicAuthTextField(
                nameState,
                R.drawable.ic_person,
                R.string.hint_name,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(top = dimensionResource(id = R.dimen.offset_26))
            )
            Spacer(modifier = Modifier.weight(11f))
            Button(
                onClick = {
                    funcNextScreen.invoke(false, nameState.value)
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

            Button(
                onClick = {
                    funcNextScreen.invoke(true, nameState.value)
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .padding(top = dimensionResource(id = R.dimen.offset_12)),
                interactionSource = remember { MutableInteractionSource() },
                shape = RoundedCornerShape(
                    dimensionResource(id = R.dimen.offset_16)
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    contentColor = Color.Transparent,
                    disabledContentColor = Color.Transparent
                ),
            ) {
                Text(
                    text = stringResource(id = R.string.btn_skip),
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
fun UserNamePreview() {
    UserNameContent({ _, _ -> }, {})
}