package com.example.a40kcc.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.Outcome
import com.example.a40kcc.data.`object`.OutcomeExpanded
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.OUTCOME_VIEW_MODEL
import com.example.a40kcc.ui.utilities.PLAYER_VIEW_MODEL
import kotlin.math.abs

@Composable
fun OutcomeScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        var addOutcome by remember { mutableStateOf(false) }
        Button(
            onClick = onBackClick,
            modifier = modifier
        ) {
            Column {
                Text(stringResource(id = R.string.home_button))
            }
        }

        val outcomes: List<OutcomeExpanded>? =
            OUTCOME_VIEW_MODEL.allOutcomesExpanded.observeAsState().value

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
                    "Player 01",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Column(
                modifier = modifier
                    .alignByBaseline()
                    .wrapContentHeight()
            ) {
                Text(
                    "Player 02",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Column(
                modifier = modifier
                    .alignByBaseline()
                    .wrapContentHeight()
            ) {
                Text(
                    "WTC Points",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        if (outcomes != null) {
            OutcomeScreen(
                outcomes, modifier
            )
        }

        FloatingActionButton(
            onClick = {
                addOutcome = !addOutcome
            },
            modifier = modifier.align(Alignment.End)
        ) {
            Icon(Icons.Filled.Add, "Add Outcome")

            if (addOutcome) {
                AddOutcome(
                    modifier
                ) { addOutcome = !addOutcome }
            }
        }
    }
}

@Composable
private fun OutcomeScreen(
    outcomes: List<OutcomeExpanded>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(outcomes) { outcome ->
            var showDetails by remember { mutableStateOf(false) }
            var editOutcome by remember { mutableStateOf(false) }
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
                        outcome.player01.name,
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
                    if (outcome.player02 != null) {
                        Text(
                            text = outcome.player02.name,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = modifier
                        )
                    }
                }
                Column(
                    modifier = modifier
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    Text(
                        outcome.outcome.pointDifferential.toString(),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                    )
                }
                Column {
                    if (showDetails) {
                        SmallFloatingActionButton(
                            onClick = {
                                editOutcome = !editOutcome
                            },
                            modifier = modifier.align(Alignment.End)
                        ) {
                            Icon(Icons.Filled.Build, "Edit Outcome")

                            if (editOutcome) {
                                EditOutcome(
                                    outcome,
                                    modifier
                                ) { editOutcome = !editOutcome }
                            }
                        }
                    }
                }
            }

            if (showDetails) {
                OutcomeDetailScreen(
                    outcome,
                    modifier
                )
            }
        }
    }
}

@Composable
private fun OutcomeDetailScreen(
    outcome: OutcomeExpanded,
    modifier: Modifier = Modifier
) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                "Player 01 Points",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                outcome.outcome.player01Points.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                "Player 01 WTC Points",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                outcome.outcome.player01TeamPoints.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                "Player 02 Points",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                outcome.outcome.player02Points.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                "Player 02 WTC Points",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                outcome.outcome.player02TeamPoints.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun AddOutcome(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    var player01ID by remember { mutableIntStateOf(0) }
    var player01Index by remember { mutableIntStateOf(0) }
    var player01Points by remember { mutableStateOf("0") }
    var player02Points by remember { mutableStateOf("0") }
    var pointDifferential by remember { mutableIntStateOf(0) }
    var player01TeamPoints by remember { mutableIntStateOf(10) }
    var player02TeamPoints by remember { mutableIntStateOf(10) }
    val playerNames: MutableList<String> = mutableListOf("")
    PLAYER_VIEW_MODEL.allPlayers.value?.forEach {
        playerNames += it.name
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
        OUTCOME_VIEW_MODEL.insert(newOutcome)
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
                        preText = "Player 01:",
                        onItemClick = {
                            player01Index = it; player01ID =
                            PLAYER_VIEW_MODEL.getByName(playerNames[player01Index]).value?.playerID
                                ?: 0
                        })
                }
                Row {
                    TextField(
                        value = player01Points,
                        onValueChange = {
                            player01Points = it; calculatePoints()
                        },
                        label = { Text("Player 01 Points:") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                }
                Row {
                    TextField(
                        value = player02Points,
                        onValueChange = { player02Points = it; calculatePoints() },
                        label = { Text("Player 02 Points:") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                }
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Column(modifier = modifier.width(100.dp)) {
                        TextField(
                            value = pointDifferential.toString(),
                            readOnly = true,
                            enabled = false,
                            onValueChange = { },
                            label = { Text("Point Differential:") },
                            textStyle = MaterialTheme.typography.bodyMedium,
                            modifier = modifier
                        )
                    }
                    Column(modifier = modifier.width(100.dp)) {
                        TextField(
                            value = player01TeamPoints.toString(),
                            readOnly = true,
                            enabled = false,
                            onValueChange = { },
                            label = { Text("Player 01 WTC Points:") },
                            textStyle = MaterialTheme.typography.bodyMedium,
                            modifier = modifier
                        )
                    }
                    Column(modifier = modifier.width(100.dp)) {
                        TextField(
                            value = player02TeamPoints.toString(),
                            readOnly = true,
                            enabled = false,
                            onValueChange = { },
                            label = { Text("Player 02 WTC Points:") },
                            textStyle = MaterialTheme.typography.bodyMedium,
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
                            "Cancel",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = modifier
                    ) {
                        Text(
                            "Add",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun EditOutcome(
    outcome: OutcomeExpanded,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    var player01ID by remember { mutableIntStateOf(outcome.outcome.player01ID) }
    var player01Index by remember { mutableIntStateOf(0) }
    var player01Points by remember { mutableStateOf(outcome.outcome.player01Points.toString()) }
    var player02Points by remember { mutableStateOf(outcome.outcome.player02Points.toString()) }
    var pointDifferential by remember { mutableIntStateOf(outcome.outcome.pointDifferential) }
    var player01TeamPoints by remember { mutableIntStateOf(outcome.outcome.player01TeamPoints) }
    var player02TeamPoints by remember { mutableIntStateOf(outcome.outcome.player02TeamPoints) }
    val playerNames: MutableList<String> = mutableListOf("")
    PLAYER_VIEW_MODEL.allPlayers.value?.forEach {
        playerNames += it.name
        if (it.playerID == player01ID) {
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
        OUTCOME_VIEW_MODEL.update(newOutcome)
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
                        preText = "Player 01:",
                        onItemClick = {
                            player01Index = it; player01ID =
                            PLAYER_VIEW_MODEL.getByName(playerNames[player01Index]).value?.playerID
                                ?: 0
                        })
                }
                Row {
                    TextField(
                        value = player01Points,
                        onValueChange = { player01Points = it; calculatePoints() },
                        label = { Text("Player 01 Points:") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                }
                Row {
                    TextField(
                        value = player02Points,
                        onValueChange = { player02Points = it; calculatePoints() },
                        label = { Text("Player 02 Points:") },
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
                        label = { Text("Point Differential:") },
                        textStyle = MaterialTheme.typography.bodyMedium,
                        modifier = modifier
                    )
                    TextField(
                        value = player01TeamPoints.toString(),
                        readOnly = true,
                        enabled = false,
                        onValueChange = { },
                        label = { Text("Player 01 WTC Points:") },
                        textStyle = MaterialTheme.typography.bodyMedium,
                        modifier = modifier
                    )
                    TextField(
                        value = player02TeamPoints.toString(),
                        readOnly = true,
                        enabled = false,
                        onValueChange = { },
                        label = { Text("Player 02 WTC Points:") },
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
                            "Cancel",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = modifier
                    ) {
                        Text(
                            "Update",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}