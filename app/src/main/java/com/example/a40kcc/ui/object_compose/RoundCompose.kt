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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.DEPLOYMENT_DATA
import com.example.a40kcc.PRIMARY_MISSION_DATA
import com.example.a40kcc.ROUND_VIEW_MODEL
import com.example.a40kcc.SECONDARY_MISSION_DATA
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.ErrorHandling
import com.example.a40kcc.ui.utilities.ScaledText
import kotlinx.coroutines.launch

@Composable
fun AddRound(
    roundNumber: Int,
    modifier: Modifier,
    onDismissRequest: (round: Round?) -> Unit
) {
    var primaryMissionName by remember { mutableStateOf("") }
    var secondaryMissionName by remember { mutableStateOf("") }
    var deploymentName by remember { mutableStateOf("") }
    var primaryMissionIndex by remember { mutableIntStateOf(0) }
    var secondaryMissionIndex by remember { mutableIntStateOf(0) }
    var deploymentIndex by remember { mutableIntStateOf(0) }
    val primaryMissionNames: List<String> =
        listOf("") + PRIMARY_MISSION_DATA.getDataKeys().toList()
    val secondaryMissionNames: List<String> =
        listOf("") + SECONDARY_MISSION_DATA.getDataKeys().toList()
    val deploymentNames: List<String> = listOf("") + DEPLOYMENT_DATA.getDataKeys().toList()

    val onConfirmation = {
        val newRound = Round(
            number = roundNumber,
            primaryMissionName = primaryMissionName,
            secondaryMissionName = secondaryMissionName,
            deploymentName = deploymentName
        )

        onDismissRequest(newRound)
    }

    Dialog(onDismissRequest = { onDismissRequest(null) }) {
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
                    TextButton(
                        onClick = { onDismissRequest(null) },
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
fun EditRound(
    round: Round,
    errorHandling: ErrorHandling,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    var primaryMissionName by remember { mutableStateOf(round.primaryMissionName) }
    var secondaryMissionName by remember { mutableStateOf(round.secondaryMissionName) }
    var deploymentName by remember { mutableStateOf(round.deploymentName) }
    var primaryMissionIndex by remember { mutableIntStateOf(0) }
    var secondaryMissionIndex by remember { mutableIntStateOf(0) }
    var deploymentIndex by remember { mutableIntStateOf(0) }
    val primaryMissionNames: List<String> =
        listOf("") + PRIMARY_MISSION_DATA.getDataKeys().toList()
    val secondaryMissionNames: List<String> =
        listOf("") + SECONDARY_MISSION_DATA.getDataKeys().toList()
    val deploymentNames: List<String> = listOf("") + DEPLOYMENT_DATA.getDataKeys().toList()

    if (primaryMissionNames.contains(primaryMissionName)) {
        primaryMissionIndex = primaryMissionNames.indexOf(primaryMissionName)
    }

    if (secondaryMissionNames.contains(secondaryMissionName)) {
        secondaryMissionIndex = secondaryMissionNames.indexOf(secondaryMissionName)
    }

    if (deploymentNames.contains(deploymentName)) {
        deploymentIndex = deploymentNames.indexOf(deploymentName)
    }

    val onConfirmation = {
        val newRound = Round(
            roundID = round.roundID,
            number = round.number,
            primaryMissionName = primaryMissionName,
            secondaryMissionName = secondaryMissionName,
            deploymentName = deploymentName
        )

        errorHandling.provideCoroutineExceptionScope(
            errorMessage = "Error updating the round: ${round.getDisplayName()}"
        ).launch {
            ROUND_VIEW_MODEL.update(newRound)
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
                        text = "Round ${round.number} Data"
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
fun RemoveRound(
    round: Round,
    errorHandling: ErrorHandling,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    val onConfirmation = {
        errorHandling.provideCoroutineExceptionScope(
            errorMessage = "Error removing the round: ${round.getDisplayName()}"
        ).launch {
            ROUND_VIEW_MODEL.delete(round)
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
                        text = "Confirm remove round ${round.getDisplayName()}",
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