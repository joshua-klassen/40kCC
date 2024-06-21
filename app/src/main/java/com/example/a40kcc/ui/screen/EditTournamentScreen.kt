package com.example.a40kcc.ui.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.example.a40kcc.ROUND_VIEW_MODEL
import com.example.a40kcc.TOURNAMENT_VIEW_MODEL
import com.example.a40kcc.TOURNAMENT_WITH_ROUNDS_VIEW_MODEL
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.data.`object`.Tournament
import com.example.a40kcc.data.`object`.TournamentWithRounds
import com.example.a40kcc.ui.object_compose.AddRound
import com.example.a40kcc.ui.object_compose.EditRound
import com.example.a40kcc.ui.utilities.ErrorHandling
import com.example.a40kcc.ui.utilities.ScaledText
import kotlinx.coroutines.launch

@Composable
fun EditTournament(
    coreObject: CoreObject,
    modifier: Modifier,
    onDismissRequest: () -> Unit
) {
    val tournament: TournamentWithRounds = coreObject as TournamentWithRounds
    var addRound by remember { mutableStateOf(false) }
    val rounds = remember { tournament.round.toMutableStateList() }
    var newRounds: List<Int> = emptyList()
    val snackbarHostState = remember { SnackbarHostState() }
    val errorHandling = remember {
        ErrorHandling(
            snackbarHostState = snackbarHostState,
            modifier = modifier
        )
    }

    val onConfirmation = {
        val newTournament = Tournament(
            tournamentID = tournament.tournament.tournamentID,
            name = tournament.tournament.name,
            date = tournament.tournament.date,
            roundCount = rounds.size
        )

        errorHandling.provideCoroutineExceptionScope(
            errorMessage = "Error updating the new tournament ${tournament.tournament.name}"
        ).launch {
            TOURNAMENT_VIEW_MODEL.update(newTournament)
            newRounds.forEach { index ->
                TOURNAMENT_WITH_ROUNDS_VIEW_MODEL.insert(
                    tournamentID = newTournament.tournamentID,
                    roundID = rounds[index].roundID
                )
            }
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
            Row {
                ScaledText(
                    text = tournament.getDisplayName(),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = modifier,
                    textAlign = TextAlign.Center
                )
            }
            Row {
                TextButton(
                    onClick = {
                        addRound = !addRound
                    },
                    modifier = modifier
                ) {
                    Text(
                        text = "Add Round",
                        style = MaterialTheme.typography.titleMedium
                    )
                }

                if (addRound) {
                    val insertRound: (round: Round?) -> Unit = { round ->
                        if (round != null) {
                            errorHandling.provideCoroutineExceptionScope(
                                errorMessage = "Error adding the new round for: ${tournament.tournament.name}"
                            ).launch {
                                ROUND_VIEW_MODEL.insert(round)
                                rounds.add(ROUND_VIEW_MODEL.allRounds().last())
                                newRounds = listOf(rounds.lastIndex) + newRounds
                                addRound = !addRound
                            }
                        } else {
                            addRound = !addRound
                        }
                    }
                    AddRound(
                        roundNumber = (rounds.size + 1),
                        onDismissRequest = insertRound,
                        modifier = modifier
                    )
                }
            }
            rounds.forEach { round ->
                var editRound by remember { mutableStateOf(false) }
                var removeRound by remember { mutableStateOf(false) }
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = modifier
                        .fillMaxWidth()
                        .horizontalScroll(ScrollState(initial = 1))
                ) {
                    round.getDetailColumns().keys.forEach { data ->
                        Column(
                            modifier = modifier
                                .wrapContentHeight()
                        ) {
                            ScaledText(
                                text = data,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = modifier,
                                textAlign = TextAlign.Center
                            )
                            round.getDetailColumns()[data]?.let {
                                ScaledText(
                                    text = it,
                                    style = MaterialTheme.typography.bodyMedium,
                                    modifier = modifier,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    Column(
                        modifier = modifier
                            .wrapContentHeight()
                    ) {
                        SmallFloatingActionButton(
                            onClick = {
                                editRound = !editRound
                            },
                            modifier = modifier.align(Alignment.End)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Build,
                                contentDescription = "Edit",
                                modifier = modifier
                            )

                            if (editRound) {
                                EditRound(
                                    round = round,
                                    errorHandling = errorHandling,
                                    modifier = modifier,
                                    onDismissRequest = {
                                        editRound = !editRound
                                    }
                                )
                            }
                        }
                    }
                    /*Column(
                        modifier = modifier
                            .wrapContentHeight()
                    ) {
                        SmallFloatingActionButton(
                            onClick = {
                                removeRound = !removeRound
                            },
                            modifier = modifier.align(Alignment.End)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "Remove",
                                modifier = modifier
                            )

                            if (removeRound) {
                                RemoveRound(
                                    round = round,
                                    errorHandling = errorHandling,
                                    modifier = modifier,
                                    onDismissRequest = {
                                        removeRound = !removeRound
                                        rounds.remove(round)
                                    }
                                )
                            }
                        }
                    }*/
                }
            }
        }
    }
}