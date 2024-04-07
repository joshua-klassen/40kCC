package com.example.a40kcc.ui.screen

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.data.`object`.RoundExpanded
import com.example.a40kcc.ui.utilities.DEPLOYMENT_DATA
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.PRIMARY_MISSION_DATA
import com.example.a40kcc.ui.utilities.ROUND_VIEW_MODEL
import com.example.a40kcc.ui.utilities.SECONDARY_MISSION_DATA
import com.example.a40kcc.ui.utilities.TOURNAMENT_VIEW_MODEL

@Composable
fun RoundScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        var addRound by remember { mutableStateOf(false) }
        Button(
            onClick = onBackClick,
            modifier = modifier
        ) {
            Column {
                Text(stringResource(id = R.string.home_button))
            }
        }

        val rounds: List<RoundExpanded>? =
            ROUND_VIEW_MODEL.allRoundsExpanded.observeAsState().value

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
                    "Round Number",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        if (rounds != null) {
            RoundScreen(
                rounds, modifier
            )
        }

        FloatingActionButton(
            onClick = {
                addRound = !addRound
            },
            modifier = modifier.align(Alignment.End)
        ) {
            Icon(Icons.Filled.Add, "Add Round")

            if (addRound) {
                AddRound(
                    modifier
                ) { addRound = !addRound }
            }
        }
    }
}

@Composable
private fun RoundScreen(
    rounds: List<RoundExpanded>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(rounds) { round ->
            var showDetails by remember { mutableStateOf(false) }
            var removeRound by remember { mutableStateOf(false) }
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
                        round.tournament.name,
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
                        text = round.round.number.toString(),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                    )
                }
                Column {
                    if (showDetails) {
                        SmallFloatingActionButton(
                            onClick = {
                                removeRound = !removeRound
                            },
                            modifier = modifier.align(Alignment.End)
                        ) {
                            Icon(Icons.Filled.Clear, "Remove Round")

                            if (removeRound) {
                                RemoveRound(
                                    round,
                                    modifier
                                ) { removeRound = !removeRound }
                            }
                        }
                    }
                }
            }

            if (showDetails) {
                RoundDetailScreen(
                    round,
                    modifier
                )
            }
        }
    }
}

@Composable
private fun RoundDetailScreen(
    round: RoundExpanded,
    modifier: Modifier = Modifier
) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                "Primary Mission",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                round.round.primaryMissionName,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                "Secondary Mission",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                round.round.secondaryMissionName,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                "Deployment",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                round.round.deploymentName,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun AddRound(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    var roundNumber by remember { mutableIntStateOf(0) }
    var primaryMissionName by remember { mutableStateOf("") }
    var secondaryMissionName by remember { mutableStateOf("") }
    var deploymentName by remember { mutableStateOf("") }
    var tournamentID by remember { mutableIntStateOf(0) }
    var primaryMissionIndex by remember { mutableIntStateOf(0) }
    var secondaryMissionIndex by remember { mutableIntStateOf(0) }
    var deploymentIndex by remember { mutableIntStateOf(0) }
    var tournamentIndex by remember { mutableIntStateOf(0) }
    val primaryMissionNames: List<String> = listOf("") + PRIMARY_MISSION_DATA.getDataKeys().toList()
    val secondaryMissionNames: List<String> =
        listOf("") + SECONDARY_MISSION_DATA.getDataKeys().toList()
    val deploymentNames: List<String> = listOf("") + DEPLOYMENT_DATA.getDataKeys().toList()
    val tournamentNames: MutableList<String> = mutableListOf("")
    TOURNAMENT_VIEW_MODEL.allTournaments.value?.forEach {
        tournamentNames += it.name
    }
    val onConfirmation = {
        val newRound = Round(
            number = roundNumber,
            primaryMissionName = primaryMissionName,
            secondaryMissionName = secondaryMissionName,
            deploymentName = deploymentName,
            tournamentID = tournamentID
        )
        ROUND_VIEW_MODEL.insert(newRound)
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
                        text = "Add a new round (Only round name is required)"
                    )
                }
                Row {
                    TextField(
                        value = roundNumber.toString(),
                        onValueChange = { roundNumber = it.toInt() },
                        label = { Text("Round Count:") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                }
                Row {
                    DropDownList(
                        itemList = primaryMissionNames,
                        selectedIndex = primaryMissionIndex,
                        modifier = modifier,
                        preText = "Primary Mission:",
                        onItemClick = {
                            primaryMissionIndex = it; primaryMissionName =
                            primaryMissionNames[primaryMissionIndex]
                        })
                }
                Row {
                    DropDownList(
                        itemList = secondaryMissionNames,
                        selectedIndex = secondaryMissionIndex,
                        modifier = modifier,
                        preText = "Secondary Mission:",
                        onItemClick = {
                            secondaryMissionIndex = it; secondaryMissionName =
                            secondaryMissionNames[secondaryMissionIndex]
                        })
                }
                Row {
                    DropDownList(
                        itemList = deploymentNames,
                        selectedIndex = deploymentIndex,
                        modifier = modifier,
                        preText = "Deployment:",
                        onItemClick = {
                            deploymentIndex = it; deploymentName = deploymentNames[deploymentIndex]
                        })
                }
                Row {
                    DropDownList(
                        itemList = tournamentNames,
                        selectedIndex = tournamentIndex,
                        modifier = modifier,
                        preText = "Preferred Faction:",
                        onItemClick = {
                            tournamentIndex = it; tournamentID =
                            TOURNAMENT_VIEW_MODEL.getByName(tournamentNames[tournamentIndex]).value?.first()?.tournamentID
                                ?: 0
                        })
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
private fun RemoveRound(
    round: RoundExpanded,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    val onConfirmation = {
        ROUND_VIEW_MODEL.delete(round.round)
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
                        text = "Confirm remove round",
                        style = MaterialTheme.typography.titleMedium
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