package com.example.a40kcc.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.a40kcc.GAME_EXPANDED_VIEW_MODEL
import com.example.a40kcc.GAME_VIEW_MODEL
import com.example.a40kcc.HISTORICAL_ROUND_DATA_VIEW_MODEL
import com.example.a40kcc.LIVE_ROUND_EXPANDED_VIEW_MODEL
import com.example.a40kcc.OUTCOME_VIEW_MODEL
import com.example.a40kcc.PREDICTION_VIEW_MODEL
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.data.`object`.Game
import com.example.a40kcc.data.`object`.GameExpanded
import com.example.a40kcc.data.`object`.LiveRoundExpanded
import com.example.a40kcc.data.`object`.Outcome
import com.example.a40kcc.data.`object`.Prediction
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.ErrorHandling
import com.example.a40kcc.ui.utilities.ScaledText
import kotlinx.coroutines.launch

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
    var canEditOutcome: Boolean by remember { mutableStateOf(false) }
    var editOutcome: Boolean by remember { mutableStateOf(false) }
    var outcome: Outcome? = remember { null }

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

    if (game.game.outcomeID != null) {
        canEditOutcome = true
        outcome = OUTCOME_VIEW_MODEL.getById(outcomeID)
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

            var liveRound: LiveRoundExpanded? = null
            var gameCompleted = false
            if (outcomeId != null) {
                gameCompleted = true
                liveRound = LIVE_ROUND_EXPANDED_VIEW_MODEL.getByGameId(game.game.gameID)
            }

            HISTORICAL_ROUND_DATA_VIEW_MODEL.insert(
                game = GAME_EXPANDED_VIEW_MODEL.getById(game.game.gameID),
                prediction = liveRound?.expectedResult,
                isComplete = gameCompleted
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
                if(!canEditOutcome) {
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
                else
                {
                    ScaledText(
                        text = "Prediction: ${predictions[predictionIndex].getDisplayName()}",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                    )
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                if (outcomeID != 0) {
                    ScaledText(
                        text = "Outcome: ${outcome?.getDisplayName() ?: "None"}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = modifier
                    )
                }

                if(!canEditOutcome) {
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
                }
                else
                {
                    TextButton(
                        onClick = {
                            editOutcome = !editOutcome
                        },
                        modifier = modifier
                    ) {
                        ScaledText(
                            text = "Edit Outcome",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = modifier
                        )
                    }
                }

                if (addOutcome) {
                    val insertOutcome: (outcome: Outcome?) -> Unit = { newOutcome ->
                        if (newOutcome != null) {
                            errorHandling.provideCoroutineExceptionScope(
                                errorMessage = "Error adding the new outcome for: ${game.getDisplayName()}"
                            ).launch {
                                OUTCOME_VIEW_MODEL.insert(newOutcome)
                                outcomeID =
                                    OUTCOME_VIEW_MODEL.getByPlayerId(game.game.player01ID)
                                        .last().outcomeID
                                outcome = newOutcome
                            }
                        }
                        addOutcome = !addOutcome
                    }
                    AddOutcome(
                        player01Id = game.game.player01ID,
                        player02Id = game.game.player02ID,
                        modifier = modifier,
                        onDismissRequest = insertOutcome
                    )
                }

                if (editOutcome) {
                    val insertOutcome: (outcome: Outcome) -> Unit = { newOutcome ->
                        if (newOutcome != game.outcome) {
                            errorHandling.provideCoroutineExceptionScope(
                                errorMessage = "Error updating the outcome for: ${game.getDisplayName()}"
                            ).launch {
                                OUTCOME_VIEW_MODEL.update(newOutcome)
                                outcome = newOutcome
                            }
                        }
                        editOutcome = !editOutcome
                    }
                    game.outcome?.let {
                        EditOutcome(
                            outcome = it,
                            modifier = modifier,
                            onDismissRequest = insertOutcome
                        )
                    }
                }
            }
        }
    }
}