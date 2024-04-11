package com.example.a40kcc.ui.screen

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.DataObject
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun DataScreen(
    data: DataObject,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column {
        Button(
            onClick = { navController.navigateUp() },
            modifier = modifier
        ) {
            Column {
                Text(text = stringResource(id = R.string.home_button))
            }
        }

        LazyColumn {
            items(items = data.getDataKeys().toList()) { key ->
                var showDetails by remember { mutableStateOf(false) }
                val onClick = {
                    showDetails = !showDetails
                }
                Text(
                    text = key,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = modifier
                        .clickable(enabled = true, onClick = onClick)
                        .fillMaxWidth()
                )

                if (showDetails) {
                    DataDetailScreen(
                        headers = data.getHeaders(),
                        details = data.getDataValue(key),
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@Composable
private fun DataDetailScreen(
    headers: Array<String>,
    details: Array<Any>?,
    modifier: Modifier = Modifier,
    imageSize: Dp = R.dimen.icon_image_size_small.dp
) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier.fillMaxWidth()) {
        for (header in headers) {
            Column(
                modifier = modifier
                    .wrapContentHeight()
            ) {
                Text(
                    text = header,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                )
            }
        }
    }

    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier.fillMaxWidth()) {
        if (details != null) {
            for (detail in details) {
                Column(
                    modifier = modifier
                        .wrapContentHeight()
                ) {
                    if (detail is Drawable) {
                        Image(
                            painter = rememberDrawablePainter(detail),
                            contentDescription = "Image",
                            contentScale = ContentScale.Inside,
                            modifier = Modifier
                                .height(imageSize)
                                .width(imageSize)
                        )
                    } else {
                        Text(
                            text = detail.toString(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}