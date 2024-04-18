package com.example.a40kcc.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.Game
import com.example.a40kcc.data.`object`.GameExpanded
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.FACTION_DATA
import com.example.a40kcc.ui.utilities.GAME_EXPANDED_VIEW_MODEL
import com.example.a40kcc.ui.utilities.GAME_VIEW_MODEL
import com.example.a40kcc.ui.utilities.OUTCOME_VIEW_MODEL
import com.example.a40kcc.ui.utilities.PLAYER_VIEW_MODEL
import com.example.a40kcc.ui.utilities.PLAYER_WITH_TEAMS_VIEW_MODEL
import com.example.a40kcc.ui.utilities.PREDICTION_VIEW_MODEL
import com.example.a40kcc.ui.utilities.ROUND_VIEW_MODEL
import com.example.a40kcc.ui.utilities.ScaledText
import com.example.a40kcc.ui.utilities.TOURNAMENT_VIEW_MODEL

@Composable
fun GameScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var addGame by remember { mutableStateOf(false) }
    Scaffold(topBar = {
        Button(
            onClick = onBackClick,
            modifier = modifier
        ) {
            Column {
                Text(text = stringResource(id = R.string.home_button))
            }
        }
    },
        bottomBar = {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                FloatingActionButton(
                    onClick = {
                        addGame = !addGame
                    },
                    modifier = modifier.align(Alignment.End)
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Game")

                    if (addGame) {
                        AddGame(
                            modifier = modifier,
                            onDismissRequest = { addGame = !addGame }
                        )
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            val games: List<GameExpanded>? =
                GAME_EXPANDED_VIEW_MODEL.allGamesFlow.observeAsState().value

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = modifier
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    ScaledText(
                        text = "Player 01",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Column(
                    modifier = modifier
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    ScaledText(
                        text = "Player 02",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Column(
                    modifier = modifier
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    ScaledText(
                        text = "Prediction",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Column(
                    modifier = modifier
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    ScaledText(
                        text = "Round Number",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }

            if (games != null) {
                GameScreen(
                    games = games,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
private fun GameScreen(
    games: List<GameExpanded>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(items = games) { game ->
            var showDetails by remember { mutableStateOf(false) }
            var removeGame by remember { mutableStateOf(false) }
            var editGame by remember { mutableStateOf(false) }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = modifier
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    ScaledText(
                        text = game.player01.player.name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                            .clickable(enabled = true, onClick = {
                                showDetails = !showDetails
                            })
                    )
                }
                Column(
                    modifier = modifier
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    if (game.player02 != null) {
                        ScaledText(
                            text = game.player02.player.name,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = modifier
                        )
                    }
                }
                Column(
                    modifier = modifier
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    ScaledText(
                        text = game.prediction.name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                            .background(Color(game.prediction.color))
                            .clickable(enabled = true, onClick = {
                                showDetails = !showDetails
                            })
                    )
                }
                Column(
                    modifier = modifier
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    ScaledText(
                        text = game.round.round.number.toString(),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                            .clickable(enabled = true, onClick = {
                                showDetails = !showDetails
                            })
                    )
                }
                Column {
                    if (showDetails) {
                        SmallFloatingActionButton(
                            onClick = {
                                removeGame = !removeGame
                            },
                            modifier = modifier.align(Alignment.End)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "Remove Game"
                            )

                            if (removeGame) {
                                RemoveGame(
                                    game = game,
                                    modifier = modifier,
                                    onDismissRequest = { removeGame = !removeGame }
                                )
                            }
                        }
                        SmallFloatingActionButton(
                            onClick = {
                                editGame = !editGame
                            },
                            modifier = modifier.align(Alignment.End)
                        ) {
                            Icon(imageVector = Icons.Filled.Build, contentDescription = "Edit Game")

                            if (editGame) {
                                EditGame(
                                    game = game,
                                    modifier = modifier,
                                    onDismissRequest = { editGame = !editGame }
                                )
                            }
                        }
                    }
                }
            }

            if (showDetails) {
                GameDetailScreen(
                    game = game,
                    modifier = modifier
                )
            }
        }
    }
}

@Composable
private fun GameDetailScreen(
    game: GameExpanded,
    modifier: Modifier = Modifier
) {
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            ScaledText(
                text = "Player 01 Faction",
                style = MaterialTheme.typography.titleMedium
            )
            ScaledText(
                text = game.game.player01FactionName,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            ScaledText(
                text = "Player 01 Detachment",
                style = MaterialTheme.typography.titleMedium
            )
            game.game.player01FactionDetachment?.let {
                ScaledText(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            ScaledText(
                text = "Player 02 Faction",
                style = MaterialTheme.typography.titleMedium
            )
            ScaledText(
                text = game.game.player02FactionName,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            ScaledText(
                text = "Player 02 Detachment",
                style = MaterialTheme.typography.titleMedium
            )
            game.game.player02FactionDetachment?.let {
                ScaledText(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            ScaledText(
                text = "Prediction",
                style = MaterialTheme.typography.titleMedium
            )
            ScaledText(
                text = game.prediction.name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = modifier
                    .background(Color(game.prediction.color))
            )
        }
        if (game.outcome != null) {
            Column(
                modifier = modifier.wrapContentHeight()
            ) {
                ScaledText(
                    text = "Outcome",
                    style = MaterialTheme.typography.titleMedium
                )
                ScaledText(
                    text = game.outcome.player01Points.toString() + " - " + game.outcome.player02Points.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            ScaledText(
                text = "Primary Mission",
                style = MaterialTheme.typography.titleMedium
            )
            ScaledText(
                text = game.round.round.primaryMissionName,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
private fun AddGame(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
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
        GAME_VIEW_MODEL.insert(game = newGame)
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
private fun EditGame(
    game: GameExpanded,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
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
        GAME_VIEW_MODEL.update(game = updatedGame)
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
private fun RemoveGame(
    game: GameExpanded,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    val onConfirmation = {
        GAME_VIEW_MODEL.delete(game = game.game)
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