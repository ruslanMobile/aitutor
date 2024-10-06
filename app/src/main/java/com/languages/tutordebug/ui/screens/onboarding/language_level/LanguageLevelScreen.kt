package com.languages.tutordebug.ui.screens.onboarding.language_level

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
import com.languages.tutordebug.ui.model.Screen.UserName
import com.languages.tutordebug.utils.fontDimensionResource
import com.languages.tutordebug.utils.languageLevelList

@Composable
fun LanguageLevelScreen() {
    val navController = LocalNavController.current
    val viewModel: LanguageLevelVM = hiltViewModel()
    LanguageLevelContent({ level ->
        viewModel.storeLanguageLevel(level)
        //navController.navigate(UserName.route)
    }, {
        navController.popBackStack()
    })
}

@Composable
fun LanguageLevelContent(
    funcNextScreen: (Int) -> Unit, funcBack: () -> Unit
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
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.offset_16))
                    .weight(2f),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(modifier = Modifier.size(dimensionResource(id = R.dimen.offset_32)),
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
                    text = stringResource(id = R.string.label_number_out_of_number, "6", "3"),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                text = stringResource(id = R.string.label_what_is_your_english_level),
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

            Text(
                text = stringResource(id = R.string.text_we_will_personalize_conversation),
                style = TextStyle(
                    fontSize = fontDimensionResource(id = R.dimen.text_18),
                    color = MaterialTheme.colorScheme.secondary,
                    fontFamily = FontFamily(Font(R.font.lato_regular)),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .weight(2f)
                    .padding(top = dimensionResource(id = R.dimen.offset_12))
                    .padding(
                        horizontal = dimensionResource(
                            id = R.dimen.offset_26
                        )
                    )
            )

            Spacer(modifier = Modifier.weight(1f))

            val selectedItem = remember {
                mutableIntStateOf(-1)
            }

            LazyColumn(
                modifier = Modifier
                    .weight(10.5f)
                    .padding(horizontal = dimensionResource(id = R.dimen.offset_20))
            ) {
                itemsIndexed(items = languageLevelList) { index, item ->
                    Card(
                        modifier = Modifier
                            .padding(bottom = dimensionResource(id = R.dimen.offset_12))
                            .fillMaxWidth()
                            .height(dimensionResource(id = R.dimen.offset_62)),
                        border = if (index == selectedItem.value) BorderStroke(
                            dimensionResource(id = R.dimen.offset_2),
                            MaterialTheme.colorScheme.outline
                        ) else null,
                        colors = CardDefaults.cardColors(
                            containerColor = if (index == selectedItem.value) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer,
                        ),
                        onClick = {
                            selectedItem.value = index
                        }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(
                                    horizontal = dimensionResource(
                                        id = R.dimen.text_16
                                    )
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.offset_12)),
                                text = stringResource(id = item.levelName),
                                style = TextStyle(
                                    fontSize = fontDimensionResource(id = R.dimen.text_16),
                                    color = if (index == selectedItem.value) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSecondaryContainer,
                                    fontFamily = FontFamily(Font(R.font.lato_bold)),
                                    textAlign = TextAlign.Center
                                )
                            )
                            Text(
                                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.offset_12)),
                                text = stringResource(id = item.levelCode),
                                style = TextStyle(
                                    fontSize = fontDimensionResource(id = R.dimen.text_16),
                                    color = if (index == selectedItem.value) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSecondaryContainer,
                                    fontFamily = FontFamily(Font(R.font.lato_bold)),
                                    textAlign = TextAlign.Center
                                )
                            )
                        }
                    }
                }
            }

            Button(
                onClick = {
                    if (selectedItem.value != -1)
                        funcNextScreen.invoke(selectedItem.value)
                },
                modifier = Modifier
                    .fillMaxWidth(0.9f),
                shape = RoundedCornerShape(
                    dimensionResource(id = R.dimen.offset_16)
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (selectedItem.value == -1) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.primaryContainer
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
                        color = if (selectedItem.value == -1) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onPrimaryContainer,
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
fun LanguageLevelPreview() {
    LanguageLevelContent({}, {})
}