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
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.Team
import com.example.a40kcc.data.`object`.TeamWithPlayers
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.PLAYER_VIEW_MODEL
import com.example.a40kcc.ui.utilities.TEAM_VIEW_MODEL
import com.example.a40kcc.ui.utilities.TEAM_WITH_PLAYERS_VIEW_MODEL

@Composable
fun TeamScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        var addTeam by remember { mutableStateOf(false) }
        Button(
            onClick = onBackClick,
            modifier = modifier
        ) {
            Column {
                Text(text = stringResource(id = R.string.home_button))
            }
        }

        val teams: List<TeamWithPlayers>? =
            TEAM_WITH_PLAYERS_VIEW_MODEL.allTeamsFlow.observeAsState().value

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
                    text = "Team Name",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        if (teams != null) {
            TeamScreen(
                teams = teams, modifier = modifier
            )
        }

        FloatingActionButton(
            onClick = {
                addTeam = !addTeam
            },
            modifier = modifier.align(Alignment.End)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Team")

            if (addTeam) {
                AddTeam(
                    modifier = modifier,
                    onDismissRequest = { addTeam = !addTeam }
                )
            }
        }
    }
}

@Composable
private fun TeamScreen(
    teams: List<TeamWithPlayers>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(teams) { team ->
            var showDetails by remember { mutableStateOf(false) }
            var removeTeam by remember { mutableStateOf(false) }
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
                        text = team.team.name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                            .clickable(true, onClick = {
                                showDetails = !showDetails
                            })
                    )
                }
                Column {
                    if (showDetails) {
                        SmallFloatingActionButton(
                            onClick = {
                                removeTeam = !removeTeam
                            },
                            modifier = modifier.align(Alignment.End)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "Remove Player"
                            )

                            if (removeTeam) {
                                RemoveTeam(
                                    team = team.team,
                                    modifier = modifier,
                                    onDismissRequest = { removeTeam = !removeTeam }
                                )
                            }
                        }
                    }
                }
            }

            if (showDetails) {
                TeamDetailScreen(
                    team = team,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
private fun TeamDetailScreen(
    team: TeamWithPlayers,
    modifier: Modifier = Modifier
) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                text = "Players",
                style = MaterialTheme.typography.titleMedium
            )
        }
        team.player.forEach {
            Column(
                modifier = modifier.wrapContentHeight()
            ) {
                Text(
                    text = "Name: " + it.name,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}

@Composable
private fun AddTeam(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    var teamName by remember { mutableStateOf("") }
    var playerID by remember { mutableIntStateOf(0) }
    var playerIndex by remember { mutableIntStateOf(0) }
    val playerNames: MutableList<String> = mutableListOf("")
    PLAYER_VIEW_MODEL.allPlayers.forEach {
        playerNames += it.name
    }
    val onConfirmation = {
        val newTeam = Team(
            name = teamName
        )
        TEAM_VIEW_MODEL.insert(newTeam)
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
                        text = "Add a new team"
                    )
                }
                Row {
                    TextField(
                        value = teamName,
                        onValueChange = { teamName = it },
                        label = { Text(text = "Team Name:") },
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                }
                Row {
                    DropDownList(
                        itemList = playerNames,
                        selectedIndex = playerIndex,
                        modifier = modifier,
                        preText = "Player:",
                        onItemClick = {
                            playerIndex = it; playerID =
                            PLAYER_VIEW_MODEL.getByName(playerNames[playerIndex]).playerID
                        })
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
private fun RemoveTeam(
    team: Team,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    val onConfirmation = {
        TEAM_VIEW_MODEL.delete(team)
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
                        text = "Confirm remove player",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Row {
                    Text(
                        text = "Team Name: " + team.name,
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