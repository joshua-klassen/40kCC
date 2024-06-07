package com.example.a40kcc.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.a40kcc.COLORS
import com.example.a40kcc.LIVE_ROUND_EXPANDED_VIEW_MODEL
import com.example.a40kcc.LIVE_ROUND_VIEW_MODEL
import com.example.a40kcc.PREDICTION_VIEW_MODEL
import com.example.a40kcc.R
import com.example.a40kcc.ROUND_WITH_TOURNAMENT_VIEW_MODEL
import com.example.a40kcc.TEAM_VIEW_MODEL
import com.example.a40kcc.TOURNAMENT_WITH_ROUNDS_VIEW_MODEL
import com.example.a40kcc.data.`object`.LiveRound
import com.example.a40kcc.data.`object`.LiveRoundExpanded
import com.example.a40kcc.data.`object`.Prediction
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.data.`object`.RoundWithTournament
import com.example.a40kcc.data.`object`.Team
import com.example.a40kcc.data.`object`.TournamentWithRounds
import com.example.a40kcc.ui.utilities.DropDownList
import com.example.a40kcc.ui.utilities.ErrorHandling
import com.example.a40kcc.ui.utilities.ScaledText
import kotlinx.coroutines.launch

private var lowEndScore: Int = 0
private var midScore: Int = 0
private var highEndScore: Int = 0
private var lossThreshold: Int = 0
private var winThreshold: Int = 0

@Composable
fun LiveRoundScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    columnWidth: Dp = 100.dp
) {
    var showPreScreen by remember { mutableStateOf(true) }
    var tournament: TournamentWithRounds? by remember { mutableStateOf(null) }
    var round: RoundWithTournament? by remember { mutableStateOf(null) }
    var team: Team? by remember { mutableStateOf(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    val errorHandling = remember {
        ErrorHandling(
            snackbarHostState = snackbarHostState,
            modifier = modifier
        )
    }

    val changeRound: (tournament: TournamentWithRounds, round: Round, team: Team) -> Unit =
        { tempTournament, tempRound, tempTeam ->
            tournament = tempTournament
            round = ROUND_WITH_TOURNAMENT_VIEW_MODEL.getById(tempRound.roundID)
            team = tempTeam
            showPreScreen = !showPreScreen
        }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            Button(
                onClick = { navController.navigate(route = "home") },
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
                },
                modifier = modifier
            ) {
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
            if (showPreScreen ||
                (tournament == null || round == null || team == null)
            ) {
                if (TOURNAMENT_WITH_ROUNDS_VIEW_MODEL.allTournaments().isNotEmpty() &&
                    TEAM_VIEW_MODEL.allTeams().isNotEmpty()
                ) {
                    PreLiveRoundScreen(
                        tournament = tournament,
                        round = round,
                        team = team,
                        modifier = modifier,
                        onDismissRequest = changeRound
                    )
                } else {
                    errorHandling.showSnackbar(
                        message = "At least one tournament and team must exist to view the Live Round",
                        duration = SnackbarDuration.Indefinite,
                        withDismissAction = true
                    )
                    navController.navigate("home")
                }
            } else {
                LiveRoundScreenData(
                    round = round!!,
                    team = team!!,
                    errorHandling = errorHandling,
                    columnWidth = columnWidth
                )
            }
        }
    }
}

@Composable
private fun LiveRoundScreenData(
    round: RoundWithTournament,
    team: Team,
    errorHandling: ErrorHandling,
    modifier: Modifier = Modifier,
    columnWidth: Dp = 100.dp
) {
    var editLiveRound by remember { mutableStateOf(false) }
    val liveRounds: List<LiveRoundExpanded> = getByTeamAndTournament(
        round = round,
        team = team
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
    }

    liveRounds.forEach { liveRound ->
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier.fillMaxWidth()
        ) {
            Column(
                modifier = modifier
                    .width(columnWidth)
                    .alignByBaseline()
                    .wrapContentHeight()
            ) {
                ScaledText(
                    text = liveRound.game?.player01?.player?.name ?: "",
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
                ScaledText(
                    text = liveRound.expectedResult?.name ?: "",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = modifier,
                    textAlign = TextAlign.Center,
                    color = Color(liveRound.expectedResult?.color ?: 0xffffffff)
                )
            }
            Column(
                modifier = modifier
                    .width(columnWidth)
                    .alignByBaseline()
                    .wrapContentHeight()
            ) {
                if (liveRound.game?.outcome != null) {
                    ScaledText(
                        text = liveRound.game.outcome.player01TeamPoints.toString(),
                        style = MaterialTheme.typography.titleLarge,
                        textAlign = TextAlign.Center,
                        modifier = modifier
                    )
                } else {
                    ScaledText(
                        text = liveRound.expectedResult?.name ?: "",
                        style = MaterialTheme.typography.titleMedium,
                        modifier = modifier
                            .clickable(enabled = true, onClick = {
                                editLiveRound = !editLiveRound
                            }),
                        textAlign = TextAlign.Center,
                        color = Color(liveRound.expectedResult?.color ?: 0xffffffff)
                    )
                }
            }
        }

        if (editLiveRound) {
            EditLiveRound(
                liveRound = liveRound,
                errorHandling = errorHandling,
                modifier = modifier,
                onDismissRequest = { editLiveRound = !editLiveRound }
            )
        }
    }
    RoundResults(
        liveRounds = liveRounds,
        modifier = modifier,
        columnWidth = columnWidth
    )
}

@Composable
fun PreLiveRoundScreen(
    tournament: TournamentWithRounds?,
    round: RoundWithTournament?,
    team: Team?,
    onDismissRequest: (tournament: TournamentWithRounds, round: Round, team: Team) -> Unit,
    modifier: Modifier = Modifier
) {
    var tournamentIndex by remember { mutableIntStateOf(0) }
    var roundIndex by remember { mutableIntStateOf(0) }
    var teamIndex by remember { mutableIntStateOf(0) }
    val tournaments: List<TournamentWithRounds> = TOURNAMENT_WITH_ROUNDS_VIEW_MODEL.allTournaments()
    val teams: List<Team> = TEAM_VIEW_MODEL.allTeams()

    if (tournament != null) {
        if (tournaments.contains(tournament)) {
            tournamentIndex = tournaments.indexOf(tournament)
        }
        roundIndex = 0
    }

    if (team != null) {
        if (teams.contains(team)) {
            teamIndex = teams.indexOf(team)
        }
    }

    if (round != null) {
        if (tournaments[tournamentIndex].round.contains(round.round)) {
            roundIndex = tournaments[tournamentIndex].round.indexOf(round.round)
        }
    }

    Dialog(
        onDismissRequest = {
            onDismissRequest(
                tournaments[tournamentIndex],
                tournaments[tournamentIndex].round[roundIndex],
                teams[teamIndex]
            )
        }
    ) {
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
                        itemList = tournaments.map { it.getDisplayName() },
                        selectedIndex = tournamentIndex,
                        preText = "Tournament: ",
                        onItemClick = { index ->
                            tournamentIndex = index
                            roundIndex = 0
                        }
                    )
                }
                Row {
                    DropDownList(
                        itemList = tournaments[tournamentIndex].round.map { it.getDisplayName() },
                        selectedIndex = roundIndex,
                        preText = "Round: ",
                        onItemClick = { index ->
                            roundIndex = index
                        }
                    )
                }
                Row {
                    DropDownList(
                        itemList = teams.map { it.getDisplayName() },
                        selectedIndex = teamIndex,
                        preText = "Team: ",
                        onItemClick = { index ->
                            teamIndex = index
                        }
                    )
                }
                Row {
                    TextButton(
                        onClick = {
                            onDismissRequest(
                                tournaments[tournamentIndex],
                                tournaments[tournamentIndex].round[roundIndex],
                                teams[teamIndex]
                            )
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

@Composable
fun getByTeamAndTournament(
    round: RoundWithTournament,
    team: Team
): List<LiveRoundExpanded> {
    val liveRounds: MutableList<LiveRoundExpanded> = mutableListOf()
    LIVE_ROUND_EXPANDED_VIEW_MODEL.allLiveRounds().forEach { liveRound ->
        var playerTeam: Team? = null
        if (liveRound.game?.player01?.team?.isNotEmpty() == true) {
            playerTeam = liveRound.game.player01.team[0]
        }

        if (liveRound.game?.round == round &&
            playerTeam == team
        ) {
            liveRounds += liveRound
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
    var lowEndColor = Color(COLORS.getValue(key = "Yellow"))
    midScore = 0
    var midColor = Color(COLORS.getValue(key = "Yellow"))
    highEndScore = 0
    var highEndColor = Color(COLORS.getValue(key = "Yellow"))
    liveRounds.forEach {
        if (it.game?.outcome != null) {
            lowEndScore += it.game.outcome.player01TeamPoints
            highEndScore += it.game.outcome.player01TeamPoints
        } else {
            lowEndScore += it.expectedResult?.minPoints ?: 0
            highEndScore += it.expectedResult?.maxPoints ?: 20
        }
        midScore = (lowEndScore + highEndScore) / 2

        lowEndColor = if (lowEndScore <= lossThreshold) {
            Color(COLORS.getValue(key = "Red"))
        } else if (lowEndScore >= winThreshold) {
            Color(COLORS.getValue(key = "Green"))
        } else {
            Color(COLORS.getValue(key = "Yellow"))
        }

        midColor = if (midScore <= lossThreshold) {
            Color(COLORS.getValue(key = "Red"))
        } else if (midScore >= winThreshold) {
            Color(COLORS.getValue(key = "Green"))
        } else {
            Color(COLORS.getValue(key = "Yellow"))
        }

        highEndColor = if (highEndScore <= lossThreshold) {
            Color(COLORS.getValue(key = "Red"))
        } else if (highEndScore >= winThreshold) {
            Color(COLORS.getValue(key = "Green"))
        } else {
            Color(COLORS.getValue(key = "Yellow"))
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
        ) {
            ScaledText(
                text = "Low End: $lowEndScore",
                style = MaterialTheme.typography.titleLarge,
                color = lowEndColor
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .alignByBaseline()
                .wrapContentHeight()
                .width(columnWidth)
        ) {
            ScaledText(
                text = "Mid: $midScore",
                style = MaterialTheme.typography.titleLarge,
                color = midColor
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .alignByBaseline()
                .wrapContentHeight()
                .width(columnWidth)
        ) {
            ScaledText(
                text = "High End: $highEndScore",
                style = MaterialTheme.typography.titleLarge,
                color = highEndColor
            )
        }
    }
}

@Composable
fun EditLiveRound(
    liveRound: LiveRoundExpanded,
    errorHandling: ErrorHandling,
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit
) {
    var predictionID by remember { mutableIntStateOf(0) }
    var predictionIndex by remember { mutableIntStateOf(0) }
    val predictions: List<Prediction> = PREDICTION_VIEW_MODEL.allPredictions()

    if (predictions.contains(liveRound.expectedResult)) {
        predictionIndex = predictions.indexOf(liveRound.expectedResult)
        predictionID = liveRound.expectedResult?.predictionID ?: 0
    }

    val onConfirmation = {
        val updatedLiveRound = LiveRound(
            liveRoundID = liveRound.liveRound.liveRoundID,
            gameID = liveRound.liveRound.gameID,
            expectedResult = predictionID
        )
        errorHandling.provideCoroutineExceptionScope(
            errorMessage = "Error updating the round: ${liveRound.getDisplayName()}"
        ).launch {
            LIVE_ROUND_VIEW_MODEL.update(liveRound = updatedLiveRound)
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
                    DropDownList(
                        itemList = predictions.map { it.getDisplayName() },
                        selectedIndex = predictionIndex,
                        preText = "Current State: ",
                        onItemClick = { index ->
                            predictionIndex = index
                            predictionID = predictions[predictionIndex].predictionID
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