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
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.a40kcc.TOURNAMENT_VIEW_MODEL
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.data.`object`.Tournament
import com.example.a40kcc.ui.utilities.ComposeData
import com.example.a40kcc.ui.utilities.ScaledText
import kotlinx.coroutines.launch

class TournamentCompose : CoreObjectCompose {
    @Composable
    override fun AddObject(
        composeData: ComposeData,
        navController: NavController,
        onDismissRequest: () -> Unit
    ) {
        navController.navigate(
            route = "addTournament"
        )
    }

    @Composable
    override fun EditObject(
        coreObject: CoreObject,
        composeData: ComposeData,
        navController: NavController,
        onDismissRequest: () -> Unit
    ) {
        val tournament: Tournament = coreObject as Tournament

        navController.navigate(
            route = "editTournament?tournamentId=${tournament.tournamentID}"
        )
    }

    @Composable
    override fun RemoveObject(
        coreObject: CoreObject,
        composeData: ComposeData,
        navController: NavController,
        onDismissRequest: () -> Unit
    ) {
        val tournament: Tournament = coreObject as Tournament

        val onConfirmation = {
            composeData.getScope().launch(
                composeData.getExceptionHandler(
                    errorMessage = "Error removing the tournament: ${tournament.getDisplayName()}"
                )
            ) {
                TOURNAMENT_VIEW_MODEL.delete(tournament)
                onDismissRequest()
            }
        }

        Dialog(onDismissRequest = { onDismissRequest() }) {
            Card(
                modifier = composeData.modifier.wrapContentSize()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = composeData.modifier.fillMaxWidth()
                ) {
                    Row {
                        ScaledText(
                            text = "Confirm remove tournament ${tournament.getDisplayName()}",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    Row {
                        TextButton(
                            onClick = { onDismissRequest() },
                            modifier = composeData.modifier
                        ) {
                            Text(
                                text = "Cancel",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                        TextButton(
                            onClick = { onConfirmation() },
                            modifier = composeData.modifier
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