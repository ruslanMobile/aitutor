package com.languages.tutordebug.ui.custom_ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
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
import com.languages.tutordebug.R
import com.languages.tutordebug.utils.COLOR_ALPHA_50_PERCENTS
import com.languages.tutordebug.utils.fontDimensionResource

@Composable
fun SettingItem(
    icon: Int,
    title: Int,
    value: String?,
    isBottomLine: Boolean = true,
    openSettingScreenFunc: () -> Unit
) {
    Column(
        modifier = Modifier.clickable {
            openSettingScreenFunc.invoke()
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.offset_20),
                    vertical = dimensionResource(id = R.dimen.offset_12)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                tint = Color.Unspecified,
                contentDescription = "",
                modifier = Modifier.size(dimensionResource(id = R.dimen.offset_26))
            )

            Text(
                text = stringResource(id = title),
                style = TextStyle(
                    fontSize = fontDimensionResource(id = R.dimen.text_16),
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontFamily = FontFamily(Font(R.font.lato_bold)),
                    textAlign = TextAlign.Center,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = dimensionResource(id = R.dimen.offset_12))
            )
            Spacer(modifier = Modifier.weight(1.5f))
            Text(
                text = value ?: "",
                style = TextStyle(
                    fontSize = fontDimensionResource(id = R.dimen.text_14),
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(
                        COLOR_ALPHA_50_PERCENTS
                    ),
                    fontFamily = FontFamily(Font(R.font.lato_bold_italic)),
                    textAlign = TextAlign.End
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(end = dimensionResource(id = R.dimen.offset_4))
                    .weight(2f)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                contentDescription = "",
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.offset_20))
                    .rotate(180f)
            )
        }
        if (isBottomLine) {
            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(id = R.dimen.offset_1)),
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
    }
}

@Preview
@Composable
fun SettingItemPreview() {
    SettingItem(
        R.drawable.ic_baby,
        R.string.label_native_language_2,
        "Spanish"
    ){}
}