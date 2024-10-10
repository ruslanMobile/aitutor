package com.languages.tutordebug.ui.custom_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.languages.tutordebug.R
import com.languages.tutordebug.utils.fontDimensionResource

@Composable
fun UserFeedbackView(
    title: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .then(modifier)
            .height(dimensionResource(id = R.dimen.offset_88))
            .fillMaxWidth(0.9f)
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.offset_40))
            )
            .padding(vertical = dimensionResource(id = R.dimen.offset_4)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_rating_stars),
            modifier = Modifier
                .padding(start = dimensionResource(id = R.dimen.offset_20))
                .width(dimensionResource(id = R.dimen.offset_62)),
            contentDescription = "",
            tint = Color.Unspecified
        )

        Column(
            modifier = Modifier
                .padding(end = dimensionResource(id = R.dimen.offset_20))
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = fontDimensionResource(id = R.dimen.text_16),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontFamily = FontFamily(Font(R.font.lato_bold)),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .wrapContentHeight(Alignment.Bottom)
                    .padding(horizontal = dimensionResource(id = R.dimen.offset_26))
            )

            Text(
                text = text,
                style = TextStyle(
                    fontSize = fontDimensionResource(id = R.dimen.text_14),
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
                    fontFamily = FontFamily(Font(R.font.lato_bold)),
                    textAlign = TextAlign.Left
                ),
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .wrapContentHeight(Alignment.Bottom)
                    .padding(horizontal = dimensionResource(id = R.dimen.offset_26))
            )
        }
    }
}

@Preview
@Composable
fun UserFeedbackViewPreview() {
    UserFeedbackView(
        title = "Amanda O, USA",
        text = "I can't believe how REAL these photos look\uD83D\uDE31, I'm amazed. Now there's no need to spend time and money on photo shoots\uD83E\uDD70",
    )
}