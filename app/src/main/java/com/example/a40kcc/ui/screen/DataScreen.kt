package com.example.a40kcc.ui.screen

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.navigation.NavHostController
import com.example.a40kcc.data.`object`.DataObject
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun DataScreen(
    cardItems: Set<Any>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    data: DataObject? = null
) {
    Column {
        Button(
            onClick = { navController.navigateUp() },
            modifier = modifier
        ) {
            Column {
                Text("Back")
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(1)
        ) {
            items(cardItems.toList()) { key ->
                var showDetails by remember { mutableStateOf(false) }
                if (key is String) {
                    var onClick = {}
                    if (data != null) {
                        onClick = {
                            showDetails = !showDetails
                        }
                    }
                    Column {
                        Text(
                            key,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = modifier
                                .clickable(true, onClick = onClick)
                                .fillMaxWidth()
                        )

                        if (showDetails) {
                            DataDetailScreen(
                                data?.getHeaders(),
                                data?.getDataValue(key),
                                modifier
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DataDetailScreen(headers: Array<String>?, details: Array<Any>?, modifier: Modifier = Modifier) {
    Column {
        Row(modifier = modifier) {
            if (headers != null) {
                for (header in headers) {
                    Column(modifier = modifier) {
                        Text(
                            header,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }

        Row(modifier = modifier) {
            if (details != null) {
                for (detail in details) {
                    Column(modifier = modifier) {
                        if (detail is Drawable) {
                            Image(
                                painter = rememberDrawablePainter(detail),
                                contentDescription = "Image",
                                contentScale = ContentScale.Inside
                            )
                        } else {
                            Text(
                                detail.toString(),
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}