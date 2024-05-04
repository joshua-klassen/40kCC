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
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.FACTION_DATA
import com.example.a40kcc.GAME_VIEW_MODEL
import com.example.a40kcc.OUTCOME_VIEW_MODEL
import com.example.a40kcc.PLAYER_WITH_TEAMS_VIEW_MODEL
import com.example.a40kcc.PREDICTION_VIEW_MODEL
import com.example.a40kcc.ROUND_VIEW_MODEL
import com.example.a40kcc.TOURNAMENT_VIEW_MODEL
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.data.`object`.Game
import com.example.a40kcc.data.`object`.GameExpanded
import com.example.a40kcc.data.`object`.PlayerWithTeams
import com.example.a40kcc.data.`object`.Prediction
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.data.`object`.Tournament
import com.example.a40kcc.ui.utilities.ComposeData
import com.example.a40kcc.ui.utilities.CoreObjectDropDownList
import com.example.a40kcc.ui.utilities.DropDownList

class GameCompose : CoreObjectCompose {
    @Composable
    override fun AddObject(
        composeData: ComposeData,
        onDismissRequest: () -> Unit
    ) {
        val modifier = composeData.modifier

        val players: List<PlayerWithTeams> = PLAYER_WITH_TEAMS_VIEW_MODEL.allPlayers()
        val predictions: List<Prediction> = PREDICTION_VIEW_MODEL.allPredictions()
        val tournaments: List<Tournament> = TOURNAMENT_VIEW_MODEL.allTournaments()
        val factionNames: List<String> = FACTION_DATA.getDataKeys().toList()

        var player01ID by remember { mutableIntStateOf(players[0].player.playerID) }
        var predictionID by remember { mutableIntStateOf(predictions[0].predictionID) }
        var tournamentID by remember { mutableIntStateOf(tournaments[0].tournamentID) }
        var player01Faction by remember { mutableStateOf(factionNames[0]) }
        var player02Faction by remember { mutableStateOf(factionNames[0]) }
        var rounds: List<Round> = ROUND_VIEW_MODEL.getByTournamentId(tournamentID)
        var roundID by remember { mutableIntStateOf(rounds.first().roundID) }

        var player01Index by remember { mutableIntStateOf(0) }
        var player01FactionIndex by remember { mutableIntStateOf(0) }
        var player02FactionIndex by remember { mutableIntStateOf(0) }
        var predictionIndex by remember { mutableIntStateOf(0) }
        var tournamentIndex by remember { mutableIntStateOf(0) }
        var roundIndex by remember { mutableIntStateOf(0) }

        val onConfirmation = {
            val newGame = Game(
                player01ID = player01ID,
                player01FactionName = player01Faction,
                player02FactionName = player02Faction,
                predictionID = predictionID,
                roundID = roundID
            )
            GAME_VIEW_MODEL.insert(
                game = newGame,
                this.getExceptionHandler(
                    errorMessage = "Error adding the new game",
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
                            text = "Add a new game"
                        )
                    }
                    Row {
                        CoreObjectDropDownList(
                            itemList = players,
                            selectedIndex = player01Index,
                            modifier = modifier,
                            preText = "Player 01: ",
                            onItemClick = { index ->
                                player01Index = index
                                player01ID = players[player01Index].player.playerID

                                if (players[player01Index].player.factionName != null) {
                                    player01FactionIndex =
                                        factionNames.indexOf(players[player01Index].player.factionName)
                                }
                            }
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = factionNames,
                            selectedIndex = player01FactionIndex,
                            modifier = modifier,
                            preText = "Player 01 Faction: ",
                            onItemClick = { index ->
                                player01FactionIndex = index
                                player01Faction = factionNames[player01FactionIndex]
                            })
                    }
                    Row {
                        DropDownList(
                            itemList = factionNames,
                            selectedIndex = player02FactionIndex,
                            modifier = modifier,
                            preText = "Player 02 Faction: ",
                            onItemClick = { index ->
                                player02FactionIndex = index
                                player02Faction = factionNames[player02FactionIndex]
                            })
                    }
                    Row {
                        CoreObjectDropDownList(
                            itemList = predictions,
                            selectedIndex = predictionIndex,
                            modifier = modifier,
                            preText = "Prediction: ",
                            onItemClick = { index ->
                                predictionIndex = index
                                predictionID = predictions[predictionIndex].predictionID
                            })
                    }
                    Row {
                        CoreObjectDropDownList(
                            itemList = tournaments,
                            selectedIndex = tournamentIndex,
                            modifier = modifier,
                            preText = "Tournament: ",
                            onItemClick = { index ->
                                tournamentIndex = index
                                tournamentID = tournaments[tournamentIndex].tournamentID
                                rounds = ROUND_VIEW_MODEL.getByTournamentId(tournamentID)
                                roundID = rounds.first().roundID
                                roundIndex = 0
                            })
                        CoreObjectDropDownList(
                            itemList = rounds,
                            selectedIndex = roundIndex,
                            modifier = modifier,
                            preText = "Round: ",
                            onItemClick = { index ->
                                roundIndex = index
                                roundID = rounds[roundIndex].roundID
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
        val outcomes: MutableList<String> = mutableListOf("")

        val game: GameExpanded = coreObject as GameExpanded
        val modifier = composeData.modifier

        val players: List<PlayerWithTeams> = PLAYER_WITH_TEAMS_VIEW_MODEL.allPlayers()
        val predictions: List<Prediction> = PREDICTION_VIEW_MODEL.allPredictions()
        val tournaments: List<Tournament> = TOURNAMENT_VIEW_MODEL.allTournaments()
        val factionNames: List<String> = FACTION_DATA.getDataKeys().toList()

        var player01ID by remember { mutableIntStateOf(game.game.player01ID) }
        var predictionID by remember {
            if (game.game.predictionID != null) {
                mutableIntStateOf(game.game.predictionID)
            } else {
                mutableIntStateOf(0)
            }
        }
        var tournamentID by remember { mutableIntStateOf(game.round.round.tournamentID) }
        var player01Faction by remember { mutableStateOf(game.game.player01FactionName) }
        var player02Faction by remember { mutableStateOf(game.game.player02FactionName) }
        var rounds: List<Round> = ROUND_VIEW_MODEL.getByTournamentId(tournamentID)
        var roundID by remember { mutableIntStateOf(game.game.roundID) }
        var outcomeID by remember {
            if (game.game.outcomeID != null) {
                mutableIntStateOf(game.game.outcomeID)
            } else {
                mutableIntStateOf(0)
            }
        }

        var player01Index by remember { mutableIntStateOf(0) }
        var player01FactionIndex by remember { mutableIntStateOf(0) }
        var player02FactionIndex by remember { mutableIntStateOf(0) }
        var predictionIndex by remember { mutableIntStateOf(0) }
        var tournamentIndex by remember { mutableIntStateOf(0) }
        var roundIndex by remember { mutableIntStateOf(0) }
        var outcomeIndex by remember { mutableIntStateOf(0) }

        if (players.contains(game.player01)) {
            player01Index = players.indexOf(game.player01)
        }

        if (predictions.contains(game.prediction)) {
            predictionIndex = predictions.indexOf(game.prediction)
        }

        if (tournaments.contains(game.round.tournament)) {
            tournamentIndex = tournaments.indexOf(game.round.tournament)
        }

        player01FactionIndex = factionNames.indexOf(player01Faction)
        player02FactionIndex = factionNames.indexOf(player02Faction)

        OUTCOME_VIEW_MODEL.getByPlayerId(player01ID).forEach { outcome ->
            outcomes += outcome.outcomeID.toString()
        }

        val onConfirmation = {
            var outcome: Int? = outcomeID
            if (outcome == 0) {
                outcome = null
            }

            val updatedGame = Game(
                gameID = game.game.gameID,
                player01ID = player01ID,
                player01FactionName = player01Faction,
                player02FactionName = player02Faction,
                predictionID = predictionID,
                roundID = roundID,
                outcomeID = outcome
            )
            GAME_VIEW_MODEL.update(
                game = updatedGame,
                this.getExceptionHandler(
                    errorMessage = "Error updating the game",
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
                            text = "Add a new game"
                        )
                    }
                    Row {
                        CoreObjectDropDownList(
                            itemList = players,
                            selectedIndex = player01Index,
                            modifier = modifier,
                            preText = "Player 01: ",
                            onItemClick = { index ->
                                player01Index = index
                                player01ID = players[player01Index].player.playerID

                                OUTCOME_VIEW_MODEL.getByPlayerId(player01ID).forEach { outcome ->
                                    outcomes += outcome.outcomeID.toString()
                                }
                            }
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = outcomes,
                            selectedIndex = outcomeIndex,
                            modifier = modifier,
                            preText = "Outcome: ",
                            onItemClick = { index ->
                                outcomeIndex = index
                                outcomeID = outcomes[outcomeIndex].toIntOrNull() ?: 0
                            }
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = factionNames,
                            selectedIndex = player01FactionIndex,
                            modifier = modifier,
                            preText = "Player 01 Faction: ",
                            onItemClick = { index ->
                                player01FactionIndex = index
                                player01Faction = factionNames[player01FactionIndex]
                            }
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = factionNames,
                            selectedIndex = player02FactionIndex,
                            modifier = modifier,
                            preText = "Player 02 Faction: ",
                            onItemClick = { index ->
                                player02FactionIndex = index
                                player02Faction = factionNames[player02FactionIndex]
                            }
                        )
                    }
                    Row {
                        CoreObjectDropDownList(
                            itemList = predictions,
                            selectedIndex = predictionIndex,
                            modifier = modifier,
                            preText = "Prediction: ",
                            onItemClick = { index ->
                                predictionIndex = index
                                predictionID = predictions[predictionIndex].predictionID
                            }
                        )
                    }
                    Row {
                        CoreObjectDropDownList(
                            itemList = tournaments,
                            selectedIndex = tournamentIndex,
                            modifier = modifier,
                            preText = "Tournament: ",
                            onItemClick = { index ->
                                tournamentIndex = index
                                tournamentID = tournaments[tournamentIndex].tournamentID
                                rounds = ROUND_VIEW_MODEL.getByTournamentId(tournamentID)
                                roundID = rounds.first().roundID
                                roundIndex = 0
                            }
                        )
                        CoreObjectDropDownList(
                            itemList = rounds,
                            selectedIndex = roundIndex,
                            modifier = modifier,
                            preText = "Round: ",
                            onItemClick = { index ->
                                roundIndex = index
                                roundID = rounds[roundIndex].roundID
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
        composeData: ComposeData,
        onDismissRequest: () -> Unit
    ) {
        val modifier = composeData.modifier
        val game: GameExpanded = coreObject as GameExpanded

        val onConfirmation = {
            GAME_VIEW_MODEL.delete(
                game = game.game,
                this.getExceptionHandler(
                    errorMessage = "Error removing the game",
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
                            text = "Confirm remove game",
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