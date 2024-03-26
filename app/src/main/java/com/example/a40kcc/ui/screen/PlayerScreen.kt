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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.a40kcc.data.model.PlayerViewModel
import com.example.a40kcc.data.`object`.DataObject
import com.example.a40kcc.data.`object`.Player
import com.example.a40kcc.data.`object`.PlayerExpanded
import com.example.a40kcc.ui.utilities.DropDownList

@Composable
fun PlayerScreen(
    playerViewModel: PlayerViewModel,
    factionData: DataObject,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        var addPlayer by remember { mutableStateOf(false) }
        Button(
            onClick = onBackClick,
            modifier = modifier
        ) {
            Column {
                Text(stringResource(id = R.string.home_button))
            }
        }

        val players: List<PlayerExpanded>? =
            playerViewModel.allPlayersExpanded.observeAsState().value

        if (players != null) {
            PlayerScreen(players, factionData, modifier)
        }

        FloatingActionButton(
            onClick = {
                addPlayer = !addPlayer
            },
        ) {
            Icon(Icons.Filled.Add, "Add Player")

            if (addPlayer) {
                AddPlayer(
                    factionData,
                    modifier
                ) { addPlayer = !addPlayer }
            }
        }
    }
}

@Composable
fun PlayerScreen(
    players: List<PlayerExpanded>,
    factionData: DataObject,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(players) { player ->
            var showDetails by remember { mutableStateOf(false) }
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
                        player.player.name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                            .clickable(true, onClick = {
                                showDetails = !showDetails
                            })
                            .fillMaxWidth()
                    )
                    Text(
                        player.team!![0].name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier.fillMaxWidth()
                    )
                }
            }

            if (showDetails) {
                PlayerDetailScreen(
                    player,
                    factionData,
                    modifier
                )
            }
        }
    }
}

@Composable
fun PlayerDetailScreen(
    player: PlayerExpanded,
    factionData: DataObject,
    modifier: Modifier = Modifier
) {
    println(player)
    println(factionData)
}

@Composable
fun AddPlayer(
    factionData: DataObject,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    var newPlayer: Player? = null
    var playerName by remember { mutableStateOf("") }
    var playerNickname by remember { mutableStateOf("") }
    var playerFaction by remember { mutableStateOf("") }
    var selectedIndex by remember { mutableIntStateOf(0) }
    val factionNames: List<String> = listOf("None") + factionData.getDataKeys().toList()
    val onConfirmation = {
        newPlayer = Player(
            playerID = 0,
            name = playerName,
            nickname = playerNickname,
            factionName = playerFaction
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
                        text = "Add a new player (Only player name is required)"
                    )
                }
                Row {
                    TextField(
                        value = playerName,
                        onValueChange = { playerName = it },
                        label = { Text("Player Name:") },
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                }
                Row {
                    TextField(
                        value = playerNickname,
                        onValueChange = { playerNickname = it },
                        label = { Text("Nickname:") },
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                }
                Row {
                    DropDownList(
                        itemList = factionNames,
                        selectedIndex = selectedIndex,
                        modifier = modifier,
                        preText = "Preferred Faction:",
                        onItemClick = {
                            selectedIndex = it; playerFaction = factionNames[selectedIndex]
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