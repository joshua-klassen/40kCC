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
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.a40kcc.FACTION_DATA
import com.example.a40kcc.PLAYER_VIEW_MODEL
import com.example.a40kcc.PLAYER_WITH_TEAMS_VIEW_MODEL
import com.example.a40kcc.TEAM_VIEW_MODEL
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.data.`object`.Player
import com.example.a40kcc.data.`object`.PlayerWithTeams
import com.example.a40kcc.data.`object`.Team
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.ErrorHandling
import com.example.a40kcc.ui.utilities.ScaledText
import kotlinx.coroutines.launch

class PlayerCompose(override var errorHandling: ErrorHandling) : CoreObjectCompose {
    @Composable
    override fun AddObject(
        navController: NavController,
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
        var playerName by remember { mutableStateOf("") }
        var playerNickname by remember { mutableStateOf("") }
        var playerFaction by remember { mutableStateOf("") }
        var teamID by remember { mutableIntStateOf(0) }
        var factionIndex by remember { mutableIntStateOf(0) }
        var teamIndex by remember { mutableIntStateOf(0) }
        val factionNames: List<String> = FACTION_DATA.getDataKeys().toList()
        val teams: List<Team> = TEAM_VIEW_MODEL.allTeams()

        val onConfirmation = {
            val newPlayer = Player(
                name = playerName,
                nickname = playerNickname,
                factionName = playerFaction
            )

            errorHandling.provideCoroutineExceptionScope(
                errorMessage = "Error adding the new player: $playerName"
            ).launch {
                PLAYER_VIEW_MODEL.insert(newPlayer)
                if (teamID != 0) {
                    val lastPlayer = PLAYER_VIEW_MODEL.allPlayers().last()
                    PLAYER_WITH_TEAMS_VIEW_MODEL.insert(
                        playerID = lastPlayer.playerID,
                        teamID = teamID
                    )
                }
            }
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
                            text = "Add a new player"
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
                            addEmptyFirstOption = true,
                            firstOptionSelected = (playerFaction.isBlank()),
                            onItemClick = {
                                factionIndex = it
                                if (factionIndex < 0) {
                                    factionIndex = 0
                                    playerFaction = ""
                                } else {
                                    playerFaction = factionNames[factionIndex]
                                }
                            }
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = teams.map { it.getDisplayName() },
                            selectedIndex = teamIndex,
                            modifier = modifier,
                            preText = "Team: ",
                            addEmptyFirstOption = true,
                            firstOptionSelected = (teamID == 0),
                            onItemClick = {
                                teamIndex = it
                                if (teamIndex < 0) {
                                    teamIndex = 0
                                    teamID = 0
                                } else {
                                    teamID = teams[teamIndex].teamID
                                }
                            }
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
                            modifier = modifier,
                            enabled = (playerFaction.isNotBlank() and playerName.isNotBlank())
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
        navController: NavController,
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
        val player: PlayerWithTeams = coreObject as PlayerWithTeams
        var playerName by remember { mutableStateOf(player.player.name) }
        var playerNickname by remember { mutableStateOf(player.player.nickname!!) }
        var playerFaction by remember { mutableStateOf(player.player.factionName!!) }
        var teamID by remember { mutableIntStateOf(player.team[0].teamID) }
        var factionIndex by remember { mutableIntStateOf(0) }
        var teamIndex by remember { mutableIntStateOf(0) }
        val factionNames: List<String> = FACTION_DATA.getDataKeys().toList()
        val teams: List<Team> = TEAM_VIEW_MODEL.allTeams()

        if (teams.contains(player.team[0])) {
            teamIndex = teams.indexOf(player.team[0])
        }

        factionIndex = factionNames.indexOf(playerFaction)

        val onConfirmation = {
            val updatedPlayer = Player(
                playerID = player.player.playerID,
                name = playerName,
                nickname = playerNickname,
                factionName = playerFaction
            )

            errorHandling.provideCoroutineExceptionScope(
                errorMessage = "Error updating player: ${player.getDisplayName()}"
            ).launch {
                PLAYER_VIEW_MODEL.update(updatedPlayer)
                onDismissRequest()
            }
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
                            addEmptyFirstOption = true,
                            firstOptionSelected = (playerFaction.isBlank()),
                            onItemClick = {
                                factionIndex = it
                                if (factionIndex < 0) {
                                    factionIndex = 0
                                    playerFaction = ""
                                } else {
                                    playerFaction = factionNames[factionIndex]
                                }
                            }
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = teams.map { it.getDisplayName() },
                            selectedIndex = teamIndex,
                            modifier = modifier,
                            preText = "Team: ",
                            onItemClick = { index ->
                                teamIndex = index
                                if (teamIndex < 0) {
                                    teamIndex = 0
                                    teamID = 0
                                } else {
                                    teamID = teams[teamIndex].teamID
                                }
                            }
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
        navController: NavController,
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
        val player: PlayerWithTeams = coreObject as PlayerWithTeams

        val onConfirmation = {
            errorHandling.provideCoroutineExceptionScope(
                errorMessage = "Error while deleting the player: ${player.getDisplayName()}"
            ).launch {
                PLAYER_VIEW_MODEL.delete(player.player)
                onDismissRequest()
            }
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