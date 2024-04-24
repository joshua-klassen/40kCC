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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.data.`object`.Team
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.PLAYER_VIEW_MODEL
import com.example.a40kcc.ui.utilities.ScaledText
import com.example.a40kcc.ui.utilities.TEAM_VIEW_MODEL

class TeamCompose : CoreObjectCompose {
    @Composable
    override fun AddObject(
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
        val localContext = LocalContext.current
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
            TEAM_VIEW_MODEL.insert(
                newTeam,
                this.getExceptionHandler(
                    errorMessage = "Error adding the new team $teamName",
                    context = localContext,
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
                            text = "Add a new team"
                        )
                    }
                    Row {
                        TextField(
                            value = teamName,
                            onValueChange = { teamName = it },
                            label = { Text(text = "Team Name: ") },
                            textStyle = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = playerNames,
                            selectedIndex = playerIndex,
                            modifier = modifier,
                            preText = "Player: ",
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

    override fun canEdit(coreObject: CoreObject): Boolean {
        return false
    }

    @Composable
    override fun RemoveObject(
        coreObject: CoreObject,
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
        val localContext = LocalContext.current
        val team: Team = coreObject as Team
        val onConfirmation = {
            TEAM_VIEW_MODEL.delete(
                team,
                this.getExceptionHandler(
                    errorMessage = "Error removing the team ${team.name}",
                    context = localContext,
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
}