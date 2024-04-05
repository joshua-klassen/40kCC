package com.example.a40kcc.ui.screen

import android.graphics.drawable.Drawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.Player
import com.example.a40kcc.data.`object`.PlayerExpanded
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.FACTION_DATA
import com.example.a40kcc.ui.utilities.PLAYER_VIEW_MODEL
import com.google.accompanist.drawablepainter.rememberDrawablePainter

@Composable
fun PlayerScreen(
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
            PLAYER_VIEW_MODEL.allPlayersExpanded.observeAsState().value

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
                    "Player Name",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Column(
                modifier = modifier
                    .alignByBaseline()
                    .wrapContentHeight()
            ) {
                Text(
                    "Player Team",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        if (players != null) {
            PlayerScreen(
                players, modifier
            )
        }

        FloatingActionButton(
            onClick = {
                addPlayer = !addPlayer
            },
            modifier = modifier.align(Alignment.End)
        ) {
            Icon(Icons.Filled.Add, "Add Player")

            if (addPlayer) {
                AddPlayer(
                    modifier
                ) { addPlayer = !addPlayer }
            }
        }
    }
}

@Composable
private fun PlayerScreen(
    players: List<PlayerExpanded>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(players) { player ->
            var showDetails by remember { mutableStateOf(false) }
            var removePlayer by remember { mutableStateOf(false) }
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
                    )
                }
                Column(
                    modifier = modifier
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    if (!player.team.isNullOrEmpty()) {
                        Text(
                            text = player.team[0].name,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = modifier
                        )
                    }
                }
                Column {
                    if (showDetails) {
                        SmallFloatingActionButton(
                            onClick = {
                                removePlayer = !removePlayer
                            },
                            modifier = modifier.align(Alignment.End)
                        ) {
                            Icon(Icons.Filled.Clear, "Remove Player")

                            if (removePlayer) {
                                RemovePlayer(
                                    player,
                                    modifier
                                ) { removePlayer = !removePlayer }
                            }
                        }
                    }
                }
            }

            if (showDetails) {
                PlayerDetailScreen(
                    player,
                    modifier
                )
            }
        }
    }
}

@Composable
private fun PlayerDetailScreen(
    player: PlayerExpanded,
    modifier: Modifier = Modifier
) {
    val playerFaction =
        if (!player.player.factionName.isNullOrBlank()) FACTION_DATA.getDataValue(player.player.factionName) else null
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                "Nickname",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                player.player.nickname!!,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                "Preferred Faction",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                player.player.factionName.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                "Super Faction",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                playerFaction?.get(1).toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                "Faction Icon",
                style = MaterialTheme.typography.titleMedium
            )
            Image(
                painter = rememberDrawablePainter(playerFaction?.get(0) as Drawable?),
                contentDescription = "Image",
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.icon_image_size_small))
                    .width(dimensionResource(id = R.dimen.icon_image_size_small))
            )
        }
    }
}

@Composable
private fun AddPlayer(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    var playerName by remember { mutableStateOf("") }
    var playerNickname by remember { mutableStateOf("") }
    var playerFaction by remember { mutableStateOf("") }
    var selectedIndex by remember { mutableIntStateOf(0) }
    val factionNames: List<String> = listOf("") + FACTION_DATA.getDataKeys().toList()
    val onConfirmation = {
        val newPlayer = Player(
            name = playerName,
            nickname = playerNickname,
            factionName = playerFaction
        )
        PLAYER_VIEW_MODEL.insert(newPlayer)
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

@Composable
private fun RemovePlayer(
    player: PlayerExpanded,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    val onConfirmation = {
        PLAYER_VIEW_MODEL.delete(player.player)
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
                        text = "Player Name: " + player.player.name,
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