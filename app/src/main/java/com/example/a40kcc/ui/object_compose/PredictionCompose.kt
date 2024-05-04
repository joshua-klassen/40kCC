package com.example.a40kcc.ui.object_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.COLORS
import com.example.a40kcc.PREDICTION_VIEW_MODEL
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.data.`object`.Prediction
import com.example.a40kcc.ui.utilities.ComposeData
import com.example.a40kcc.ui.utilities.ScaledText

class PredictionCompose : CoreObjectCompose {
    @Composable
    override fun AddObject(
        composeData: ComposeData,
        onDismissRequest: () -> Unit
    ) {
        val modifier = composeData.modifier
        var predictionName by remember { mutableStateOf("") }
        var predictionColor by remember { mutableLongStateOf(COLORS.getValue(key = "Black")) }
        var predictionMin by remember { mutableStateOf("") }
        var predictionMax by remember { mutableStateOf("") }

        val onConfirmation = {
            val newPrediction = Prediction(
                name = predictionName,
                color = predictionColor,
                minPoints = predictionMin.toIntOrNull() ?: 0,
                maxPoints = predictionMax.toIntOrNull() ?: 0
            )
            PREDICTION_VIEW_MODEL.insert(
                newPrediction,
                this.getExceptionHandler(
                    errorMessage = "Error adding new prediction $predictionName",
                    composeData = composeData,
                    continueRun = true
                )
            )
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
                            label = { Text(text = "Name: ") },
                            textStyle = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row {
                        TextField(
                            value = predictionColor.toString(),
                            onValueChange = { predictionColor = it.toLong() },
                            label = { Text(text = "Color: ") },
                            textStyle = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row {
                        TextField(
                            value = predictionMin,
                            onValueChange = { predictionMin = it },
                            label = { Text(text = "Minimum Points: ") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            textStyle = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row {
                        TextField(
                            value = predictionMax,
                            onValueChange = { predictionMax = it },
                            label = { Text(text = "Maximum Points: ") },
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

    override fun canEdit(coreObject: CoreObject): Boolean {
        val prediction: Prediction = coreObject as Prediction

        return !prediction.defaultOption
    }

    @Composable
    override fun RemoveObject(
        coreObject: CoreObject,
        composeData: ComposeData,
        onDismissRequest: () -> Unit
    ) {
        val modifier = composeData.modifier
        val prediction: Prediction = coreObject as Prediction

        val onConfirmation = {
            PREDICTION_VIEW_MODEL.delete(
                prediction,
                this.getExceptionHandler(
                    errorMessage = "Error removing prediction ${prediction.name}",
                    composeData = composeData,
                    continueRun = true
                )
            )
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
                        ScaledText(
                            text = "Confirm remove prediction",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Row {
                        ScaledText(
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
}