package com.example.a40kcc.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.core.graphics.toColorInt
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.GameExpanded
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.GAME_VIEW_MODEL
import com.example.a40kcc.ui.utilities.PREDICTION_VIEW_MODEL

@Composable
fun GameScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        var addGame by remember { mutableStateOf(false) }
        Button(
            onClick = onBackClick,
            modifier = modifier
        ) {
            Column {
                Text(stringResource(id = R.string.home_button))
            }
        }

        val games: List<GameExpanded>? =
            GAME_VIEW_MODEL.allGamesExpanded.observeAsState().value

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier.fillMaxWidth()
        ) {
            Column(
                modifier = modifier
                    .alignByBaseline()
                    .wrapContentHeight()
            ) {
                Text(
                    "Player 01",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Column(
                modifier = modifier
                    .alignByBaseline()
                    .wrapContentHeight()
            ) {
                Text(
                    "Player 02",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Column(
                modifier = modifier
                    .alignByBaseline()
                    .wrapContentHeight()
            ) {
                Text(
                    "Prediction",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Column(
                modifier = modifier
                    .alignByBaseline()
                    .wrapContentHeight()
            ) {
                Text(
                    "Round Number",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        if (games != null) {
            GameScreen(
                games, modifier
            )
        }

        FloatingActionButton(
            onClick = {
                addGame = !addGame
            },
            modifier = modifier.align(Alignment.End)
        ) {
            Icon(Icons.Filled.Add, "Add Game")

            if (addGame) {
                AddGame(
                    modifier
                ) { addGame = !addGame }
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
        items(games) { game ->
            var showDetails by remember { mutableStateOf(false) }
            var removeGame by remember { mutableStateOf(false) }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = modifier
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    Text(
                        game.player01.name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                            .clickable(true, onClick = {
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
                        Text(
                            text = game.player02.name,
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
                    Text(
                        game.prediction.name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                            .clickable(true, onClick = {
                                showDetails = !showDetails
                            })
                            .background(Color(game.prediction.color.toColorInt()))
                    )
                }
                Column(
                    modifier = modifier
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    Text(
                        game.round.number.toString(),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                            .clickable(true, onClick = {
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
                            Icon(Icons.Filled.Clear, "Remove Game")

                            if (removeGame) {
                                RemoveGame(
                                    game,
                                    modifier
                                ) { removeGame = !removeGame }
                            }
                        }
                    }
                }
            }

            if (showDetails) {
                GameDetailScreen(
                    game,
                    modifier
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
            Text(
                "Player 01 Faction",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                game.game.player01FactionName,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                "Player 01 Detachment",
                style = MaterialTheme.typography.titleMedium
            )
            game.game.player01FactionDetachment?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                "Player 02 Faction",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                game.game.player02FactionName,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                "Player 02 Detachment",
                style = MaterialTheme.typography.titleMedium
            )
            game.game.player02FactionDetachment?.let {
                Text(
                    it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                "Prediction",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                game.prediction.name,
                modifier = Modifier.background(Color(game.prediction.color.toColorInt())),
                style = MaterialTheme.typography.bodyMedium
            )
        }
        if (game.outcome != null) {
            Column(
                modifier = modifier.wrapContentHeight()
            ) {
                Text(
                    "Outcome",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    game.outcome.player01Points.toString() + " - " + game.outcome.player02Points.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Column(
            modifier = modifier.wrapContentHeight()
        ) {
            Text(
                "Primary Mission",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                game.round.primaryMissionName,
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
    var predictionID by remember { mutableIntStateOf(0) }
    var predictionIndex by remember { mutableIntStateOf(0) }
    val predictionNames: MutableList<String> = mutableListOf("")
    PREDICTION_VIEW_MODEL.allPredictions.value?.forEach {
        predictionNames += it.name
    }
    val onConfirmation = {
        //PREDICTION_VIEW_MODEL.insert()
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
                        itemList = predictionNames,
                        selectedIndex = predictionIndex,
                        modifier = modifier,
                        preText = "Prediction:",
                        onItemClick = {
                            predictionIndex = it; predictionID =
                            PREDICTION_VIEW_MODEL.getByName(predictionNames[predictionIndex]).value?.predictionID
                                ?: 0
                        })
                }
                Row {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = modifier
                    ) {
                        Text(
                            "Cancel",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = modifier
                    ) {
                        Text(
                            "Add",
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
        GAME_VIEW_MODEL.delete(game.game)
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
                            "Cancel",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = modifier
                    ) {
                        Text(
                            "Confirm",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
    }
}