package com.example.a40kcc.ui.coreobjects

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.data.`object`.Tournament
import com.example.a40kcc.ui.utilities.ScaledText
import com.example.a40kcc.ui.utilities.TOURNAMENT_VIEW_MODEL
import java.util.Date

class TournamentObject : CoreObjectCompose {
    @Composable
    override fun AddObject(
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
        var tournamentName by remember { mutableStateOf("") }
        var tournamentRounds by remember { mutableIntStateOf(0) }
        val onConfirmation = {
            val newTournament = Tournament(
                name = tournamentName,
                date = Date(),
                roundCount = tournamentRounds
            )
            TOURNAMENT_VIEW_MODEL.insert(newTournament)
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
                            value = tournamentRounds.toString(),
                            onValueChange = { tournamentRounds = it.toInt() },
                            label = { Text(text = "Round Count: ") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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

    override fun canEdit(): Boolean {
        return false
    }

    @Composable
    override fun RemoveObject(
        coreObject: CoreObject,
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
        val tournament: Tournament = coreObject as Tournament
        val onConfirmation = {
            TOURNAMENT_VIEW_MODEL.delete(tournament)
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