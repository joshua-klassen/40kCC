package com.example.a40kcc

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
import com.example.a40kcc.data.`object`.DataObject
import com.example.a40kcc.ui.screen.DataScreen
import com.example.a40kcc.ui.screen.GameScreen
import com.example.a40kcc.ui.screen.HomeScreen
import com.example.a40kcc.ui.screen.OutcomeScreen
import com.example.a40kcc.ui.screen.PlayerScreen
import com.example.a40kcc.ui.screen.PreLiveRoundScreen
import com.example.a40kcc.ui.screen.PredictionScreen
import com.example.a40kcc.ui.screen.RoundScreen
import com.example.a40kcc.ui.screen.TeamScreen
import com.example.a40kcc.ui.screen.TournamentScreen
import com.example.a40kcc.ui.theme.Theme40kCC
import com.example.a40kcc.ui.utilities.DEPLOYMENT_DATA
import com.example.a40kcc.ui.utilities.FACTION_DATA
import com.example.a40kcc.ui.utilities.GAME_EXPANDED_VIEW_MODEL
import com.example.a40kcc.ui.utilities.GAME_VIEW_MODEL
import com.example.a40kcc.ui.utilities.LIVE_ROUND_EXPANDED_VIEW_MODEL
import com.example.a40kcc.ui.utilities.LIVE_ROUND_VIEW_MODEL
import com.example.a40kcc.ui.utilities.OUTCOME_VIEW_MODEL
import com.example.a40kcc.ui.utilities.OUTCOME_WITH_PLAYERS_VIEW_MODEL
import com.example.a40kcc.ui.utilities.PLAYER_VIEW_MODEL
import com.example.a40kcc.ui.utilities.PLAYER_WITH_TEAMS_VIEW_MODEL
import com.example.a40kcc.ui.utilities.PREDICTION_VIEW_MODEL
import com.example.a40kcc.ui.utilities.PRIMARY_MISSION_DATA
import com.example.a40kcc.ui.utilities.ROUND_VIEW_MODEL
import com.example.a40kcc.ui.utilities.ROUND_WITH_TOURNAMENT_VIEW_MODEL
import com.example.a40kcc.ui.utilities.SECONDARY_MISSION_DATA
import com.example.a40kcc.ui.utilities.TEAM_VIEW_MODEL
import com.example.a40kcc.ui.utilities.TEAM_WITH_PLAYERS_VIEW_MODEL
import com.example.a40kcc.ui.utilities.TOURNAMENT_VIEW_MODEL

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
        setContent {
            Theme40kCC {
                val margin = dimensionResource(id = R.dimen.margin_small)
                val modifier = Modifier.padding(horizontal = margin, vertical = margin)
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
                    NavHost(navController = navController, startDestination = "home") {
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
                            GameScreen(
                                onBackClick = { navController.navigateUp() },
                                modifier = modifier
                            )
                        }
                        composable(route = "liveRound") {
                            PreLiveRoundScreen(
                                onBackClick = { navController.navigateUp() },
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
                        composable(route = "outcomes") {
                            OutcomeScreen(
                                onBackClick = { navController.navigateUp() },
                                modifier = modifier
                            )
                        }
                        composable(route = "players") {
                            PlayerScreen(
                                onBackClick = { navController.navigateUp() },
                                modifier = modifier
                            )
                        }
                        composable(route = "predictions") {
                            PredictionScreen(
                                onBackClick = { navController.navigateUp() },
                                modifier = modifier
                            )
                        }
                        composable(route = "rounds") {
                            RoundScreen(
                                onBackClick = { navController.navigateUp() },
                                modifier = modifier
                            )
                        }
                        composable(route = "teams") {
                            TeamScreen(
                                onBackClick = { navController.navigateUp() },
                                modifier = modifier
                            )
                        }
                        composable(route = "tournaments") {
                            TournamentScreen(
                                onBackClick = { navController.navigateUp() },
                                modifier = modifier
                            )
                        }
                    }
                }
            }
        }
    }
}