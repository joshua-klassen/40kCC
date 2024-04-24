package com.example.a40kcc.ui.utilities

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.isUnspecified

@Composable
fun ScaledText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    textAlign: TextAlign = TextAlign.Left,
    color: Color = style.color,
    softWrap: Boolean = false
) {
    var resizedTextStyle by remember { mutableStateOf(style) }
    var draw by remember { mutableStateOf(false) }

    val defaultFontSize = style.fontSize

    Text(
        text = text,
        color = color,
        modifier = modifier.drawWithContent {
            if (draw) {
                drawContent()
            }
        },
        softWrap = softWrap,
        style = resizedTextStyle,
        textAlign = textAlign,
        onTextLayout = { result ->
            if (result.didOverflowWidth) {
                if (style.fontSize.isUnspecified) {
                    resizedTextStyle = resizedTextStyle.copy(
                        fontSize = defaultFontSize
                    )
                }
                resizedTextStyle = resizedTextStyle.copy(
                    fontSize = resizedTextStyle.fontSize * 0.98
                )
            } else {
                draw = true
            }
        }
    )
}