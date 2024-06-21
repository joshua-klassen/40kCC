package com.example.a40kcc.ui.screen

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.data.`object`.Outcome
import com.example.a40kcc.ui.utilities.ScaledText
import kotlin.math.abs

@Composable
fun AddOutcome(
    player01Id: Int,
    player02Id: Int?,
    modifier: Modifier,
    onDismissRequest: (outcome: Outcome?) -> Unit
) {
    var player01Points by remember { mutableStateOf("0") }
    var player02Points by remember { mutableStateOf("0") }
    var pointDifferential by remember { mutableIntStateOf(0) }
    var player01TeamPoints by remember { mutableIntStateOf(10) }
    var player02TeamPoints by remember { mutableIntStateOf(10) }

    fun calculatePoints() {
        val differential = (player01Points.toIntOrNull() ?: 0) - (player02Points.toIntOrNull() ?: 0)
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

    val onConfirmation = {
        val newOutcome = Outcome(
            player01ID = player01Id,
            player02ID = player02Id,
            player01Points = player01Points.toIntOrNull() ?: 0,
            player02Points = player02Points.toIntOrNull() ?: 0,
            player01TeamPoints = player01TeamPoints,
            player02TeamPoints = player02TeamPoints,
            pointDifferential = pointDifferential
        )
        onDismissRequest(newOutcome)
    }

    Dialog(onDismissRequest = { onDismissRequest(null) }) {
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
                        text = "Add a new outcome",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                    )
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
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Column(modifier = modifier.width(100.dp)) {
                        ScaledText(
                            text = "Player 01 WTC Points: $player01TeamPoints",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = modifier
                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
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
                        onClick = { onDismissRequest(null) },
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
fun EditOutcome(
    outcome: Outcome,
    modifier: Modifier,
    onDismissRequest: (outcome: Outcome) -> Unit
) {
    var player01Points by remember { mutableStateOf(outcome.player01Points.toString()) }
    var player02Points by remember { mutableStateOf(outcome.player02Points.toString()) }
    var pointDifferential by remember { mutableIntStateOf(outcome.pointDifferential) }
    var player01TeamPoints by remember { mutableIntStateOf(outcome.player01TeamPoints) }
    var player02TeamPoints by remember { mutableIntStateOf(outcome.player02TeamPoints) }

    fun calculatePoints() {
        val differential = (player01Points.toIntOrNull() ?: 0) - (player02Points.toIntOrNull() ?: 0)
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

    val onConfirmation = {
        val newOutcome = Outcome(
            outcomeID = outcome.outcomeID,
            player01ID = outcome.player01ID,
            player02ID = outcome.player02ID,
            player01Points = player01Points.toIntOrNull() ?: 0,
            player02Points = player02Points.toIntOrNull() ?: 0,
            player01TeamPoints = player01TeamPoints,
            player02TeamPoints = player02TeamPoints,
            pointDifferential = pointDifferential
        )
        onDismissRequest(newOutcome)
    }

    Dialog(onDismissRequest = { onDismissRequest(outcome) }) {
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
                        text = "Update outcome",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                    )
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
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Column(modifier = modifier.width(100.dp)) {
                        ScaledText(
                            text = "Player 01 WTC Points: $player01TeamPoints",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = modifier
                        )
                    }
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
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
                        onClick = { onDismissRequest(outcome) },
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