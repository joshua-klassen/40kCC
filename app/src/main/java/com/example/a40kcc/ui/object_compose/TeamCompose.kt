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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.a40kcc.TEAM_VIEW_MODEL
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.data.`object`.Team
import com.example.a40kcc.data.`object`.TeamWithPlayers
import com.example.a40kcc.ui.utilities.ErrorHandling
import com.example.a40kcc.ui.utilities.ScaledText
import kotlinx.coroutines.launch

class TeamCompose(override var errorHandling: ErrorHandling) : CoreObjectCompose {
    @Composable
    override fun AddObject(
        navController: NavController,
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
        var teamName by remember { mutableStateOf("") }

        val onConfirmation = {
            val newTeam = Team(
                name = teamName
            )
            errorHandling.provideCoroutineExceptionScope(
                errorMessage = "Error adding team: $teamName"
            ).launch {
                TEAM_VIEW_MODEL.insert(newTeam)
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
                            enabled = (teamName.isNotBlank())
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
        navController: NavController,
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
        val team: TeamWithPlayers = coreObject as TeamWithPlayers

        val onConfirmation = {
            errorHandling.provideCoroutineExceptionScope(
                errorMessage = "Error removing team: ${team.getDisplayName()}"
            ).launch {
                TEAM_VIEW_MODEL.delete(team.team)
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
                            text = "Team Name: " + team.team.name,
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