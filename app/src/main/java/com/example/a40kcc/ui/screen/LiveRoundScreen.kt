package com.example.a40kcc.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.a40kcc.R
import com.example.a40kcc.data.`object`.LiveRoundExpanded
import com.example.a40kcc.ui.object_compose.LiveRoundCompose
import com.example.a40kcc.ui.object_compose.LiveRoundScreenDataObject
import com.example.a40kcc.ui.utilities.ScaledText

@Composable
fun LiveRoundScreen(
    onBackClick: () -> Unit,
    liveRoundCompose: LiveRoundCompose,
    liveRoundScreenData: LiveRoundScreenDataObject,
    modifier: Modifier = Modifier,
    columnWidth: Dp = 100.dp
) {
    var showPreScreen by remember { mutableStateOf(true) }

    if (liveRoundScreenData.areValuesSet()) {
        showPreScreen = false
    }

    Scaffold(
        topBar = {
            Button(
                onClick = onBackClick,
                modifier = modifier
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.home_button),
                        modifier = modifier
                    )
                }
            }
        },
        bottomBar = {
            Button(
                onClick = {
                    showPreScreen = !showPreScreen
                    liveRoundScreenData.changeLiveRound()
                },
                modifier = modifier
            ) {
                if (showPreScreen) {
                    liveRoundCompose.PreLiveRoundScreen(
                        liveRoundScreenData = liveRoundScreenData,
                        modifier = modifier,
                        onDismissRequest = { showPreScreen = !showPreScreen }
                    )
                }
                Column {
                    Text(
                        text = "Change Round",
                        modifier = modifier
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (showPreScreen) {
                liveRoundCompose.PreLiveRoundScreen(
                    liveRoundScreenData = liveRoundScreenData,
                    modifier = modifier,
                    onDismissRequest = { showPreScreen = !showPreScreen }
                )
            } else {
                LiveRoundScreenData(
                    liveRoundCompose = liveRoundCompose,
                    liveRoundScreenData = liveRoundScreenData,
                    modifier = modifier,
                    columnWidth = columnWidth
                )
            }
        }
    }
}

@Composable
private fun LiveRoundScreenData(
    liveRoundCompose: LiveRoundCompose,
    liveRoundScreenData: LiveRoundScreenDataObject,
    modifier: Modifier = Modifier,
    columnWidth: Dp = 100.dp
) {
    var editLiveRound by remember { mutableStateOf(false) }
    val liveRounds: List<LiveRoundExpanded> =
        liveRoundCompose.getByTeamAndTournament(
            teamId = liveRoundScreenData.teamID,
            roundId = liveRoundScreenData.roundID,
            tournamentId = liveRoundScreenData.tournamentID
        )

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier
            .fillMaxWidth()
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

        liveRounds.forEach { liveRound ->
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = modifier
                        .width(columnWidth)
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    ScaledText(
                        text = liveRound.game.player01.player.name,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = modifier,
                        textAlign = TextAlign.Center
                    )
                }
                Column(
                    modifier = modifier
                        .width(columnWidth)
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    if (liveRound.game.outcome != null) {
                        ScaledText(
                            text = liveRound.game.outcome.player01TeamPoints.toString(),
                            style = MaterialTheme.typography.titleLarge,
                            modifier = modifier
                        )
                    } else {
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
                }
                Column(
                    modifier = modifier
                        .width(columnWidth)
                        .alignByBaseline()
                        .wrapContentHeight()
                ) {
                    ScaledText(
                        text = liveRound.expectedResult.name,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = modifier,
                        textAlign = TextAlign.Center
                    )
                }
            }

            if (editLiveRound) {
                liveRoundCompose.EditLiveRound(
                    liveRound = liveRound,
                    onDismissRequest = {
                        editLiveRound = !editLiveRound
                    },
                    modifier = modifier
                )
            }
        }
    }
    liveRoundCompose.RoundResults(
        liveRounds = liveRounds,
        modifier = modifier,
        columnWidth = columnWidth
    )
}