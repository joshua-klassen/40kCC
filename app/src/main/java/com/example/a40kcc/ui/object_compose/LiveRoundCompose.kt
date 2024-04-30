package com.example.a40kcc.ui.object_compose

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.a40kcc.LIVE_ROUND_EXPANDED_VIEW_MODEL
import com.example.a40kcc.LIVE_ROUND_VIEW_MODEL
import com.example.a40kcc.PREDICTION_VIEW_MODEL
import com.example.a40kcc.ROUND_VIEW_MODEL
import com.example.a40kcc.TEAM_VIEW_MODEL
import com.example.a40kcc.TOURNAMENT_VIEW_MODEL
import com.example.a40kcc.data.`object`.LiveRound
import com.example.a40kcc.data.`object`.LiveRoundExpanded
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.ScaledText
import kotlinx.coroutines.CoroutineExceptionHandler

class LiveRoundScreenDataObject {
    var tournamentID: Int = 0
    var roundID: Int = 0
    var teamID: Int = 0
    private var valuesSet: Boolean = false

    fun areValuesSet(): Boolean {
        return this.valuesSet
    }

    fun confirmValues() {
        this.valuesSet = true
    }

    fun changeLiveRound() {
        this.valuesSet = false; this.tournamentID = 0; this.roundID = 0; this.teamID = 0
    }
}

class LiveRoundCompose {
    private var lowEndScore: Int = 0
    private var midScore: Int = 0
    private var highEndScore: Int = 0
    private var lossThreshold: Int = 0
    private var winThreshold: Int = 0

    @Composable
    fun PreLiveRoundScreen(
        liveRoundScreenData: LiveRoundScreenDataObject,
        onDismissRequest: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        val localContext = LocalContext.current
        var tournamentIndex by remember { mutableIntStateOf(0) }
        var roundIndex by remember { mutableIntStateOf(0) }
        var teamIndex by remember { mutableIntStateOf(0) }
        val tournamentNames: MutableList<String> = mutableListOf("")
        var tournamentRounds: MutableMap<String, Int> = mutableMapOf(Pair("", 0))
        val teamNames: MutableList<String> = mutableListOf("")

        TOURNAMENT_VIEW_MODEL.allTournaments.forEach {
            tournamentNames += it.name
            if (it.tournamentID == liveRoundScreenData.tournamentID) {
                tournamentIndex = tournamentNames.lastIndex
            }
        }

        TEAM_VIEW_MODEL.allTeams.forEach {
            teamNames += it.name
            if (it.teamID == liveRoundScreenData.teamID) {
                teamIndex = teamNames.lastIndex
            }
        }

        if (tournamentIndex != 0) {
            tournamentRounds = mutableMapOf(Pair("", 0))
            ROUND_VIEW_MODEL.getByTournamentId(liveRoundScreenData.tournamentID).forEach { round ->
                tournamentRounds += mutableMapOf(
                    Pair(
                        round.number.toString(),
                        round.roundID
                    )
                )
                if (round.roundID == liveRoundScreenData.roundID) {
                    roundIndex = tournamentRounds.keys.toList().lastIndex
                }
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
                        DropDownList(
                            itemList = tournamentNames,
                            selectedIndex = tournamentIndex,
                            preText = "Tournament: ",
                            onItemClick = {
                                tournamentIndex = it
                                if (it != 0) {
                                    liveRoundScreenData.tournamentID =
                                        TOURNAMENT_VIEW_MODEL.getByName(tournamentNames[tournamentIndex])
                                            .first().tournamentID
                                    tournamentRounds = mutableMapOf(Pair("", 0))
                                    ROUND_VIEW_MODEL.getByTournamentId(liveRoundScreenData.tournamentID)
                                        .forEach { round ->
                                            tournamentRounds += mutableMapOf(
                                                Pair(
                                                    round.number.toString(),
                                                    round.roundID
                                                )
                                            )
                                        }
                                }
                            }
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = tournamentRounds.keys.toList(),
                            selectedIndex = roundIndex,
                            preText = "Round: ",
                            onItemClick = {
                                roundIndex = it
                                if (it != 0) {
                                    liveRoundScreenData.roundID =
                                        ROUND_VIEW_MODEL.getById(tournamentRounds[tournamentRounds.keys.toList()[roundIndex]]!!).roundID
                                }
                            }
                        )
                    }
                    Row {
                        DropDownList(
                            itemList = teamNames,
                            selectedIndex = teamIndex,
                            preText = "Team: ",
                            onItemClick = {
                                teamIndex = it
                                if (it != 0) {
                                    liveRoundScreenData.teamID =
                                        TEAM_VIEW_MODEL.getByName(teamNames[teamIndex]).teamID
                                }
                            }
                        )
                    }
                    Row {
                        TextButton(
                            onClick = {
                                if (liveRoundScreenData.tournamentID != 0 && liveRoundScreenData.roundID != 0 && liveRoundScreenData.teamID != 0) {
                                    liveRoundScreenData.confirmValues()
                                    onDismissRequest()
                                } else {
                                    Toast.makeText(
                                        localContext,
                                        "Valid tournament, round and team must be selected",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            },
                            modifier = modifier
                        ) {
                            Text(
                                text = "View",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                        TextButton(
                            onClick = { onDismissRequest() },
                            modifier = modifier
                        ) {
                            Text(
                                text = "Cancel",
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun getByTeamAndTournament(
        teamId: Int,
        roundId: Int,
        tournamentId: Int
    ): List<LiveRoundExpanded> {
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
    fun RoundResults(
        liveRounds: List<LiveRoundExpanded>,
        modifier: Modifier = Modifier,
        columnWidth: Dp = 100.dp
    ) {
        lowEndScore = 0
        var lowEndBackground = Color(0xffffff00)
        midScore = 0
        var midBackground = Color(0xffffff00)
        highEndScore = 0
        var highEndBackground = Color(0xffffff00)
        liveRounds.forEach {
            if (it.game.outcome != null) {
                lowEndScore += it.game.outcome.player01TeamPoints
                highEndScore += it.game.outcome.player01TeamPoints
            } else {
                lowEndScore += it.expectedResult.minPoints
                highEndScore += it.expectedResult.maxPoints
            }
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
                    .width(columnWidth)
                    .background(lowEndBackground)
            ) {
                ScaledText(
                    text = "Low End: $lowEndScore",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .alignByBaseline()
                    .wrapContentHeight()
                    .width(columnWidth)
                    .background(midBackground)
            ) {
                ScaledText(
                    text = "Mid: $midScore",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .alignByBaseline()
                    .wrapContentHeight()
                    .width(columnWidth)
                    .background(highEndBackground)
            ) {
                ScaledText(
                    text = "High End: $highEndScore",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }

    @Composable
    fun EditLiveRound(
        liveRound: LiveRoundExpanded,
        onDismissRequest: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        val localContext = LocalContext.current
        var predictionID by remember { mutableIntStateOf(0) }
        var predictionIndex by remember { mutableIntStateOf(0) }
        val predictionNames: MutableList<String> = mutableListOf()
        PREDICTION_VIEW_MODEL.allPredictions.forEach {
            predictionNames += it.name
            if (it.predictionID == liveRound.liveRound.expectedResult) {
                predictionIndex = predictionNames.lastIndex
            }
        }
        val onConfirmation = {
            val updatedLiveRound = LiveRound(
                liveRoundID = liveRound.liveRound.liveRoundID,
                gameID = liveRound.liveRound.gameID,
                expectedResult = predictionID
            )
            LIVE_ROUND_VIEW_MODEL.update(
                liveRound = updatedLiveRound,
                exceptionHandler = CoroutineExceptionHandler { _, _ ->
                    Toast.makeText(
                        localContext,
                        "Error updating Live Round",
                        Toast.LENGTH_LONG
                    ).show()
                }
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
                        DropDownList(
                            itemList = predictionNames,
                            selectedIndex = predictionIndex,
                            preText = "Current State: ",
                            onItemClick = {
                                predictionIndex = it
                                predictionID =
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