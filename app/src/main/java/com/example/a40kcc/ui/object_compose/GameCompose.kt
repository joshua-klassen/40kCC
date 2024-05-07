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
import androidx.navigation.NavController
import com.example.a40kcc.FACTION_DATA
import com.example.a40kcc.GAME_VIEW_MODEL
import com.example.a40kcc.LIVE_ROUND_VIEW_MODEL
import com.example.a40kcc.PLAYER_WITH_TEAMS_VIEW_MODEL
import com.example.a40kcc.PREDICTION_VIEW_MODEL
import com.example.a40kcc.TOURNAMENT_WITH_ROUNDS_VIEW_MODEL
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.data.`object`.Game
import com.example.a40kcc.data.`object`.GameExpanded
import com.example.a40kcc.data.`object`.LiveRound
import com.example.a40kcc.data.`object`.PlayerWithTeams
import com.example.a40kcc.data.`object`.Prediction
import com.example.a40kcc.data.`object`.TournamentWithRounds
import com.example.a40kcc.ui.utilities.ComposeData
import com.example.a40kcc.ui.utilities.DropDownList
import kotlinx.coroutines.launch

class GameCompose : CoreObjectCompose {
    @Composable
    override fun AddObject(
        composeData: ComposeData,
        navController: NavController,
        onDismissRequest: () -> Unit
    ) {
        val players: List<PlayerWithTeams> = PLAYER_WITH_TEAMS_VIEW_MODEL.allPlayers()
        val predictions: List<Prediction> = PREDICTION_VIEW_MODEL.allPredictions()
        val tournaments: List<TournamentWithRounds> =
            TOURNAMENT_WITH_ROUNDS_VIEW_MODEL.allTournaments()
        val factionNames: List<String> = FACTION_DATA.getDataKeys().toList()

        var player01ID by remember { mutableIntStateOf(players[0].player.playerID) }
        var predictionID by remember { mutableIntStateOf(predictions[0].predictionID) }
        var tournamentID by remember { mutableIntStateOf(tournaments[0].tournament.tournamentID) }
        var player01Faction by remember { mutableStateOf(factionNames[0]) }
        var player02Faction by remember { mutableStateOf(factionNames[0]) }
        var roundID by remember { mutableIntStateOf(tournaments[0].round.first().roundID) }

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
            composeData.getScope().launch(
                composeData.getExceptionHandler(
                    errorMessage = "Error adding the new game"
                )
            ) {
                GAME_VIEW_MODEL.insert(game = newGame)
                val lastGame = GAME_VIEW_MODEL.allGames().last()
                LIVE_ROUND_VIEW_MODEL.insert(
                    liveRound = LiveRound(
                        gameID = lastGame.gameID,
                        expectedResult = lastGame.predictionID!!
                    )
                )
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
                        Text(
                            text = "Add a new game"
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = players.map { it.getDisplayName() },
                            selectedIndex = player01Index,
                            modifier = composeData.modifier,
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
                            modifier = composeData.modifier,
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
                            modifier = composeData.modifier,
                            preText = "Player 02 Faction: ",
                            onItemClick = { index ->
                                player02FactionIndex = index
                                player02Faction = factionNames[player02FactionIndex]
                            })
                    }
                    Row {
                        DropDownList(
                            itemList = predictions.map { it.getDisplayName() },
                            selectedIndex = predictionIndex,
                            modifier = composeData.modifier,
                            preText = "Prediction: ",
                            onItemClick = { index ->
                                predictionIndex = index
                                predictionID = predictions[predictionIndex].predictionID
                            })
                    }
                    Row {
                        DropDownList(
                            itemList = tournaments.map { it.getDisplayName() },
                            selectedIndex = tournamentIndex,
                            modifier = composeData.modifier,
                            preText = "Tournament: ",
                            onItemClick = { index ->
                                tournamentIndex = index
                                tournamentID = tournaments[tournamentIndex].tournament.tournamentID
                                roundID = tournaments[tournamentIndex].round.first().roundID
                                roundIndex = 0
                            })
                        DropDownList(
                            itemList = tournaments[tournamentIndex].round.map { it.getDisplayName() },
                            selectedIndex = roundIndex,
                            modifier = composeData.modifier,
                            preText = "Round: ",
                            onItemClick = { index ->
                                roundIndex = index
                                roundID = tournaments[tournamentIndex].round[roundIndex].roundID
                            })
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
        navController: NavController,
        onDismissRequest: () -> Unit
    ) {
        val game: GameExpanded = coreObject as GameExpanded

        navController.navigate(
            route = "editGame?gameId=${game.game.gameID}"
        )
    }

    @Composable
    override fun RemoveObject(
        coreObject: CoreObject,
        composeData: ComposeData,
        navController: NavController,
        onDismissRequest: () -> Unit
    ) {
        val game: GameExpanded = coreObject as GameExpanded

        val onConfirmation = {
            composeData.getScope().launch(
                composeData.getExceptionHandler(
                    errorMessage = "Error removing the game: ${game.getDisplayName()}"
                )
            ) {
                GAME_VIEW_MODEL.delete(game = game.game)
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
                        Text(
                            text = "Confirm remove game",
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