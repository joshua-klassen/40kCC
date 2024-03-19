package com.example.a40kcc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a40kcc.data.model.DeploymentViewModel
import com.example.a40kcc.data.model.DeploymentViewModelFactory
import com.example.a40kcc.data.model.FactionViewModel
import com.example.a40kcc.data.model.FactionViewModelFactory
import com.example.a40kcc.data.model.GameViewModel
import com.example.a40kcc.data.model.GameViewModelFactory
import com.example.a40kcc.data.model.MissionViewModel
import com.example.a40kcc.data.model.MissionViewModelFactory
import com.example.a40kcc.data.model.ObjectiveViewModel
import com.example.a40kcc.data.model.ObjectiveViewModelFactory
import com.example.a40kcc.data.model.OutcomeViewModel
import com.example.a40kcc.data.model.OutcomeViewModelFactory
import com.example.a40kcc.data.model.PlayerViewModel
import com.example.a40kcc.data.model.PlayerViewModelFactory
import com.example.a40kcc.data.model.PredictionViewModel
import com.example.a40kcc.data.model.PredictionViewModelFactory
import com.example.a40kcc.data.model.RoundViewModel
import com.example.a40kcc.data.model.RoundViewModelFactory
import com.example.a40kcc.data.model.TeamViewModel
import com.example.a40kcc.data.model.TeamViewModelFactory
import com.example.a40kcc.data.model.TournamentViewModel
import com.example.a40kcc.data.model.TournamentViewModelFactory
import com.example.a40kcc.ui.screen.DeploymentScreen
import com.example.a40kcc.ui.screen.FactionScreen
import com.example.a40kcc.ui.screen.GameScreen
import com.example.a40kcc.ui.screen.HomeScreen
import com.example.a40kcc.ui.screen.MissionScreen
import com.example.a40kcc.ui.screen.ObjectiveScreen
import com.example.a40kcc.ui.screen.OutcomeScreen
import com.example.a40kcc.ui.screen.PlayerScreen
import com.example.a40kcc.ui.screen.PredictionScreen
import com.example.a40kcc.ui.screen.RoundScreen
import com.example.a40kcc.ui.screen.TeamScreen
import com.example.a40kcc.ui.screen.TournamentScreen
import com.example.a40kcc.ui.theme._40kCCTheme

class MainActivity : ComponentActivity() {
    private val deploymentViewModel: DeploymentViewModel by viewModels {
        DeploymentViewModelFactory((application as Application40kCC).deployment)
    }

    private val factionViewModel: FactionViewModel by viewModels {
        FactionViewModelFactory((application as Application40kCC).faction)
    }

    private val gameViewModel: GameViewModel by viewModels {
        GameViewModelFactory((application as Application40kCC).game)
    }

    private val missionViewModel: MissionViewModel by viewModels {
        MissionViewModelFactory((application as Application40kCC).mission)
    }

    private val objectiveViewModel: ObjectiveViewModel by viewModels {
        ObjectiveViewModelFactory((application as Application40kCC).objective)
    }

    private val outcomeViewModel: OutcomeViewModel by viewModels {
        OutcomeViewModelFactory((application as Application40kCC).outcome)
    }

    private val playerViewModel: PlayerViewModel by viewModels {
        PlayerViewModelFactory((application as Application40kCC).player)
    }

    private val predictionViewModel: PredictionViewModel by viewModels {
        PredictionViewModelFactory((application as Application40kCC).prediction)
    }

    private val roundViewModel: RoundViewModel by viewModels {
        RoundViewModelFactory((application as Application40kCC).round)
    }

    private val teamViewModel: TeamViewModel by viewModels {
        TeamViewModelFactory((application as Application40kCC).team)
    }

    private val tournamentViewModel: TournamentViewModel by viewModels {
        TournamentViewModelFactory((application as Application40kCC).tournament)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _40kCCTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") { HomeScreen(navController) }
                        composable("deployments") {
                            DeploymentScreen(
                                deploymentViewModel,
                                onBackClick = { navController.navigate("home") })
                        }
                        composable("factions") {
                            FactionScreen(
                                factionViewModel,
                                onBackClick = { navController.navigate("home") })
                        }
                        composable("games") {
                            GameScreen(
                                gameViewModel,
                                onBackClick = { navController.navigate("home") })
                        }
                        composable("missions") {
                            MissionScreen(
                                missionViewModel,
                                onBackClick = { navController.navigate("home") })
                        }
                        composable("objectives") {
                            ObjectiveScreen(
                                objectiveViewModel,
                                onBackClick = { navController.navigate("home") })
                        }
                        composable("outcomes") {
                            OutcomeScreen(
                                outcomeViewModel,
                                onBackClick = { navController.navigate("home") })
                        }
                        composable("players") {
                            PlayerScreen(
                                playerViewModel,
                                onBackClick = { navController.navigate("home") })
                        }
                        composable("predictions") {
                            PredictionScreen(
                                predictionViewModel,
                                onBackClick = { navController.navigate("home") })
                        }
                        composable("rounds") {
                            RoundScreen(
                                roundViewModel,
                                onBackClick = { navController.navigate("home") })
                        }
                        composable("teams") {
                            TeamScreen(
                                teamViewModel,
                                onBackClick = { navController.navigate("home") })
                        }
                        composable("tournaments") {
                            TournamentScreen(
                                tournamentViewModel,
                                onBackClick = { navController.navigate("home") })
                        }
                    }
                }
            }
        }
    }
}