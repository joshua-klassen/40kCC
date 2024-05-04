package com.example.a40kcc.ui.object_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.FACTION_DATA
import com.example.a40kcc.PLAYER_VIEW_MODEL
import com.example.a40kcc.TEAM_VIEW_MODEL
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.data.`object`.Player
import com.example.a40kcc.data.`object`.PlayerWithTeams
import com.example.a40kcc.ui.utilities.ComposeData
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.ScaledText

class PlayerCompose : CoreObjectCompose {
    @Composable
    override fun AddObject(
        composeData: ComposeData,
        onDismissRequest: () -> Unit
    ) {
        val modifier = composeData.modifier
        var playerName by remember { mutableStateOf("") }
        var playerNickname by remember { mutableStateOf("") }
        var playerFaction by remember { mutableStateOf("") }
        var teamID by remember { mutableIntStateOf(0) }
        var factionIndex by remember { mutableIntStateOf(0) }
        var teamIndex by remember { mutableIntStateOf(0) }
        val factionNames: List<String> = listOf("") + FACTION_DATA.getDataKeys().toList()
        val teamNames: MutableList<String> = mutableListOf("")

        TEAM_VIEW_MODEL.allTeams().forEach { team ->
            teamNames += team.name
        }

        val onConfirmation = {
            val newPlayer = Player(
                name = playerName,
                nickname = playerNickname,
                factionName = playerFaction
            )
            PLAYER_VIEW_MODEL.insert(
                newPlayer,
                this.getExceptionHandler(
                    errorMessage = "Error adding the new player $playerName",
                    composeData = composeData,
                    continueRun = true
                )
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
                            label = { Text(text = "Player Name: ") },
                            textStyle = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row {
                        TextField(
                            value = playerNickname,
                            onValueChange = { playerNickname = it },
                            label = { Text(text = "Nickname: ") },
                            textStyle = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = factionNames,
                            selectedIndex = factionIndex,
                            modifier = modifier,
                            preText = "Preferred Faction: ",
                            onItemClick = {
                                factionIndex = it; playerFaction = factionNames[factionIndex]
                            })
                    }
                    Row {
                        DropDownList(
                            itemList = teamNames,
                            selectedIndex = teamIndex,
                            modifier = modifier,
                            preText = "Team: ",
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
    override fun EditObject(
        coreObject: CoreObject,
        composeData: ComposeData,
        onDismissRequest: () -> Unit
    ) {
        val modifier = composeData.modifier
        val player: PlayerWithTeams = coreObject as PlayerWithTeams
        var playerName by remember { mutableStateOf(player.player.name) }
        var playerNickname by remember { mutableStateOf(player.player.nickname!!) }
        var playerFaction by remember { mutableStateOf(player.player.factionName!!) }
        var teamID by remember { mutableIntStateOf(player.team[0].teamID) }
        var factionIndex by remember { mutableIntStateOf(0) }
        var teamIndex by remember { mutableIntStateOf(0) }
        val factionNames: List<String> = listOf("") + FACTION_DATA.getDataKeys().toList()
        val teamNames: MutableList<String> = mutableListOf("")

        TEAM_VIEW_MODEL.allTeams().forEach { team ->
            teamNames += team.name
            if (team.name == player.team[0].name) {
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
            PLAYER_VIEW_MODEL.update(
                updatedPlayer,
                this.getExceptionHandler(
                    errorMessage = "Error updating the player ${player.player.name}",
                    composeData = composeData,
                    continueRun = true
                )
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
                            text = "Edit a player"
                        )
                    }
                    Row {
                        TextField(
                            value = playerName,
                            onValueChange = { playerName = it },
                            label = { Text(text = "Player Name: ") },
                            textStyle = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row {
                        TextField(
                            value = playerNickname,
                            onValueChange = { playerNickname = it },
                            label = { Text(text = "Nickname: ") },
                            textStyle = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = factionNames,
                            selectedIndex = factionIndex,
                            modifier = modifier,
                            preText = "Preferred Faction: ",
                            onItemClick = {
                                factionIndex = it; playerFaction = factionNames[factionIndex]
                            })
                    }
                    Row {
                        DropDownList(
                            itemList = teamNames,
                            selectedIndex = teamIndex,
                            modifier = modifier,
                            preText = "Team: ",
                            onItemClick = { index ->
                                teamIndex = index
                                if (index != 0) {
                                    teamID = TEAM_VIEW_MODEL.getByName(teamNames[teamIndex]).teamID
                                }
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
    override fun RemoveObject(
        coreObject: CoreObject,
        composeData: ComposeData,
        onDismissRequest: () -> Unit
    ) {
        val modifier = composeData.modifier
        val player: PlayerWithTeams = coreObject as PlayerWithTeams

        val onConfirmation = {
            PLAYER_VIEW_MODEL.delete(
                player.player,
                this.getExceptionHandler(
                    errorMessage = "Error removing the player ${player.player.name}",
                    composeData = composeData,
                    continueRun = true
                )
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
                        ScaledText(
                            text = "Confirm remove player",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Row {
                        ScaledText(
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
}