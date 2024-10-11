package com.languages.tutordebug.ui.screens.onboarding.native_language

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.languages.tutordebug.MainActivity
import com.languages.tutordebug.R
import com.languages.tutordebug.ui.LocalNavController
import com.languages.tutordebug.ui.model.Screen.AboutAppSecond
import com.languages.tutordebug.ui.model.Screen.UserName
import com.languages.tutordebug.ui.model.UserLanguage
import com.languages.tutordebug.ui.model.enums.UsersLanguageEnum
import com.languages.tutordebug.ui.screens.onboarding.about_app_second.AboutAppSecondVM
import com.languages.tutordebug.utils.fontDimensionResource
import com.languages.tutordebug.utils.languagesList
import java.util.Locale

@Composable
fun NativeLanguageScreen() {
    val navController = LocalNavController.current
    val userLanguage = rememberSaveable {
        mutableStateOf<UsersLanguageEnum?>(null)
    }
    val viewModel: NativeLanguageVM = hiltViewModel()
    val activity = LocalContext.current as MainActivity
    val context = LocalContext.current
    NativeLanguageContent({
        viewModel.storeUserNativeLanguage(userLanguage.value)
        navController.navigate(UserName.route)
        activity.setLocale(context)
        activity.recreate()
    }, {
        navController.popBackStack()
    }, {
        userLanguage.value = it.languageCode
    })
}

@Composable
fun NativeLanguageContent(
    funcNextScreen: () -> Unit,
    funcBack: () -> Unit,
    funcSelect: (UserLanguage) -> Unit
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
                    text = stringResource(id = R.string.label_number_out_of_number, "3", "3"),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Text(
                text = stringResource(id = R.string.label_native_language),
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
                text = stringResource(id = R.string.text_choose_your_native_language),
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

            val selectedItem = remember {
                mutableIntStateOf(-1)
            }

            LazyColumn(
                modifier = Modifier
                    .weight(11.5f)
                    .padding(horizontal = dimensionResource(id = R.dimen.offset_20))
            ) {
                itemsIndexed(items = languagesList) { index, item ->
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
                            funcSelect.invoke(item)
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
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(dimensionResource(id = R.dimen.offset_26)),
                                painter = painterResource(id = item.icon),
                                tint = Color.Unspecified,
                                contentDescription = ""
                            )
                            Text(
                                modifier = Modifier.padding(start = dimensionResource(id = R.dimen.offset_12)),
                                text = item.languageCode.languageName, style = TextStyle(
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
                        funcNextScreen.invoke()
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
fun NativeLanguagePreview() {
    NativeLanguageContent({}, {}, {})
}