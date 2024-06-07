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
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.ErrorHandling
import kotlinx.coroutines.launch

class GameCompose(override var errorHandling: ErrorHandling) : CoreObjectCompose {
    override fun canAdd(): Boolean {
        return !(PLAYER_WITH_TEAMS_VIEW_MODEL.allPlayers().isEmpty() ||
                TOURNAMENT_WITH_ROUNDS_VIEW_MODEL.allTournaments().isEmpty())
    }

    @Composable
    override fun AddObject(
        navController: NavController,
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
        val players: List<PlayerWithTeams> = PLAYER_WITH_TEAMS_VIEW_MODEL.allPlayers()
        val predictions: List<Prediction> = PREDICTION_VIEW_MODEL.allPredictions()
        val tournaments: List<TournamentWithRounds> =
            TOURNAMENT_WITH_ROUNDS_VIEW_MODEL.allTournaments()
        val factionNames: List<String> = FACTION_DATA.getDataKeys().toList()

        var player01ID by remember { mutableIntStateOf(0) }
        var predictionID by remember { mutableIntStateOf(0) }
        var tournamentID by remember { mutableIntStateOf(0) }
        var player01Faction by remember { mutableStateOf("") }
        var player02Faction by remember { mutableStateOf("") }
        var roundID by remember { mutableIntStateOf(0) }

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

            errorHandling.provideCoroutineExceptionScope(
                errorMessage = "Error adding the new game"
            ).launch {
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
                        DropDownList(
                            itemList = players.map { it.getDisplayName() },
                            selectedIndex = player01Index,
                            modifier = modifier,
                            preText = "Player 01: ",
                            addEmptyFirstOption = true,
                            firstOptionSelected = (player01ID == 0),
                            onItemClick = { index ->
                                player01Index = index
                                if (player01Index < 0) {
                                    player01Index = 0
                                    player01ID = 0
                                } else {
                                    player01ID = players[player01Index].player.playerID

                                    if (players[player01Index].player.factionName != null) {
                                        player01FactionIndex =
                                            factionNames.indexOf(players[player01Index].player.factionName)
                                        player01Faction = factionNames[player01FactionIndex]
                                    }
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
                            addEmptyFirstOption = true,
                            firstOptionSelected = (player01Faction.isBlank()),
                            onItemClick = { index ->
                                player01FactionIndex = index
                                if (player01FactionIndex < 0) {
                                    player01FactionIndex = 0
                                    player01Faction = ""
                                } else {
                                    player01Faction = factionNames[player01FactionIndex]
                                }
                            }
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = factionNames,
                            selectedIndex = player02FactionIndex,
                            modifier = modifier,
                            preText = "Player 02 Faction: ",
                            addEmptyFirstOption = true,
                            firstOptionSelected = (player02Faction.isBlank()),
                            onItemClick = { index ->
                                player02FactionIndex = index
                                if (player02FactionIndex < 0) {
                                    player02FactionIndex = 0
                                    player02Faction = ""
                                } else {
                                    player02Faction = factionNames[player02FactionIndex]
                                }
                            }
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = predictions.map { it.getDisplayName() },
                            selectedIndex = predictionIndex,
                            modifier = modifier,
                            preText = "Prediction: ",
                            addEmptyFirstOption = true,
                            firstOptionSelected = (predictionID == 0),
                            onItemClick = { index ->
                                predictionIndex = index
                                if (predictionIndex < 0) {
                                    predictionIndex = 0
                                    predictionID = 0
                                } else {
                                    predictionID = predictions[predictionIndex].predictionID
                                }
                            }
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = tournaments.map { it.getDisplayName() },
                            selectedIndex = tournamentIndex,
                            modifier = modifier,
                            preText = "Tournament: ",
                            addEmptyFirstOption = true,
                            firstOptionSelected = (tournamentID == 0),
                            onItemClick = { index ->
                                tournamentIndex = index
                                if (tournamentIndex < 0) {
                                    tournamentIndex = 0
                                    tournamentID = 0
                                    roundID = 0
                                    roundIndex = 0
                                } else {
                                    tournamentID =
                                        tournaments[tournamentIndex].tournament.tournamentID
                                    roundID = 0
                                    roundIndex = 0
                                }
                            }
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = tournaments[tournamentIndex].round.map { it.getDisplayName() },
                            selectedIndex = roundIndex,
                            modifier = modifier,
                            preText = "Round: ",
                            addEmptyFirstOption = true,
                            firstOptionSelected = (roundID == 0),
                            onItemClick = { index ->
                                roundIndex = index
                                if (roundIndex < 0) {
                                    roundIndex = 0
                                    roundID = 0
                                } else {
                                    roundID = tournaments[tournamentIndex].round[roundIndex].roundID
                                }
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
                            modifier = modifier,
                            enabled = ((player01ID != 0) and player01Faction.isNotBlank() and (predictionID != 0) and (roundID != 0))
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
        navController: NavController,
        modifier: Modifier,
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
        navController: NavController,
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
        val game: GameExpanded = coreObject as GameExpanded

        val onConfirmation = {
            errorHandling.provideCoroutineExceptionScope(
                errorMessage = "Error removing the game: ${game.getDisplayName()}"
            ).launch {
                GAME_VIEW_MODEL.delete(game = game.game)
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