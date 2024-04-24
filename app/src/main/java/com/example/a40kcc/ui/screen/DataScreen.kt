package com.example.a40kcc.ui.screen

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.DataObject
import com.example.a40kcc.ui.utilities.ScaledText
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun DataScreen(
    data: DataObject,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageWidth: Dp = R.dimen.icon_image_size_default.dp,
    imageHeight: Dp = R.dimen.icon_image_size_default.dp
) {
    Scaffold(topBar = {
        Button(
            onClick = onBackClick,
            modifier = modifier
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.home_button),
                    modifier = modifier
                )
            }
        }
    }) { paddingValues ->
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            data.getDataKeys().forEach { key ->
                var showDetails by remember { mutableStateOf(false) }
                var clickable by remember { mutableStateOf(true) }

                if (data.getDataValue(key) == null || data.getDataValue(key)!!.isEmpty()) {
                    clickable = false
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable(enabled = clickable, onClick = {
                            showDetails = !showDetails
                        })
                ) {
                    Column(
                        modifier = modifier
                            .alignByBaseline()
                            .wrapContentHeight()
                    ) {
                        ScaledText(
                            text = key,
                            style = MaterialTheme.typography.titleLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }

                if (showDetails) {
                    data.getDataValue(key)?.let {
                        println(data.getHeaders().size)
                        if (data.getHeaders().size > 1) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(ScrollState(initial = 1))
                            ) {
                                DataDetailScreen(
                                    headers = data.getHeaders(),
                                    details = it,
                                    modifier = modifier,
                                    imageWidth = imageWidth,
                                    imageHeight = imageHeight
                                )
                            }
                        } else {
                            Row(
                                horizontalArrangement = Arrangement.SpaceEvenly,
                                modifier = modifier
                                    .fillMaxWidth()
                            ) {
                                DataDetailScreen(
                                    headers = data.getHeaders(),
                                    details = it,
                                    modifier = modifier,
                                    imageWidth = imageWidth,
                                    imageHeight = imageHeight
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DataDetailScreen(
    headers: Array<String>,
    details: Array<Any>,
    modifier: Modifier = Modifier,
    imageWidth: Dp = R.dimen.icon_image_size_default.dp,
    imageHeight: Dp = R.dimen.icon_image_size_default.dp
) {
    var arrayCounter = 0
    headers.forEach { header ->
        Column(
            modifier = modifier
                .wrapContentHeight()
        ) {
            ScaledText(
                text = header,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            val detail = details[arrayCounter]
            if (detail is Drawable) {
                Image(
                    painter = rememberDrawablePainter(detail),
                    contentDescription = "Image",
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .height(imageHeight)
                        .width(imageWidth)
                )
            } else {
                ScaledText(
                    text = detail.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center,
                    softWrap = true,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
        arrayCounter++
    }
}