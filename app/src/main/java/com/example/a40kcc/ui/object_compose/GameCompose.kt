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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.data.`object`.CoreObject
import com.example.a40kcc.data.`object`.Game
import com.example.a40kcc.data.`object`.GameExpanded
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.FACTION_DATA
import com.example.a40kcc.ui.utilities.GAME_VIEW_MODEL
import com.example.a40kcc.ui.utilities.OUTCOME_VIEW_MODEL
import com.example.a40kcc.ui.utilities.PLAYER_VIEW_MODEL
import com.example.a40kcc.ui.utilities.PLAYER_WITH_TEAMS_VIEW_MODEL
import com.example.a40kcc.ui.utilities.PREDICTION_VIEW_MODEL
import com.example.a40kcc.ui.utilities.ROUND_VIEW_MODEL
import com.example.a40kcc.ui.utilities.TOURNAMENT_VIEW_MODEL

class GameCompose : CoreObjectCompose {
    @Composable
    override fun AddObject(
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
        val localContext = LocalContext.current
        var player01ID by remember { mutableIntStateOf(0) }
        var player01Faction by remember { mutableStateOf("") }
        var player02Faction by remember { mutableStateOf("") }
        var predictionID by remember { mutableIntStateOf(0) }
        var tournamentID by remember { mutableIntStateOf(0) }
        var roundID by remember { mutableIntStateOf(0) }
        var player01Index by remember { mutableIntStateOf(0) }
        var player01FactionIndex by remember { mutableIntStateOf(0) }
        var player02FactionIndex by remember { mutableIntStateOf(0) }
        var predictionIndex by remember { mutableIntStateOf(0) }
        var tournamentIndex by remember { mutableIntStateOf(0) }
        var roundIndex by remember { mutableIntStateOf(0) }
        val playerNames: MutableList<String> = mutableListOf("")
        val factionNames: List<String> = listOf("") + FACTION_DATA.getDataKeys().toList()
        val predictionNames: MutableList<String> = mutableListOf("")
        val tournamentNames: MutableList<String> = mutableListOf("")
        var tournamentRounds: MutableMap<String, Int> = mutableMapOf(Pair("", 0))

        PLAYER_VIEW_MODEL.allPlayers.forEach {
            playerNames += it.name
        }
        PREDICTION_VIEW_MODEL.allPredictions.forEach {
            predictionNames += it.name
        }
        TOURNAMENT_VIEW_MODEL.allTournaments.forEach {
            tournamentNames += it.name
        }
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
                        Text(
                            text = "Add a new game"
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = playerNames,
                            selectedIndex = player01Index,
                            modifier = modifier,
                            preText = "Player 01: ",
                            onItemClick = {
                                player01Index = it
                                val player =
                                    PLAYER_WITH_TEAMS_VIEW_MODEL.getByName(playerNames[player01Index])
                                player01ID = player.player.playerID

                                if (player.player.factionName != null) {
                                    player01FactionIndex =
                                        factionNames.indexOf(player.player.factionName)
                                }
                            })
                    }
                    Row {
                        DropDownList(
                            itemList = factionNames,
                            selectedIndex = player01FactionIndex,
                            modifier = modifier,
                            preText = "Player 01 Faction: ",
                            onItemClick = {
                                player01FactionIndex = it; player01Faction =
                                factionNames[player01FactionIndex]
                            })
                    }
                    Row {
                        DropDownList(
                            itemList = factionNames,
                            selectedIndex = player02FactionIndex,
                            modifier = modifier,
                            preText = "Player 02 Faction: ",
                            onItemClick = {
                                player02FactionIndex = it; player02Faction =
                                factionNames[player02FactionIndex]
                            })
                    }
                    Row {
                        DropDownList(
                            itemList = predictionNames,
                            selectedIndex = predictionIndex,
                            modifier = modifier,
                            preText = "Prediction: ",
                            onItemClick = {
                                predictionIndex = it; predictionID =
                                PREDICTION_VIEW_MODEL.getByName(predictionNames[predictionIndex]).predictionID
                            })
                    }
                    Row {
                        DropDownList(
                            itemList = tournamentNames,
                            selectedIndex = tournamentIndex,
                            modifier = modifier,
                            preText = "Tournament: ",
                            onItemClick = {
                                tournamentIndex = it; tournamentID =
                                TOURNAMENT_VIEW_MODEL.getByName(tournamentNames[tournamentIndex])
                                    .first().tournamentID
                                tournamentRounds = mutableMapOf(Pair("", 0))
                                ROUND_VIEW_MODEL.getByTournamentId(tournamentID).forEach { round ->
                                    tournamentRounds += mutableMapOf(
                                        Pair(
                                            round.number.toString(),
                                            round.roundID
                                        )
                                    )
                                }
                            })
                        DropDownList(
                            itemList = tournamentRounds.keys.toList(),
                            selectedIndex = roundIndex,
                            modifier = modifier,
                            preText = "Round: ",
                            onItemClick = {
                                roundIndex = it; roundID =
                                ROUND_VIEW_MODEL.getById(tournamentRounds[tournamentRounds.keys.toList()[roundIndex]]!!).roundID
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
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
        val localContext = LocalContext.current
        val game: GameExpanded = coreObject as GameExpanded
        var player01ID by remember { mutableIntStateOf(game.game.player01ID) }
        var player01Faction by remember { mutableStateOf(game.game.player01FactionName) }
        var player02Faction by remember { mutableStateOf(game.game.player02FactionName) }
        var predictionID by remember {
            if (game.game.predictionID != null) mutableIntStateOf(game.game.predictionID) else mutableIntStateOf(
                0
            )
        }
        var outcomeID by remember {
            if (game.game.outcomeID != null) mutableIntStateOf(game.game.outcomeID) else mutableIntStateOf(
                0
            )
        }
        var tournamentID by remember { mutableIntStateOf(game.round.round.tournamentID) }
        var roundID by remember { mutableIntStateOf(game.game.roundID) }
        var player01Index by remember { mutableIntStateOf(0) }
        var player01FactionIndex by remember { mutableIntStateOf(0) }
        var player02FactionIndex by remember { mutableIntStateOf(0) }
        var predictionIndex by remember { mutableIntStateOf(0) }
        var outcomeIndex by remember { mutableIntStateOf(0) }
        var tournamentIndex by remember { mutableIntStateOf(0) }
        var roundIndex by remember { mutableIntStateOf(0) }
        val playerNames: MutableList<String> = mutableListOf("")
        val factionNames: List<String> = listOf("") + FACTION_DATA.getDataKeys().toList()
        val predictionNames: MutableList<String> = mutableListOf("")
        val outcomes: MutableList<String> = mutableListOf("")
        val tournamentNames: MutableList<String> = mutableListOf("")
        var tournamentRounds: MutableMap<String, Int> = mutableMapOf(Pair("", 0))
        PLAYER_VIEW_MODEL.allPlayers.forEach {
            playerNames += it.name
            if (it.playerID == player01ID) {
                player01Index = playerNames.lastIndex
            }
        }
        player01FactionIndex = factionNames.indexOf(player01Faction)
        player02FactionIndex = factionNames.indexOf(player02Faction)
        PREDICTION_VIEW_MODEL.allPredictions.forEach {
            predictionNames += it.name
            if (it.predictionID == predictionID) {
                predictionIndex = predictionNames.lastIndex
            }
        }
        TOURNAMENT_VIEW_MODEL.allTournaments.forEach {
            tournamentNames += it.name
            if (it.tournamentID == tournamentID) {
                tournamentIndex = tournamentNames.lastIndex
            }
        }
        if (tournamentIndex != 0) {
            ROUND_VIEW_MODEL.getByTournamentId(tournamentID).forEach { round ->
                tournamentRounds += mutableMapOf(Pair(round.number.toString(), round.roundID))
                if (round.roundID == roundID) {
                    roundIndex = tournamentRounds.keys.toList().lastIndex
                }
            }
        }
        OUTCOME_VIEW_MODEL.getByPlayerId(player01ID).forEach { outcome ->
            outcomes += outcome.outcomeID.toString()
        }
        val onConfirmation = {
            val updatedGame = Game(
                gameID = game.game.gameID,
                player01ID = player01ID,
                player01FactionName = player01Faction,
                player02FactionName = player02Faction,
                predictionID = predictionID,
                roundID = roundID,
                outcomeID = outcomeID
            )
            GAME_VIEW_MODEL.update(
                game = updatedGame,
                this.getExceptionHandler(
                    errorMessage = "Error updating the game",
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
                        Text(
                            text = "Add a new game"
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = playerNames,
                            selectedIndex = player01Index,
                            modifier = modifier,
                            preText = "Player 01: ",
                            onItemClick = {
                                player01Index = it
                                player01ID =
                                    PLAYER_VIEW_MODEL.getByName(playerNames[player01Index]).playerID

                                OUTCOME_VIEW_MODEL.getByPlayerId(player01ID).forEach { outcome ->
                                    outcomes += outcome.outcomeID.toString()
                                }
                            })
                    }
                    Row {
                        DropDownList(
                            itemList = outcomes,
                            selectedIndex = outcomeIndex,
                            modifier = modifier,
                            preText = "Outcome: ",
                            onItemClick = {
                                outcomeIndex = it
                                outcomeID = outcomes[outcomeIndex].toInt()
                            })
                    }
                    Row {
                        DropDownList(
                            itemList = factionNames,
                            selectedIndex = player01FactionIndex,
                            modifier = modifier,
                            preText = "Player 01 Faction: ",
                            onItemClick = {
                                player01FactionIndex = it
                                player01Faction = factionNames[player01FactionIndex]
                            })
                    }
                    Row {
                        DropDownList(
                            itemList = factionNames,
                            selectedIndex = player02FactionIndex,
                            modifier = modifier,
                            preText = "Player 02 Faction: ",
                            onItemClick = {
                                player02FactionIndex = it
                                player02Faction = factionNames[player02FactionIndex]
                            })
                    }
                    Row {
                        DropDownList(
                            itemList = predictionNames,
                            selectedIndex = predictionIndex,
                            modifier = modifier,
                            preText = "Prediction: ",
                            onItemClick = {
                                predictionIndex = it
                                predictionID =
                                    PREDICTION_VIEW_MODEL.getByName(predictionNames[predictionIndex]).predictionID
                            })
                    }
                    Row {
                        DropDownList(
                            itemList = tournamentNames,
                            selectedIndex = tournamentIndex,
                            modifier = modifier,
                            preText = "Tournament: ",
                            onItemClick = {
                                tournamentIndex = it
                                tournamentID =
                                    TOURNAMENT_VIEW_MODEL.getByName(tournamentNames[tournamentIndex])
                                        .first().tournamentID
                                tournamentRounds = mutableMapOf(Pair("", 0))
                                ROUND_VIEW_MODEL.getByTournamentId(tournamentID).forEach { round ->
                                    tournamentRounds += mutableMapOf(
                                        Pair(
                                            round.number.toString(),
                                            round.roundID
                                        )
                                    )
                                }
                            })
                        DropDownList(
                            itemList = tournamentRounds.keys.toList(),
                            selectedIndex = roundIndex,
                            modifier = modifier,
                            preText = "Round: ",
                            onItemClick = {
                                roundIndex = it
                                roundID =
                                    ROUND_VIEW_MODEL.getById(tournamentRounds[tournamentRounds.keys.toList()[roundIndex]]!!).roundID
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
    override fun RemoveObject(
        coreObject: CoreObject,
        modifier: Modifier,
        onDismissRequest: () -> Unit
    ) {
        val localContext = LocalContext.current
        val game: GameExpanded = coreObject as GameExpanded
        val onConfirmation = {
            GAME_VIEW_MODEL.delete(
                game = game.game,
                this.getExceptionHandler(
                    errorMessage = "Error removing the game",
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