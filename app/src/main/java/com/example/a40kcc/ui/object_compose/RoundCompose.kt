package com.example.a40kcc.ui.object_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.data.`object`.RoundWithTournament
import com.example.a40kcc.ui.utilities.DEPLOYMENT_DATA
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.PRIMARY_MISSION_DATA
import com.example.a40kcc.ui.utilities.ROUND_VIEW_MODEL
import com.example.a40kcc.ui.utilities.SECONDARY_MISSION_DATA
import com.example.a40kcc.ui.utilities.ScaledText
import com.example.a40kcc.ui.utilities.TOURNAMENT_VIEW_MODEL

class RoundCompose : CoreObjectCompose {
    @Composable
    override fun AddObject(
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
        val localContext = LocalContext.current
        var roundNumber by remember { mutableIntStateOf(0) }
        var primaryMissionName by remember { mutableStateOf("") }
        var secondaryMissionName by remember { mutableStateOf("") }
        var deploymentName by remember { mutableStateOf("") }
        var tournamentID by remember { mutableIntStateOf(0) }
        var primaryMissionIndex by remember { mutableIntStateOf(0) }
        var secondaryMissionIndex by remember { mutableIntStateOf(0) }
        var deploymentIndex by remember { mutableIntStateOf(0) }
        var tournamentIndex by remember { mutableIntStateOf(0) }
        val primaryMissionNames: List<String> =
            listOf("") + PRIMARY_MISSION_DATA.getDataKeys().toList()
        val secondaryMissionNames: List<String> =
            listOf("") + SECONDARY_MISSION_DATA.getDataKeys().toList()
        val deploymentNames: List<String> = listOf("") + DEPLOYMENT_DATA.getDataKeys().toList()
        val tournamentNames: MutableList<String> = mutableListOf("")
        TOURNAMENT_VIEW_MODEL.allTournaments.forEach {
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
            ROUND_VIEW_MODEL.insert(
                newRound,
                this.getExceptionHandler(
                    errorMessage = "Error adding the new round",
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
                            text = "Add a new round (Only round name is required)"
                        )
                    }
                    Row {
                        TextField(
                            value = roundNumber.toString(),
                            onValueChange = { roundNumber = it.toInt() },
                            label = { Text(text = "Round Count: ") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            textStyle = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = primaryMissionNames,
                            selectedIndex = primaryMissionIndex,
                            modifier = modifier,
                            preText = "Primary Mission: ",
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
                            preText = "Secondary Mission: ",
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
                            preText = "Deployment: ",
                            onItemClick = {
                                deploymentIndex = it; deploymentName =
                                deploymentNames[deploymentIndex]
                            })
                    }
                    Row {
                        DropDownList(
                            itemList = tournamentNames,
                            selectedIndex = tournamentIndex,
                            modifier = modifier,
                            preText = "Preferred Faction: ",
                            onItemClick = {
                                tournamentIndex = it
                                tournamentID =
                                    TOURNAMENT_VIEW_MODEL.getByName(tournamentNames[tournamentIndex])
                                        .first().tournamentID
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
        val round: RoundWithTournament = coreObject as RoundWithTournament
        val onConfirmation = {
            ROUND_VIEW_MODEL.delete(
                round.round,
                this.getExceptionHandler(
                    errorMessage = "Error removing the round",
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