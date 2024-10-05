package com.languages.tutordebug.ui.custom_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.languages.tutordebug.R
import com.languages.tutordebug.utils.fontDimensionResource

@Composable
fun BasicAuthTextField(
    state: MutableState<String>,
    startIcon: Int,
    hintText: Int,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    BasicTextField(
        modifier = modifier,
        value = state.value,
        singleLine = true,
        cursorBrush = Brush.verticalGradient(
            listOf(
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.primary
            )
        ),
        visualTransformation = visualTransformation,
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.primary,
            fontSize = fontDimensionResource(id = R.dimen.text_16)
        ),
        decorationBox = { innerTextField ->
            Card(
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.offset_12)),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.offset_12),
                            vertical = dimensionResource(id = R.dimen.offset_16)
                        ),
                ) {
                    Icon(
                        painter = painterResource(id = startIcon),
                        modifier = Modifier.size(dimensionResource(id = R.dimen.offset_20)),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.padding(3.dp))
                    if (state.value.isEmpty()) {
                        Text(
                            text = stringResource(id = hintText), style = TextStyle(
                                fontSize = fontDimensionResource(id = R.dimen.text_16),
                                color = MaterialTheme.colorScheme.secondary
                            )
                        )
                    }
                    innerTextField()
                }
            }
        },
        onValueChange = { value ->
            state.value = value
        })
}