package com.example.a40kcc

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.a40kcc.data.model.GameViewModel
import com.example.a40kcc.data.model.GameViewModelFactory
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
import com.example.a40kcc.data.`object`.DataObject
import com.example.a40kcc.ui.screen.DataScreen
import com.example.a40kcc.ui.screen.GameScreen
import com.example.a40kcc.ui.screen.HomeScreen
import com.example.a40kcc.ui.screen.OutcomeScreen
import com.example.a40kcc.ui.screen.PlayerScreen
import com.example.a40kcc.ui.screen.PredictionScreen
import com.example.a40kcc.ui.screen.RoundScreen
import com.example.a40kcc.ui.screen.TeamScreen
import com.example.a40kcc.ui.screen.TournamentScreen
import com.example.a40kcc.ui.theme.Theme40kCC

class MainActivity : ComponentActivity() {
    private lateinit var deploymentData: DataObject
    private lateinit var factionData: DataObject
    private lateinit var primaryMissionData: DataObject
    private lateinit var secondaryMissionData: DataObject

    private val gameViewModel: GameViewModel by viewModels {
        GameViewModelFactory((application as Application40kCC).game)
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
            Theme40kCC {
                val margin = dimensionResource(id = R.dimen.margin_small)
                val modifier = Modifier.padding(margin, margin)
                Surface(modifier = modifier.fillMaxSize()) {
                    val activity = (LocalContext.current as Activity)
                    deploymentData = DataObject(
                        activity.resources,
                        R.array.DeploymentHeader,
                        R.array.Deployments
                    )
                    factionData = DataObject(
                        activity.resources,
                        R.array.FactionsHeader,
                        R.array.Factions
                    )
                    primaryMissionData = DataObject(
                        activity.resources,
                        R.array.PrimaryMissionHeader,
                        R.array.PrimaryMissions
                    )
                    secondaryMissionData = DataObject(
                        activity.resources,
                        R.array.SecondaryMissionHeader,
                        R.array.SecondaryMissions
                    )
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") { HomeScreen(navController, modifier) }
                        composable("deployments") {
                            DataScreen(
                                deploymentData.getDataKeys(),
                                navController,
                                modifier,
                                deploymentData
                            )
                        }
                        composable("factions") {
                            DataScreen(
                                factionData.getDataKeys(),
                                navController,
                                modifier,
                                factionData
                            )
                        }
                        composable("games") {
                            GameScreen(
                                gameViewModel,
                                onBackClick = { navController.navigateUp() })
                        }
                        composable("primaryMissions") {
                            DataScreen(
                                primaryMissionData.getDataKeys(),
                                navController,
                                modifier,
                                primaryMissionData
                            )
                        }
                        composable("secondaryMissions") {
                            DataScreen(
                                secondaryMissionData.getDataKeys(),
                                navController,
                                modifier,
                                secondaryMissionData
                            )
                        }
                        composable("outcomes") {
                            OutcomeScreen(
                                outcomeViewModel,
                                onBackClick = { navController.navigateUp() })
                        }
                        composable("players") {
                            PlayerScreen(
                                playerViewModel,
                                onBackClick = { navController.navigateUp() })
                        }
                        composable("predictions") {
                            PredictionScreen(
                                predictionViewModel,
                                onBackClick = { navController.navigateUp() })
                        }
                        composable("rounds") {
                            RoundScreen(
                                roundViewModel,
                                onBackClick = { navController.navigateUp() })
                        }
                        composable("teams") {
                            TeamScreen(
                                teamViewModel,
                                onBackClick = { navController.navigateUp() })
                        }
                        composable("tournaments") {
                            TournamentScreen(
                                tournamentViewModel,
                                onBackClick = { navController.navigateUp() })
                        }
                    }
                }
            }
        }
    }
}