package com.example.a40kcc.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.LiveRound
import com.example.a40kcc.data.`object`.LiveRoundExpanded
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.GAME_VIEW_MODEL
import com.example.a40kcc.ui.utilities.LIVE_ROUND_EXPANDED_VIEW_MODEL
import com.example.a40kcc.ui.utilities.LIVE_ROUND_VIEW_MODEL
import com.example.a40kcc.ui.utilities.PREDICTION_VIEW_MODEL
import com.example.a40kcc.ui.utilities.ROUND_VIEW_MODEL
import com.example.a40kcc.ui.utilities.ScaledText
import com.example.a40kcc.ui.utilities.TEAM_VIEW_MODEL
import com.example.a40kcc.ui.utilities.TOURNAMENT_VIEW_MODEL

var lowEndScore: Int = 0
var midScore: Int = 0
var highEndScore: Int = 0
var lossThreshold: Int = 0
var winThreshold: Int = 0

val columnWidth: Dp = 100.dp

@Composable
fun PreLiveRoundScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    //HACK TO MAKE THINGS WORK FOR ALPHA
    //Add all games to the live round
    GAME_VIEW_MODEL.allGames.forEach {
        if (LIVE_ROUND_VIEW_MODEL.getByGameId(it.gameID) == null) {
            println("add game ${it.gameID}")
            LIVE_ROUND_VIEW_MODEL.insert(
                LiveRound(
                    gameID = it.gameID,
                    expectedResult = it.predictionID!!
                )
            )
        }
    }

    var goToLiveRound by remember { mutableStateOf(false) }
    var preLiveRound by remember { mutableStateOf(true) }
    var tournamentID by remember { mutableIntStateOf(0) }
    var roundID by remember { mutableIntStateOf(0) }
    var teamID by remember { mutableIntStateOf(0) }
    var tournamentIndex by remember { mutableIntStateOf(0) }
    var roundIndex by remember { mutableIntStateOf(0) }
    var teamIndex by remember { mutableIntStateOf(0) }
    val tournamentNames: MutableList<String> = mutableListOf("")
    var tournamentRounds: MutableMap<String, Int> = mutableMapOf(Pair("", 0))
    val teamNames: MutableList<String> = mutableListOf("")
    TOURNAMENT_VIEW_MODEL.allTournaments.forEach {
        tournamentNames += it.name
    }
    TEAM_VIEW_MODEL.allTeams.forEach {
        teamNames += it.name
    }
    if (preLiveRound) {
        Dialog(onDismissRequest = { onBackClick() }) {
            Card(
                modifier = modifier.wrapContentSize()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Row {
                        DropDownList(
                            itemList = tournamentNames,
                            selectedIndex = tournamentIndex,
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
                    }
                    Row {
                        DropDownList(
                            itemList = tournamentRounds.keys.toList(),
                            selectedIndex = roundIndex,
                            preText = "Round: ",
                            onItemClick = {
                                roundIndex = it; roundID =
                                ROUND_VIEW_MODEL.getById(tournamentRounds[tournamentRounds.keys.toList()[roundIndex]]!!).roundID
                            })
                    }
                    Row {
                        DropDownList(
                            itemList = teamNames,
                            selectedIndex = teamIndex,
                            preText = "Team: ",
                            onItemClick = {
                                teamIndex = it; teamID =
                                TEAM_VIEW_MODEL.getByName(teamNames[teamIndex]).teamID
                            })
                    }
                    Row {
                        TextButton(
                            onClick = {
                                goToLiveRound = !goToLiveRound; preLiveRound = !preLiveRound
                            },
                            modifier = modifier
                        ) {
                            Text(
                                text = "View",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }
        }
    }
    if (goToLiveRound) {
        LiveRoundScreen(tournamentID = tournamentID,
            roundID = roundID,
            teamID = teamID,
            modifier = modifier,
            onBackClick = { onBackClick() })
    }
}

@Composable
fun getByTeamAndTournament(teamId: Int, roundId: Int, tournamentId: Int): List<LiveRoundExpanded> {
    val liveRounds: MutableList<LiveRoundExpanded> = mutableListOf()
    val currentLiveRounds: List<LiveRoundExpanded>? =
        LIVE_ROUND_EXPANDED_VIEW_MODEL.allLiveRoundsFlow.observeAsState().value
    currentLiveRounds?.forEach {
        if (it.game.round.round.roundID == roundId &&
            it.game.round.round.tournamentID == tournamentId &&
            it.game.player01.team[0].teamID == teamId
        ) {
            liveRounds += it
        }
    }
    lossThreshold = ((liveRounds.toList().size * 20) / 2) - 6
    winThreshold = ((liveRounds.toList().size * 20) / 2) + 6
    return liveRounds.toList()
}

@Composable
private fun LiveRoundScreen(
    tournamentID: Int,
    roundID: Int,
    teamID: Int,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column {
        Button(
            onClick = onBackClick,
            modifier = modifier
        ) {
            Column {
                Text(text = stringResource(id = R.string.home_button))
            }
        }

        val liveRounds: List<LiveRoundExpanded> =
            getByTeamAndTournament(teamID, roundID, tournamentID)

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .width(columnWidth)
                    .alignByBaseline()
                    .wrapContentHeight()
            ) {
                ScaledText(
                    text = "Player",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .width(columnWidth)
                    .alignByBaseline()
                    .wrapContentHeight()
            ) {
                ScaledText(
                    text = "Prediction",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .width(columnWidth)
                    .alignByBaseline()
                    .wrapContentHeight()
            ) {
                ScaledText(
                    text = "Current State",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }

        LiveRoundScreen(
            liveRounds = liveRounds,
            modifier = modifier
        )
        RoundResults(
            liveRounds = liveRounds,
            modifier = modifier
        )
    }
}

@Composable
private fun LiveRoundScreen(
    liveRounds: List<LiveRoundExpanded>,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        lowEndScore = 0
        midScore = 0
        highEndScore = 0
        items(items = liveRounds) { liveRound ->
            lowEndScore += liveRound.expectedResult.minPoints
            highEndScore += liveRound.expectedResult.maxPoints
            midScore = (lowEndScore + highEndScore) / 2
            var editLiveRound by remember { mutableStateOf(false) }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier.fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .width(columnWidth)
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    ScaledText(
                        text = liveRound.game.player01.player.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .width(columnWidth)
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    ScaledText(
                        text = liveRound.game.prediction.name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.background(Color(liveRound.game.prediction.color))
                    )
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .width(columnWidth)
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    ScaledText(
                        text = liveRound.expectedResult.name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = modifier
                            .background(Color(liveRound.expectedResult.color))
                            .clickable(enabled = true, onClick = {
                                editLiveRound = !editLiveRound
                            })
                    )
                }
                Column {
                    if (editLiveRound) {
                        var predictionID by remember { mutableIntStateOf(0) }
                        var predictionIndex by remember { mutableIntStateOf(0) }
                        val predictionNames: MutableList<String> = mutableListOf()
                        PREDICTION_VIEW_MODEL.allPredictions.forEach {
                            predictionNames += it.name
                            if(it.predictionID == liveRound.liveRound.expectedResult)
                            {
                                predictionIndex = predictionNames.lastIndex
                            }
                        }
                        val onConfirmation = {
                            val updatedLiveRound = LiveRound(
                                liveRoundID = liveRound.liveRound.liveRoundID,
                                gameID = liveRound.liveRound.gameID,
                                expectedResult = predictionID
                            )
                            LIVE_ROUND_VIEW_MODEL.update(updatedLiveRound)
                            editLiveRound = !editLiveRound
                        }
                        Dialog(onDismissRequest = { editLiveRound = !editLiveRound }) {
                            Card(
                                modifier = modifier.wrapContentSize()
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = modifier.fillMaxWidth()
                                ) {
                                    Row {
                                        DropDownList(
                                            itemList = predictionNames,
                                            selectedIndex = predictionIndex,
                                            preText = "Current State:",
                                            onItemClick = {
                                                predictionIndex = it; predictionID =
                                                PREDICTION_VIEW_MODEL.getByName(predictionNames[predictionIndex]).predictionID
                                            })
                                    }
                                    Row {
                                        TextButton(
                                            onClick = { onConfirmation() }
                                        ) {
                                            Text(
                                                text = "Update",
                                                style = MaterialTheme.typography.titleMedium
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RoundResults(
    liveRounds: List<LiveRoundExpanded>,
    modifier: Modifier = Modifier
) {
    lowEndScore = 0
    var lowEndBackground = Color(0xffffff00)
    midScore = 0
    var midBackground = Color(0xffffff00)
    highEndScore = 0
    var highEndBackground = Color(0xffffff00)
    liveRounds.forEach {
        lowEndScore += it.expectedResult.minPoints
        highEndScore += it.expectedResult.maxPoints
        midScore = (lowEndScore + highEndScore) / 2

        lowEndBackground = if (lowEndScore <= lossThreshold) {
            Color(0Xffff0000)
        } else if (lowEndScore >= winThreshold) {
            Color(0xff00ff00)
        } else {
            Color(0xffffff00)
        }

        midBackground = if (midScore <= lossThreshold) {
            Color(0Xffff0000)
        } else if (midScore >= winThreshold) {
            Color(0xff00ff00)
        } else {
            Color(0xffffff00)
        }

        highEndBackground = if (highEndScore <= lossThreshold) {
            Color(0Xffff0000)
        } else if (highEndScore >= winThreshold) {
            Color(0xff00ff00)
        } else {
            Color(0xffffff00)
        }
    }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .alignByBaseline()
                .wrapContentHeight()
                .background(lowEndBackground)
        ) {
            Text(
                text = "Low End",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = lowEndScore.toString(),
                style = MaterialTheme.typography.titleLarge
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .alignByBaseline()
                .wrapContentHeight()
                .background(midBackground)
        ) {
            Text(
                text = "Mid",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = midScore.toString(),
                style = MaterialTheme.typography.titleLarge
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .alignByBaseline()
                .wrapContentHeight()
                .background(highEndBackground)
        ) {
            Text(
                text = "High End",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = highEndScore.toString(),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}