package com.example.a40kcc.ui.object_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.OUTCOME_VIEW_MODEL
import com.example.a40kcc.PLAYER_VIEW_MODEL
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.data.`object`.Outcome
import com.example.a40kcc.data.`object`.OutcomeWithPlayers
import com.example.a40kcc.ui.utilities.ComposeData
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.ScaledText
import kotlin.math.abs

class OutcomeCompose : CoreObjectCompose {
    @Composable
    override fun AddObject(
        composeData: ComposeData,
        onDismissRequest: () -> Unit
    ) {
        val modifier = composeData.modifier
        var player01ID by remember { mutableIntStateOf(0) }
        var player01Index by remember { mutableIntStateOf(0) }
        var player01Points by remember { mutableStateOf("0") }
        var player02Points by remember { mutableStateOf("0") }
        var pointDifferential by remember { mutableIntStateOf(0) }
        var player01TeamPoints by remember { mutableIntStateOf(10) }
        var player02TeamPoints by remember { mutableIntStateOf(10) }
        val playerNames: MutableList<String> = mutableListOf("")

        PLAYER_VIEW_MODEL.allPlayers().forEach { player ->
            playerNames += player.name
        }

        val onConfirmation = {
            val newOutcome = Outcome(
                player01ID = player01ID,
                player01Points = player01Points.toInt(),
                player02Points = player02Points.toInt(),
                player01TeamPoints = player01TeamPoints,
                player02TeamPoints = player02TeamPoints,
                pointDifferential = pointDifferential
            )
            OUTCOME_VIEW_MODEL.insert(
                newOutcome,
                this.getExceptionHandler(
                    errorMessage = "Error adding the new outcome",
                    composeData = composeData,
                    continueRun = true
                )
            )
            onDismissRequest()
        }

        fun calculatePoints() {
            if (player01Points.toIntOrNull() == null || player02Points.toIntOrNull() == null) {
                return
            }

            val differential = player01Points.toInt() - player02Points.toInt()
            val wtcPoints = (abs(differential) - 1) / 5

            if (differential < 0) {
                player02TeamPoints = 10 + wtcPoints
                player01TeamPoints = 10 - wtcPoints
            } else {
                player01TeamPoints = 10 + wtcPoints
                player02TeamPoints = 10 - wtcPoints
            }

            if (player01TeamPoints > 20) player01TeamPoints = 20
            if (player02TeamPoints > 20) player02TeamPoints = 20
            if (player01TeamPoints < 0) player01TeamPoints = 0
            if (player02TeamPoints < 0) player02TeamPoints = 0

            pointDifferential = abs(differential)
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
                            text = "Add a new outcome"
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = playerNames,
                            selectedIndex = player01Index,
                            modifier = modifier,
                            preText = "Player 01: ",
                            onItemClick = { index ->
                                player01Index = index
                                if (index != 0) {
                                    player01ID =
                                        PLAYER_VIEW_MODEL.getByName(playerNames[player01Index]).playerID
                                }
                            })
                    }
                    Row {
                        TextField(
                            value = player01Points,
                            onValueChange = { player01Points = it; calculatePoints() },
                            label = { Text(text = "Player 01 Points: ") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            textStyle = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row {
                        TextField(
                            value = player02Points,
                            onValueChange = { player02Points = it; calculatePoints() },
                            label = { Text(text = "Player 02 Points: ") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            textStyle = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                        Column(modifier = modifier.width(100.dp)) {
                            ScaledText(
                                text = "Point Differential: $pointDifferential",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = modifier
                            )
                        }
                        Column(modifier = modifier.width(100.dp)) {
                            ScaledText(
                                text = "Player 01 WTC Points: $player01TeamPoints",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = modifier
                            )
                        }
                        Column(modifier = modifier.width(100.dp)) {
                            ScaledText(
                                text = "Player 02 WTC Points: $player02TeamPoints",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = modifier
                            )
                        }
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
    override fun EditObject(
        coreObject: CoreObject,
        composeData: ComposeData,
        onDismissRequest: () -> Unit
    ) {
        val modifier = composeData.modifier
        val outcome: OutcomeWithPlayers = coreObject as OutcomeWithPlayers
        var player01ID by remember { mutableIntStateOf(outcome.outcome.player01ID) }
        var player01Index by remember { mutableIntStateOf(0) }
        var player01Points by remember { mutableStateOf(outcome.outcome.player01Points.toString()) }
        var player02Points by remember { mutableStateOf(outcome.outcome.player02Points.toString()) }
        var pointDifferential by remember { mutableIntStateOf(outcome.outcome.pointDifferential) }
        var player01TeamPoints by remember { mutableIntStateOf(outcome.outcome.player01TeamPoints) }
        var player02TeamPoints by remember { mutableIntStateOf(outcome.outcome.player02TeamPoints) }
        val playerNames: MutableList<String> = mutableListOf("")

        PLAYER_VIEW_MODEL.allPlayers().forEach { player ->
            playerNames += player.name
            if (player.playerID == player01ID) {
                player01Index = playerNames.lastIndex
            }
        }

        val onConfirmation = {
            val newOutcome = Outcome(
                outcomeID = outcome.outcome.outcomeID,
                player01ID = player01ID,
                player01Points = player01Points.toInt(),
                player02Points = player02Points.toInt(),
                player01TeamPoints = pointDifferential,
                player02TeamPoints = pointDifferential,
                pointDifferential = pointDifferential
            )
            OUTCOME_VIEW_MODEL.update(
                newOutcome,
                this.getExceptionHandler(
                    errorMessage = "Error updating the outcome",
                    composeData = composeData,
                    continueRun = true
                )
            )
            onDismissRequest()
        }

        fun calculatePoints() {
            if (player01Points.toIntOrNull() == null || player02Points.toIntOrNull() == null) {
                return
            }

            val differential = player01Points.toInt() - player02Points.toInt()
            val wtcPoints = (abs(differential) - 1) / 5

            if (differential < 0) {
                player02TeamPoints = 10 + wtcPoints
                player01TeamPoints = 10 - wtcPoints
            } else {
                player01TeamPoints = 10 + wtcPoints
                player02TeamPoints = 10 - wtcPoints
            }
            pointDifferential = abs(differential)
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
                            text = "Add a new outcome"
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = playerNames,
                            selectedIndex = player01Index,
                            modifier = modifier,
                            preText = "Player 01: ",
                            onItemClick = { index ->
                                player01Index = index
                                if (index != 0) {
                                    player01ID =
                                        PLAYER_VIEW_MODEL.getByName(playerNames[player01Index]).playerID
                                }
                            })
                    }
                    Row {
                        TextField(
                            value = player01Points,
                            onValueChange = { player01Points = it; calculatePoints() },
                            label = { Text(text = "Player 01 Points: ") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            textStyle = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row {
                        TextField(
                            value = player02Points,
                            onValueChange = { player02Points = it; calculatePoints() },
                            label = { Text(text = "Player 02 Points: ") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            textStyle = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row {
                        TextField(
                            value = pointDifferential.toString(),
                            readOnly = true,
                            enabled = false,
                            onValueChange = { },
                            label = { Text(text = "Point Differential: ") },
                            textStyle = MaterialTheme.typography.bodyMedium,
                            modifier = modifier
                        )
                        TextField(
                            value = player01TeamPoints.toString(),
                            readOnly = true,
                            enabled = false,
                            onValueChange = { },
                            label = { Text(text = "Player 01 WTC Points: ") },
                            textStyle = MaterialTheme.typography.bodyMedium,
                            modifier = modifier
                        )
                        TextField(
                            value = player02TeamPoints.toString(),
                            readOnly = true,
                            enabled = false,
                            onValueChange = { },
                            label = { Text(text = "Player 02 WTC Points: ") },
                            textStyle = MaterialTheme.typography.bodyMedium,
                            modifier = modifier
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
                                text = "Update",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }
        }
    }

    override fun canRemove(coreObject: CoreObject): Boolean {
        return false
    }
}