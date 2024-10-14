package com.languages.tutordebug.ui.screens.onboarding.desired_lanaguage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.languages.tutordebug.R
import com.languages.tutordebug.ui.LocalNavController
import com.languages.tutordebug.ui.custom_ui.BasicAuthTextField
import com.languages.tutordebug.ui.model.Screen.LanguageLevelScreen
import com.languages.tutordebug.ui.model.Screen.UserName
import com.languages.tutordebug.utils.desiredLanguagesList
import com.languages.tutordebug.utils.fontDimensionResource

@Composable
fun DesiredLanguageScreen() {
    val navController = LocalNavController.current
    val viewModel: DesiredLanguageVM = hiltViewModel()
    val isOnboardingDone = viewModel.isOnboardingDone()
    DesiredLanguageContent(isOnboardingDone, { value ->
        viewModel.storeDesiredLanguage(value) {
            if (isOnboardingDone)
                navController.popBackStack()
            else
                navController.navigate(LanguageLevelScreen.route)
        }
    }, {
        navController.popBackStack()
    })
}

@Composable
fun DesiredLanguageContent(
    isOnboardingDone: Boolean,
    funcNextScreen: (String) -> Unit,
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
                if (!isOnboardingDone) {
                    Text(
                        text = stringResource(id = R.string.label_number_out_of_number, "5"),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            Text(
                text = stringResource(id = R.string.label_wich_language_do_you_wanna_learn),
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

            Spacer(modifier = Modifier.weight(1f))

            val selectedLanguage = remember {
                mutableStateOf(-1)
            }

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(4),
                modifier = Modifier
                    .weight(12.5f),
                contentPadding = PaddingValues(
                    horizontal = dimensionResource(id = R.dimen.offset_20),
                    vertical = dimensionResource(id = R.dimen.offset_20)
                ),
                horizontalArrangement = Arrangement.spacedBy(
                    dimensionResource(id = R.dimen.offset_20)
                ),
                verticalItemSpacing = dimensionResource(id = R.dimen.offset_16)
            ) {
                itemsIndexed(desiredLanguagesList) { index, item ->
                    Column(
                        Modifier.clickable {
                            selectedLanguage.value = index
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .aspectRatio(1f)
                                .border(
                                    width = dimensionResource(id = R.dimen.offset_1),
                                    color = if (selectedLanguage.value == index) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.secondary,
                                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.offset_10))
                                )
                                .background(
                                    if (selectedLanguage.value == index) MaterialTheme.colorScheme.primary.copy(
                                        alpha = 0.3f
                                    ) else Color.Transparent,
                                    shape = RoundedCornerShape(dimensionResource(id = R.dimen.offset_10))
                                ),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.offset_12)),
                                painter = painterResource(id = item.icon),
                                tint = Color.Unspecified,
                                contentDescription = ""
                            )
                        }
                        Text(
                            text = item.languageCode.languageName,
                            maxLines = 1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = dimensionResource(id = R.dimen.offset_6)),
                            overflow = TextOverflow.Ellipsis,
                            style = TextStyle(
                                fontSize = fontDimensionResource(id = R.dimen.text_14),
                                color = if (selectedLanguage.value == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                                fontFamily = FontFamily(Font(R.font.lato_bold)),
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }
            }

            Button(
                onClick = {
                    if (selectedLanguage.value != -1)
                        funcNextScreen.invoke(desiredLanguagesList[selectedLanguage.value].languageCode.languageName)
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                shape = RoundedCornerShape(
                    dimensionResource(id = R.dimen.offset_16)
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (selectedLanguage.value == -1) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.primaryContainer
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
                        color = if (selectedLanguage.value == -1) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onPrimaryContainer,
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
fun DesiredLanguagePreview() {
    DesiredLanguageContent(false, {}, {})
}