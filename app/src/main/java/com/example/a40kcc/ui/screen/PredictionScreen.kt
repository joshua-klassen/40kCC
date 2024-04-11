package com.example.a40kcc.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.Prediction
import com.example.a40kcc.ui.utilities.PREDICTION_VIEW_MODEL

@Composable
fun PredictionScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        var addPrediction by remember { mutableStateOf(false) }
        Button(
            onClick = onBackClick,
            modifier = modifier
        ) {
            Column {
                Text(text = stringResource(id = R.string.home_button))
            }
        }

        val predictions: List<Prediction>? =
            PREDICTION_VIEW_MODEL.allPredictionsFlow.observeAsState().value

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier.fillMaxWidth()
        ) {
            Column(
                modifier = modifier
                    .alignByBaseline()
                    .wrapContentHeight()
            ) {
                Text(
                    text = "Prediction Name",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Column(
                modifier = modifier
                    .alignByBaseline()
                    .wrapContentHeight()
            ) {
                Text(
                    text = "Prediction Color",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        if (predictions != null) {
            PredictionScreen(predictions = predictions, modifier = modifier)
        }

        FloatingActionButton(
            onClick = {
                addPrediction = !addPrediction
            },
            modifier = modifier.align(Alignment.End)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Prediction")

            if (addPrediction) {
                AddPrediction(
                    modifier = modifier,
                    onDismissRequest = { addPrediction = !addPrediction }
                )
            }
        }
    }
}

@Composable
private fun PredictionScreen(
    predictions: List<Prediction>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(predictions) { prediction ->
            var showDetails by remember { mutableStateOf(false) }
            var removePrediction by remember { mutableStateOf(false) }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = modifier
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    Text(
                        text = prediction.name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                            .clickable(true, onClick = {
                                showDetails = !showDetails
                            })
                    )
                }
                Column(
                    modifier = modifier
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    Text(
                        text = prediction.color.toString(),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier.background(Color(prediction.color))
                    )
                }
                Column {
                    if (showDetails && !prediction.defaultOption) {
                        SmallFloatingActionButton(
                            onClick = {
                                removePrediction = !removePrediction
                            },
                            modifier = modifier.align(Alignment.End)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "Remove Prediction"
                            )

                            if (removePrediction) {
                                RemovePrediction(
                                    prediction = prediction,
                                    modifier = modifier,
                                    onDismissRequest = { removePrediction = !removePrediction }
                                )
                            }
                        }
                    }
                }
            }

            if (showDetails) {
                PredictionDetailScreen(
                    prediction = prediction,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
private fun PredictionDetailScreen(
    prediction: Prediction,
    modifier: Modifier = Modifier
) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                text = "Minimum Points",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = prediction.minPoints.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                text = "Maximum Points",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = prediction.maxPoints.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun AddPrediction(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    var predictionName by remember { mutableStateOf("") }
    var predictionColor by remember { mutableLongStateOf(0xFF000000) }
    var predictionMin by remember { mutableIntStateOf(0) }
    var predictionMax by remember { mutableIntStateOf(0) }
    val onConfirmation = {
        val newPrediction = Prediction(
            name = predictionName,
            color = predictionColor,
            minPoints = predictionMin,
            maxPoints = predictionMax
        )
        PREDICTION_VIEW_MODEL.insert(newPrediction)
        onDismissRequest()
    }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = modifier.wrapContentSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxWidth()
            ) {
                Row {
                    Text(
                        text = "Add a new prediction"
                    )
                }
                Row {
                    TextField(
                        value = predictionName,
                        onValueChange = { predictionName = it },
                        label = { Text(text = "Name:") },
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                }
                Row {
                    TextField(
                        value = predictionColor.toString(),
                        onValueChange = { predictionColor = it.toLong() },
                        label = { Text(text = "Color:") },
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                }
                Row {
                    TextField(
                        value = predictionMin.toString(),
                        onValueChange = { predictionMin = it.toInt() },
                        label = { Text(text = "Minimum Points:") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                }
                Row {
                    TextField(
                        value = predictionMax.toString(),
                        onValueChange = { predictionMax = it.toInt() },
                        label = { Text(text = "Maximum Points:") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                }
                Row {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = modifier
                    ) {
                        Text(
                            text = "Cancel",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = modifier
                    ) {
                        Text(
                            text = "Add",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RemovePrediction(
    prediction: Prediction,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    val onConfirmation = {
        PREDICTION_VIEW_MODEL.delete(prediction)
        onDismissRequest()
    }
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = modifier.wrapContentSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxWidth()
            ) {
                Row {
                    Text(
                        text = "Confirm remove prediction",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Row {
                    Text(
                        text = "Prediction Name: " + prediction.name,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                Row {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = modifier
                    ) {
                        Text(
                            text = "Cancel",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = modifier
                    ) {
                        Text(
                            text = "Confirm",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}