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
import androidx.compose.material.icons.filled.Build
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
import com.example.a40kcc.data.`object`.PlayerWithTeams
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.FACTION_DATA
import com.example.a40kcc.ui.utilities.PLAYER_VIEW_MODEL
import com.example.a40kcc.ui.utilities.PLAYER_WITH_TEAMS_VIEW_MODEL
import com.example.a40kcc.ui.utilities.TEAM_VIEW_MODEL
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
                Text(text = stringResource(id = R.string.home_button))
            }
        }

        val players: List<PlayerWithTeams>? =
            PLAYER_WITH_TEAMS_VIEW_MODEL.allPlayersFlow.observeAsState().value

        //HACK TO MAKE THINGS WORK FOR ALPHA
        //Add all players to the first team
        players?.forEach {
            if (it.team.isEmpty()) {
                val teams = TEAM_VIEW_MODEL.allTeams
                PLAYER_WITH_TEAMS_VIEW_MODEL.insert(
                    playerID = it.player.playerID,
                    teamID = teams.first().teamID
                )
            }
        }

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
                    text = "Player Name",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Column(
                modifier = modifier
                    .alignByBaseline()
                    .wrapContentHeight()
            ) {
                Text(
                    text = "Player Team",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        if (players != null) {
            PlayerScreen(
                players = players,
                modifier = modifier
            )
        }

        FloatingActionButton(
            onClick = {
                addPlayer = !addPlayer
            },
            modifier = modifier.align(Alignment.End)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Player")

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
    players: List<PlayerWithTeams>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(players) { player ->
            var showDetails by remember { mutableStateOf(false) }
            var removePlayer by remember { mutableStateOf(false) }
            var editPlayer by remember { mutableStateOf(false) }
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
                        text = player.player.name,
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
                    println(player.team)
                    if (player.team.isNotEmpty()) {
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
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "Remove Player"
                            )

                            if (removePlayer) {
                                RemovePlayer(
                                    player = player,
                                    modifier = modifier,
                                    onDismissRequest = { removePlayer = !removePlayer }
                                )
                            }
                        }
                        SmallFloatingActionButton(
                            onClick = {
                                editPlayer = !editPlayer
                            },
                            modifier = modifier.align(Alignment.End)
                        ) {
                            Icon(imageVector = Icons.Filled.Build, contentDescription = "Edit Game")

                            if (editPlayer) {
                                EditPlayer(
                                    player = player,
                                    modifier = modifier,
                                    onDismissRequest = { editPlayer = !editPlayer }
                                )
                            }
                        }
                    }
                }
            }

            if (showDetails) {
                PlayerDetailScreen(
                    player = player,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
private fun PlayerDetailScreen(
    player: PlayerWithTeams,
    modifier: Modifier = Modifier
) {
    var playerFaction: Array<Any>? = null
    if (!player.player.factionName.isNullOrBlank()) {
        playerFaction = FACTION_DATA.getDataValue(player.player.factionName)
    }

    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                text = "Nickname",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = player.player.nickname!!,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                text = "Preferred Faction",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = player.player.factionName.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                text = "Super Faction",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = playerFaction?.get(1).toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                text = "Faction Icon",
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
    var teamID by remember { mutableIntStateOf(0) }
    var factionIndex by remember { mutableIntStateOf(0) }
    var teamIndex by remember { mutableIntStateOf(0) }
    val factionNames: List<String> = listOf("") + FACTION_DATA.getDataKeys().toList()
    val teamNames: MutableList<String> = mutableListOf("")
    TEAM_VIEW_MODEL.allTeams.forEach {
        teamNames += it.name
    }
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
                        label = { Text(text = "Player Name:") },
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                }
                Row {
                    TextField(
                        value = playerNickname,
                        onValueChange = { playerNickname = it },
                        label = { Text(text = "Nickname:") },
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                }
                Row {
                    DropDownList(
                        itemList = factionNames,
                        selectedIndex = factionIndex,
                        modifier = modifier,
                        preText = "Preferred Faction:",
                        onItemClick = {
                            factionIndex = it; playerFaction = factionNames[factionIndex]
                        })
                }
                Row {
                    DropDownList(
                        itemList = teamNames,
                        selectedIndex = teamIndex,
                        modifier = modifier,
                        preText = "Team:",
                        onItemClick = {
                            teamIndex = it; teamID =
                            TEAM_VIEW_MODEL.getByName(teamNames[teamIndex]).teamID
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
private fun EditPlayer(
    player: PlayerWithTeams,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    var playerName by remember { mutableStateOf(player.player.name) }
    var playerNickname by remember { mutableStateOf(player.player.nickname!!) }
    var playerFaction by remember { mutableStateOf(player.player.factionName!!) }
    var teamID by remember { mutableIntStateOf(player.team[0].teamID) }
    var factionIndex by remember { mutableIntStateOf(0) }
    var teamIndex by remember { mutableIntStateOf(0) }
    val factionNames: List<String> = listOf("") + FACTION_DATA.getDataKeys().toList()
    val teamNames: MutableList<String> = mutableListOf("")
    TEAM_VIEW_MODEL.allTeams.forEach {
        teamNames += it.name
        if (it.name == player.team[0].name) {
            teamIndex = teamNames.lastIndex
        }
    }
    factionIndex = factionNames.indexOf(playerFaction)
    val onConfirmation = {
        val updatedPlayer = Player(
            playerID = player.player.playerID,
            name = playerName,
            nickname = playerNickname,
            factionName = playerFaction
        )
        PLAYER_VIEW_MODEL.update(updatedPlayer)
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
                        text = "Edit a player"
                    )
                }
                Row {
                    TextField(
                        value = playerName,
                        onValueChange = { playerName = it },
                        label = { Text(text = "Player Name:") },
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                }
                Row {
                    TextField(
                        value = playerNickname,
                        onValueChange = { playerNickname = it },
                        label = { Text(text = "Nickname:") },
                        textStyle = MaterialTheme.typography.bodyMedium
                    )
                }
                Row {
                    DropDownList(
                        itemList = factionNames,
                        selectedIndex = factionIndex,
                        modifier = modifier,
                        preText = "Preferred Faction:",
                        onItemClick = {
                            factionIndex = it; playerFaction = factionNames[factionIndex]
                        })
                }
                Row {
                    DropDownList(
                        itemList = teamNames,
                        selectedIndex = teamIndex,
                        modifier = modifier,
                        preText = "Team:",
                        onItemClick = {
                            teamIndex = it; teamID =
                            TEAM_VIEW_MODEL.getByName(teamNames[teamIndex]).teamID
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
private fun RemovePlayer(
    player: PlayerWithTeams,
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