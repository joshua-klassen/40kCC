package com.example.a40kcc.ui.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.ui.object_compose.CoreObjectCompose
import com.example.a40kcc.ui.utilities.ScaledText

@Composable
fun ObjectScreen(
    objectList: List<CoreObject>,
    onBackClick: () -> Unit,
    objectCompose: CoreObjectCompose,
    modifier: Modifier = Modifier,
    columnWidth: Dp = 100.dp
) {
    var addObject by remember { mutableStateOf(false) }
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
    },
        bottomBar = {
            if (objectCompose.canAdd()) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    FloatingActionButton(
                        onClick = {
                            addObject = !addObject
                        },
                        modifier = modifier.align(Alignment.End)
                    ) {
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")

                        if (addObject) {
                            objectCompose.AddObject(
                                modifier = modifier,
                                onDismissRequest = { addObject = !addObject }
                            )
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            objectList.forEach { coreObject ->
                var editObject by remember { mutableStateOf(false) }
                var removeObject by remember { mutableStateOf(false) }
                var showDetails by remember { mutableStateOf(false) }
                var clickable by remember { mutableStateOf(true) }

                if (coreObject.getDetailColumns().isEmpty()) {
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
                    coreObject.getCoreColumns().keys.forEach { data ->
                        Column(
                            modifier = modifier
                                .width(columnWidth)
                                .alignByBaseline()
                                .wrapContentHeight()
                        ) {
                            ScaledText(
                                text = data,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = modifier,
                                textAlign = TextAlign.Center
                            )
                            coreObject.getCoreColumns()[data]?.let {
                                ScaledText(
                                    text = it,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = modifier,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }

                if (showDetails) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = modifier
                            .fillMaxWidth()
                            .horizontalScroll(ScrollState(initial = 1))
                    ) {
                        ObjectDetailsScreen(
                            coreObject = coreObject,
                            modifier = modifier,
                            columnWidth = columnWidth
                        )

                        if (objectCompose.canEdit(coreObject = coreObject)) {
                            Column(
                                modifier = modifier
                                    .wrapContentHeight()
                            ) {
                                SmallFloatingActionButton(
                                    onClick = {
                                        editObject = !editObject
                                    },
                                    modifier = modifier.align(Alignment.End)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Build,
                                        contentDescription = "Edit",
                                        modifier = modifier
                                    )

                                    if (editObject) {
                                        objectCompose.EditObject(
                                            coreObject = coreObject,
                                            modifier = modifier,
                                            onDismissRequest = { editObject = !editObject }
                                        )
                                    }
                                }
                            }
                        }

                        if (objectCompose.canRemove(coreObject = coreObject)) {
                            Column(
                                modifier = modifier
                                    .wrapContentHeight()
                            ) {
                                SmallFloatingActionButton(
                                    onClick = {
                                        removeObject = !removeObject
                                    },
                                    modifier = modifier.align(Alignment.End)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Clear,
                                        contentDescription = "Remove",
                                        modifier = modifier
                                    )

                                    if (removeObject) {
                                        objectCompose.RemoveObject(
                                            coreObject = coreObject,
                                            modifier = modifier,
                                            onDismissRequest = { removeObject = !removeObject }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ObjectDetailsScreen(
    coreObject: CoreObject,
    modifier: Modifier = Modifier,
    columnWidth: Dp = 100.dp
) {
    coreObject.getDetailColumns().keys.forEach { data ->
        Column(
            modifier = modifier
                .width(columnWidth)
                .wrapContentHeight()
        ) {
            ScaledText(
                text = data,
                style = MaterialTheme.typography.titleMedium,
                modifier = modifier,
                textAlign = TextAlign.Center
            )
            coreObject.getDetailColumns()[data]?.let {
                ScaledText(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = modifier,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}