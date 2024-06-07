package com.example.a40kcc.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import com.example.a40kcc.GAME_VIEW_MODEL
import com.example.a40kcc.OUTCOME_VIEW_MODEL
import com.example.a40kcc.PREDICTION_VIEW_MODEL
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.data.`object`.Game
import com.example.a40kcc.data.`object`.GameExpanded
import com.example.a40kcc.data.`object`.Outcome
import com.example.a40kcc.data.`object`.Prediction
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.ErrorHandling
import com.example.a40kcc.ui.utilities.ScaledText
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun EditGame(
    coreObject: CoreObject,
    modifier: Modifier,
    onDismissRequest: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val errorHandling = remember {
        ErrorHandling(
            snackbarHostState = snackbarHostState,
            modifier = modifier
        )
    }

    val game: GameExpanded = coreObject as GameExpanded
    var addOutcome: Boolean by remember { mutableStateOf(false) }

    val predictions: List<Prediction> = PREDICTION_VIEW_MODEL.allPredictions()
    var predictionID by remember {
        if (game.game.predictionID != null) {
            mutableIntStateOf(game.game.predictionID)
        } else {
            mutableIntStateOf(0)
        }
    }
    var outcomeID by remember {
        if (game.game.outcomeID != null) {
            mutableIntStateOf(game.game.outcomeID)
        } else {
            mutableIntStateOf(0)
        }
    }

    var predictionIndex by remember { mutableIntStateOf(0) }

    if (predictions.contains(game.prediction)) {
        predictionIndex = predictions.indexOf(game.prediction)
    }

    val onConfirmation = {
        var outcomeId: Int? = outcomeID
        if (outcomeId == 0) {
            outcomeId = null
        }

        val updatedGame = Game(
            gameID = game.game.gameID,
            player01ID = game.game.player01ID,
            player01FactionName = game.game.player01FactionName,
            player02FactionName = game.game.player02FactionName,
            predictionID = predictionID,
            roundID = game.game.roundID,
            outcomeID = outcomeId
        )

        errorHandling.provideCoroutineExceptionScope(
            errorMessage = "Error updating the game: ${game.getDisplayName()}"
        ).launch {
            GAME_VIEW_MODEL.update(
                game = updatedGame
            )
            onDismissRequest()
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            Button(
                onClick = { onDismissRequest() },
                modifier = modifier
            ) {
                Column {
                    Text(
                        text = "Cancel",
                        modifier = modifier
                    )
                }
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                FloatingActionButton(
                    onClick = { onConfirmation() },
                    modifier = modifier.align(Alignment.End)
                ) {
                    Text(
                        text = "Update",
                        modifier = modifier
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                ScaledText(
                    text = "Game Data",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = modifier
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                ScaledText(
                    text = "Player 01: ${game.player01?.player?.getDisplayName() ?: ""}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = modifier
                )
                ScaledText(
                    text = "Round: ${game.round?.getDisplayName() ?: ""}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = modifier
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                ScaledText(
                    text = "Player 02 Faction: ${game.game.player02FactionName}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = modifier
                )
                ScaledText(
                    text = "Player 01 Faction: ${game.game.player01FactionName}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = modifier
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                ScaledText(
                    text = "Update",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = modifier
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                DropDownList(
                    itemList = predictions.map { it.getDisplayName() },
                    selectedIndex = predictionIndex,
                    modifier = modifier,
                    preText = "Prediction: ",
                    onItemClick = { index ->
                        predictionIndex = index
                        predictionID = predictions[predictionIndex].predictionID
                    }
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                if (outcomeID != 0) {
                    ScaledText(
                        text = "Outcome: ${OUTCOME_VIEW_MODEL.getById(outcomeID).getDisplayName()}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = modifier
                    )
                }
                TextButton(
                    onClick = {
                        addOutcome = !addOutcome
                    },
                    modifier = modifier
                ) {
                    ScaledText(
                        text = "Add Outcome",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = modifier
                    )
                }

                if (addOutcome) {
                    val insertOutcome: (outcome: Outcome?) -> Unit = { outcome ->
                        if (outcome != null) {
                            errorHandling.provideCoroutineExceptionScope(
                                errorMessage = "Error adding the new outcome for: ${game.getDisplayName()}"
                            ).launch {
                                OUTCOME_VIEW_MODEL.insert(outcome)
                                outcomeID =
                                    OUTCOME_VIEW_MODEL.getByPlayerId(game.game.player01ID)
                                        .last().outcomeID
                            }
                        } else {
                            addOutcome = !addOutcome
                        }
                    }
                    AddOutcome(
                        playerId = game.game.player01ID,
                        modifier = modifier,
                        onDismissRequest = insertOutcome
                    )
                }
            }
        }
    }
}

@Composable
private fun AddOutcome(
    playerId: Int,
    modifier: Modifier,
    onDismissRequest: (outcome: Outcome?) -> Unit
) {
    var player01Points by remember { mutableStateOf("0") }
    var player02Points by remember { mutableStateOf("0") }
    var pointDifferential by remember { mutableIntStateOf(0) }
    var player01TeamPoints by remember { mutableIntStateOf(10) }
    var player02TeamPoints by remember { mutableIntStateOf(10) }

    val onConfirmation = {
        val newOutcome = Outcome(
            player01ID = playerId,
            player01Points = player01Points.toInt(),
            player02Points = player02Points.toInt(),
            player01TeamPoints = player01TeamPoints,
            player02TeamPoints = player02TeamPoints,
            pointDifferential = pointDifferential
        )
        onDismissRequest(newOutcome)
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