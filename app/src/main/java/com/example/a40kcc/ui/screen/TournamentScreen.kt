package com.example.a40kcc.ui.screen

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.Tournament
import com.example.a40kcc.ui.utilities.TOURNAMENT_VIEW_MODEL
import java.util.Date

@Composable
fun TournamentScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        var addTournament by remember { mutableStateOf(false) }
        Button(
            onClick = onBackClick,
            modifier = modifier
        ) {
            Column {
                Text(stringResource(id = R.string.home_button))
            }
        }

        val tournaments: List<Tournament>? =
            TOURNAMENT_VIEW_MODEL.allTournaments.observeAsState().value

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
                    "Tournament Name",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Column(
                modifier = modifier
                    .alignByBaseline()
                    .wrapContentHeight()
            ) {
                Text(
                    "Tournament Rounds",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Column(
                modifier = modifier
                    .alignByBaseline()
                    .wrapContentHeight()
            ) {
                Text(
                    "Tournament Date",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        if (tournaments != null) {
            TournamentScreen(tournaments, modifier)
        }

        FloatingActionButton(
            onClick = {
                addTournament = !addTournament
            },
            modifier = modifier.align(Alignment.End)
        ) {
            Icon(Icons.Filled.Add, "Add Tournament")

            if (addTournament) {
                AddTournament(
                    modifier
                ) { addTournament = !addTournament }
            }
        }
    }
}

@Composable
private fun TournamentScreen(
    tournaments: List<Tournament>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(tournaments) { tournament ->
            var removeTournament by remember { mutableStateOf(false) }
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
                        tournament.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Column(
                    modifier = modifier
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    Text(
                        text = tournament.roundCount.toString(),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                    )
                }
                Column(
                    modifier = modifier
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    Text(
                        text = tournament.date.toString(),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                    )
                }
                Column {
                    SmallFloatingActionButton(
                        onClick = {
                            removeTournament = !removeTournament
                        },
                        modifier = modifier.align(Alignment.End)
                    ) {
                        Icon(Icons.Filled.Clear, "Remove Tournament")

                        if (removeTournament) {
                            RemoveTournament(
                                tournament,
                                modifier
                            ) { removeTournament = !removeTournament }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun AddTournament(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    var tournamentName by remember { mutableStateOf("") }
    var tournamentRounds by remember { mutableIntStateOf(0) }
    val onConfirmation = {
        val newTournament = Tournament(
            name = tournamentName,
            date = Date(),
            roundCount = tournamentRounds
        )
        TOURNAMENT_VIEW_MODEL.insert(newTournament)
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
                        text = "Add a new tournament"
                    )
                }
                Row {
                    TextField(
                        value = tournamentName,
                        onValueChange = { tournamentName = it },
                        label = { Text("Name:") },
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                }
                Row {
                    TextField(
                        value = tournamentRounds.toString(),
                        onValueChange = { tournamentRounds = it.toInt() },
                        label = { Text("Round Count:") },
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
private fun RemoveTournament(
    tournament: Tournament,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    val onConfirmation = {
        TOURNAMENT_VIEW_MODEL.delete(tournament)
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
                        text = "Confirm remove tournament",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Row {
                    Text(
                        text = "Tournament Name: " + tournament.name,
                        style = MaterialTheme.typography.bodyMedium
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
                            "Confirm",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}