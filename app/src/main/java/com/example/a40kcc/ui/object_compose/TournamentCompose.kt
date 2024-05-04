package com.example.a40kcc.ui.object_compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.DEPLOYMENT_DATA
import com.example.a40kcc.PRIMARY_MISSION_DATA
import com.example.a40kcc.ROUND_VIEW_MODEL
import com.example.a40kcc.SECONDARY_MISSION_DATA
import com.example.a40kcc.TOURNAMENT_VIEW_MODEL
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.data.`object`.Tournament
import com.example.a40kcc.ui.utilities.ComposeData
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.ScaledText
import java.util.Date

class TournamentCompose : CoreObjectCompose {
    @Composable
    override fun AddObject(
        composeData: ComposeData,
        onDismissRequest: () -> Unit
    ) {
        val modifier = composeData.modifier
        var tournamentName by remember { mutableStateOf("") }
        var tournamentRounds by remember { mutableStateOf("") }

        val onConfirmation = {
            val newTournament = Tournament(
                name = tournamentName,
                date = Date(),
                roundCount = tournamentRounds.toIntOrNull() ?: 0
            )
            TOURNAMENT_VIEW_MODEL.insert(
                newTournament,
                this.getExceptionHandler(
                    errorMessage = "Error adding the new tournament $tournamentName",
                    composeData = composeData,
                    continueRun = true
                )
            )
            onDismissRequest()
        }
        Dialog(onDismissRequest = { onDismissRequest() }) {
            val scrollState = rememberScrollState()
            Card(
                modifier = modifier
                    .wrapContentSize()
                    .verticalScroll(scrollState)
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Row {
                        ScaledText(
                            text = "Add a new tournament"
                        )
                    }
                    Row {
                        TextField(
                            value = tournamentName,
                            onValueChange = { tournamentName = it },
                            label = { Text(text = "Name: ") },
                            textStyle = MaterialTheme.typography.bodyMedium
                        )
                    }
                    Row {
                        TextField(
                            value = tournamentRounds,
                            onValueChange = { tournamentRounds = it },
                            label = { Text(text = "Round Count: ") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            textStyle = MaterialTheme.typography.bodyMedium
                        )
                    }
                    for (roundNumber in 1..(tournamentRounds.toIntOrNull() ?: 0)) {
                        AddRoundObject(roundNumber = roundNumber, composeData = composeData)
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
    fun AddRoundObject(
        roundNumber: Int,
        composeData: ComposeData
    ) {
        val modifier = composeData.modifier
        var showButtons by remember { mutableStateOf(true) }
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

        TOURNAMENT_VIEW_MODEL.allTournaments().forEach { tournament ->
            tournamentNames += tournament.name
        }

        val onDismissRequest = {
            showButtons = !showButtons
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
                    composeData = composeData,
                    continueRun = true
                )
            )
            onDismissRequest()
        }

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
                        text = "Round $roundNumber Data"
                    )
                }
                Row {
                    DropDownList(
                        itemList = primaryMissionNames,
                        selectedIndex = primaryMissionIndex,
                        modifier = modifier,
                        preText = "Primary Mission: ",
                        onItemClick = { index ->
                            primaryMissionIndex = index
                            primaryMissionName = primaryMissionNames[primaryMissionIndex]
                        })
                }
                Row {
                    DropDownList(
                        itemList = secondaryMissionNames,
                        selectedIndex = secondaryMissionIndex,
                        modifier = modifier,
                        preText = "Secondary Mission: ",
                        onItemClick = { index ->
                            secondaryMissionIndex = index
                            secondaryMissionName = secondaryMissionNames[secondaryMissionIndex]
                        })
                }
                Row {
                    DropDownList(
                        itemList = deploymentNames,
                        selectedIndex = deploymentIndex,
                        modifier = modifier,
                        preText = "Deployment: ",
                        onItemClick = { index ->
                            deploymentIndex = index
                            deploymentName = deploymentNames[deploymentIndex]
                        })
                }
                Row {
                    DropDownList(
                        itemList = tournamentNames,
                        selectedIndex = tournamentIndex,
                        modifier = modifier,
                        preText = "Tournament: ",
                        onItemClick = { index ->
                            tournamentIndex = index
                            if (index != 0) {
                                tournamentID =
                                    TOURNAMENT_VIEW_MODEL.getByName(tournamentNames[tournamentIndex])
                                        .first().tournamentID
                            }
                        })
                }
                if (showButtons) {
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
        composeData: ComposeData,
        onDismissRequest: () -> Unit
    ) {
        val modifier = composeData.modifier
        val tournament: Tournament = coreObject as Tournament

        val onConfirmation = {
            TOURNAMENT_VIEW_MODEL.delete(
                tournament,
                this.getExceptionHandler(
                    errorMessage = "Error removing the tournament ${tournament.name}",
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
                            text = "Confirm remove tournament",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Row {
                        Text(
                            text = "Tournament Name: " + tournament.name,
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