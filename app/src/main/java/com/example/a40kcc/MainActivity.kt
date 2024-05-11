package com.example.a40kcc

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.a40kcc.data.model.GameExpandedViewModel
import com.example.a40kcc.data.model.GameExpandedViewModelFactory
import com.example.a40kcc.data.model.GameViewModel
import com.example.a40kcc.data.model.GameViewModelFactory
import com.example.a40kcc.data.model.LiveRoundExpandedViewModel
import com.example.a40kcc.data.model.LiveRoundExpandedViewModelFactory
import com.example.a40kcc.data.model.LiveRoundViewModel
import com.example.a40kcc.data.model.LiveRoundViewModelFactory
import com.example.a40kcc.data.model.OutcomeViewModel
import com.example.a40kcc.data.model.OutcomeViewModelFactory
import com.example.a40kcc.data.model.OutcomeWithPlayersViewModel
import com.example.a40kcc.data.model.OutcomeWithPlayersViewModelFactory
import com.example.a40kcc.data.model.PlayerViewModel
import com.example.a40kcc.data.model.PlayerViewModelFactory
import com.example.a40kcc.data.model.PlayerWithTeamsViewModel
import com.example.a40kcc.data.model.PlayerWithTeamsViewModelFactory
import com.example.a40kcc.data.model.PredictionViewModel
import com.example.a40kcc.data.model.PredictionViewModelFactory
import com.example.a40kcc.data.model.RoundViewModel
import com.example.a40kcc.data.model.RoundViewModelFactory
import com.example.a40kcc.data.model.RoundWithTournamentViewModel
import com.example.a40kcc.data.model.RoundWithTournamentViewModelFactory
import com.example.a40kcc.data.model.TeamViewModel
import com.example.a40kcc.data.model.TeamViewModelFactory
import com.example.a40kcc.data.model.TeamWithPlayersViewModel
import com.example.a40kcc.data.model.TeamWithPlayersViewModelFactory
import com.example.a40kcc.data.model.TournamentViewModel
import com.example.a40kcc.data.model.TournamentViewModelFactory
import com.example.a40kcc.data.model.TournamentWithRoundsViewModel
import com.example.a40kcc.data.model.TournamentWithRoundsViewModelFactory
import com.example.a40kcc.data.`object`.DataObject
import com.example.a40kcc.data.`object`.RoundWithTournament
import com.example.a40kcc.data.`object`.Team
import com.example.a40kcc.data.`object`.TournamentWithRounds
import com.example.a40kcc.ui.object_compose.GameCompose
import com.example.a40kcc.ui.object_compose.PlayerCompose
import com.example.a40kcc.ui.object_compose.TeamCompose
import com.example.a40kcc.ui.object_compose.TournamentCompose
import com.example.a40kcc.ui.screen.AddTournament
import com.example.a40kcc.ui.screen.DataScreen
import com.example.a40kcc.ui.screen.EditGame
import com.example.a40kcc.ui.screen.EditTournament
import com.example.a40kcc.ui.screen.HomeScreen
import com.example.a40kcc.ui.screen.LiveRoundScreen
import com.example.a40kcc.ui.screen.ObjectScreen
import com.example.a40kcc.ui.theme.Theme40kCC
import com.example.a40kcc.ui.utilities.ComposeData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GAME_VIEW_MODEL = ViewModelProvider(
            owner = this,
            factory = GameViewModelFactory((application as Application40kCC).game)
        )[GameViewModel::class.java]
        GAME_EXPANDED_VIEW_MODEL = ViewModelProvider(
            owner = this,
            factory = GameExpandedViewModelFactory((application as Application40kCC).gameExpanded)
        )[GameExpandedViewModel::class.java]
        LIVE_ROUND_VIEW_MODEL = ViewModelProvider(
            owner = this,
            factory = LiveRoundViewModelFactory((application as Application40kCC).liveRound)
        )[LiveRoundViewModel::class.java]
        LIVE_ROUND_EXPANDED_VIEW_MODEL = ViewModelProvider(
            owner = this,
            LiveRoundExpandedViewModelFactory((application as Application40kCC).liveRoundExpanded)
        )[LiveRoundExpandedViewModel::class.java]
        OUTCOME_VIEW_MODEL = ViewModelProvider(
            owner = this,
            factory = OutcomeViewModelFactory((application as Application40kCC).outcome)
        )[OutcomeViewModel::class.java]
        OUTCOME_WITH_PLAYERS_VIEW_MODEL = ViewModelProvider(
            owner = this,
            factory = OutcomeWithPlayersViewModelFactory((application as Application40kCC).outcomeWithPlayers)
        )[OutcomeWithPlayersViewModel::class.java]
        PLAYER_VIEW_MODEL = ViewModelProvider(
            owner = this,
            factory = PlayerViewModelFactory((application as Application40kCC).player)
        )[PlayerViewModel::class.java]
        PLAYER_WITH_TEAMS_VIEW_MODEL = ViewModelProvider(
            owner = this,
            factory = PlayerWithTeamsViewModelFactory((application as Application40kCC).playerWithTeams)
        )[PlayerWithTeamsViewModel::class.java]
        PREDICTION_VIEW_MODEL = ViewModelProvider(
            owner = this,
            factory = PredictionViewModelFactory((application as Application40kCC).prediction)
        )[PredictionViewModel::class.java]
        ROUND_VIEW_MODEL = ViewModelProvider(
            owner = this,
            factory = RoundViewModelFactory((application as Application40kCC).round)
        )[RoundViewModel::class.java]
        ROUND_WITH_TOURNAMENT_VIEW_MODEL = ViewModelProvider(
            owner = this,
            factory = RoundWithTournamentViewModelFactory((application as Application40kCC).roundWithTournament)
        )[RoundWithTournamentViewModel::class.java]
        TEAM_VIEW_MODEL = ViewModelProvider(
            owner = this,
            factory = TeamViewModelFactory((application as Application40kCC).team)
        )[TeamViewModel::class.java]
        TEAM_WITH_PLAYERS_VIEW_MODEL = ViewModelProvider(
            owner = this,
            factory = TeamWithPlayersViewModelFactory((application as Application40kCC).teamWithPlayers)
        )[TeamWithPlayersViewModel::class.java]
        TOURNAMENT_VIEW_MODEL = ViewModelProvider(
            owner = this,
            factory = TournamentViewModelFactory((application as Application40kCC).tournament)
        )[TournamentViewModel::class.java]
        TOURNAMENT_WITH_ROUNDS_VIEW_MODEL = ViewModelProvider(
            owner = this,
            factory = TournamentWithRoundsViewModelFactory((application as Application40kCC).tournamentWithRounds)
        )[TournamentWithRoundsViewModel::class.java]
        setContent {
            Theme40kCC {
                val margin = dimensionResource(id = R.dimen.margin_small)
                val modifier = Modifier.padding(horizontal = margin, vertical = margin)
                val scope = rememberCoroutineScope()
                val snackbarHostState = remember { SnackbarHostState() }
                COMPOSE_DATA = remember {
                    ComposeData(
                        snackbarHostState = snackbarHostState,
                        coroutineScope = scope,
                        modifier = modifier
                    )
                }
                Scaffold(
                    snackbarHost = {
                        SnackbarHost(hostState = snackbarHostState)
                    }
                ) { paddingValues ->
                    Surface(modifier = modifier.fillMaxSize()) {
                        val activity = (LocalContext.current as Activity)
                        DEPLOYMENT_DATA = DataObject(
                            resources = activity.resources,
                            headerID = R.array.DeploymentHeader,
                            dataID = R.array.Deployments
                        )
                        FACTION_DATA = DataObject(
                            resources = activity.resources,
                            headerID = R.array.FactionsHeader,
                            dataID = R.array.Factions
                        )
                        PRIMARY_MISSION_DATA = DataObject(
                            resources = activity.resources,
                            headerID = R.array.PrimaryMissionHeader,
                            dataID = R.array.PrimaryMissions
                        )
                        SECONDARY_MISSION_DATA = DataObject(
                            resources = activity.resources,
                            headerID = R.array.SecondaryMissionHeader,
                            dataID = R.array.SecondaryMissions
                        )
                        val navController = rememberNavController()
                        NavHost(
                            navController = navController,
                            startDestination = "home", Modifier
                                .padding(paddingValues)
                        ) {
                            composable(route = "home") {
                                HomeScreen(
                                    navController = navController,
                                    modifier = modifier
                                )
                            }
                            composable(route = "deployments") {
                                DataScreen(
                                    data = DEPLOYMENT_DATA,
                                    navController = navController,
                                    modifier = modifier
                                )
                            }
                            composable(route = "factions") {
                                DataScreen(
                                    data = FACTION_DATA,
                                    navController = navController,
                                    modifier = modifier
                                )
                            }
                            composable(route = "games") {
                                GAME_EXPANDED_VIEW_MODEL.allGamesFlow.observeAsState().value?.let { games ->
                                    ObjectScreen(
                                        objectList = games,
                                        navController = navController,
                                        objectCompose = GameCompose(),
                                        modifier = modifier
                                    )
                                }
                            }
                            composable(route = "liveRound") {
                                val tournament: TournamentWithRounds? by remember {
                                    mutableStateOf(
                                        null
                                    )
                                }
                                val round: RoundWithTournament? by remember { mutableStateOf(null) }
                                val team: Team? by remember { mutableStateOf(null) }
                                LiveRoundScreen(
                                    navController = navController,
                                    rememberedTournament = tournament,
                                    rememberedRound = round,
                                    rememberedTeam = team,
                                    modifier = modifier
                                )
                            }
                            composable(route = "primaryMissions") {
                                DataScreen(
                                    data = PRIMARY_MISSION_DATA,
                                    navController = navController,
                                    modifier = modifier
                                )
                            }
                            composable(route = "secondaryMissions") {
                                DataScreen(
                                    data = SECONDARY_MISSION_DATA,
                                    navController = navController,
                                    modifier = modifier
                                )
                            }
                            composable(route = "players") {
                                PLAYER_WITH_TEAMS_VIEW_MODEL.allPlayersFlow.observeAsState().value?.let { players ->
                                    ObjectScreen(
                                        objectList = players,
                                        navController = navController,
                                        objectCompose = PlayerCompose(),
                                        modifier = modifier
                                    )
                                }
                            }
                            composable(route = "teams") {
                                TEAM_WITH_PLAYERS_VIEW_MODEL.allTeamsFlow.observeAsState().value?.let { teams ->
                                    ObjectScreen(
                                        objectList = teams,
                                        navController = navController,
                                        objectCompose = TeamCompose(),
                                        modifier = modifier
                                    )
                                }
                            }
                            composable(route = "tournaments") {
                                TOURNAMENT_VIEW_MODEL.allTournamentsFlow.observeAsState().value?.let { tournaments ->
                                    ObjectScreen(
                                        objectList = tournaments,
                                        navController = navController,
                                        objectCompose = TournamentCompose(),
                                        modifier = modifier
                                    )
                                }
                            }
                            composable(route = "editGame?gameId={gameId}",
                                arguments = listOf(
                                    navArgument(
                                        name = "gameId", builder = { type = NavType.IntType }
                                    )
                                )
                            ) {
                                val gameId = it.arguments?.getInt("gameId")
                                if (gameId == null) {
                                    navController.navigate(route = "games")
                                } else {
                                    EditGame(
                                        coreObject = GAME_EXPANDED_VIEW_MODEL.getById(gameId = gameId),
                                        modifier = modifier,
                                        onDismissRequest = { navController.navigate(route = "games") }
                                    )
                                }
                            }
                            composable(route = "addTournament") {
                                AddTournament(
                                    modifier = modifier,
                                    onDismissRequest = { navController.navigate(route = "tournaments") }
                                )
                            }
                            composable(route = "editTournament?tournamentId={tournamentId}",
                                arguments = listOf(
                                    navArgument(
                                        name = "tournamentId", builder = { type = NavType.IntType }
                                    )
                                )
                            ) {
                                val tournamentId = it.arguments?.getInt("tournamentId")
                                if (tournamentId == null) {
                                    navController.navigate(route = "tournaments")
                                } else {
                                    EditTournament(
                                        coreObject = TOURNAMENT_WITH_ROUNDS_VIEW_MODEL.getById(
                                            tournamentId = tournamentId
                                        ),
                                        modifier = modifier,
                                        onDismissRequest = { navController.navigate(route = "tournaments") }
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