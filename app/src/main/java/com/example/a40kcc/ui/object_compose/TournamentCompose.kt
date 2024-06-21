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
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.a40kcc.GAME_VIEW_MODEL
import com.example.a40kcc.TOURNAMENT_VIEW_MODEL
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.data.`object`.Tournament
import com.example.a40kcc.ui.utilities.ErrorHandling
import com.example.a40kcc.ui.utilities.ScaledText
import kotlinx.coroutines.launch

class TournamentCompose(override var errorHandling: ErrorHandling) : CoreObjectCompose {
    @Composable
    override fun AddObject(
        navController: NavController,
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
        navController.navigate(
            route = "addTournament"
        )
    }

    override fun canEdit(coreObject: CoreObject): Boolean {
        val tournament: Tournament = coreObject as Tournament

        val games = GAME_VIEW_MODEL.getGameByTournamentId(tournament.tournamentID)

        return games.isEmpty()
    }

    @Composable
    override fun EditObject(
        coreObject: CoreObject,
        navController: NavController,
        modifier: Modifier,
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
        navController: NavController,
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
        val tournament: Tournament = coreObject as Tournament

        val onConfirmation = {
            errorHandling.provideCoroutineExceptionScope(
                errorMessage = "Error removing the tournament: ${tournament.getDisplayName()}"
            ).launch {
                TOURNAMENT_VIEW_MODEL.delete(tournament)
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
                            text = "Confirm remove tournament ${tournament.getDisplayName()}",
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