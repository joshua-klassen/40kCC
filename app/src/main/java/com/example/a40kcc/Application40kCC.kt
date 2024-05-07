package com.example.a40kcc

import android.app.Application
import com.example.a40kcc.data.Application40kCCDatabase
import com.example.a40kcc.data.repository.GameExpandedRepository
import com.example.a40kcc.data.repository.GameRepository
import com.example.a40kcc.data.repository.LiveRoundExpandedRepository
import com.example.a40kcc.data.repository.LiveRoundRepository
import com.example.a40kcc.data.repository.OutcomeRepository
import com.example.a40kcc.data.repository.OutcomeWithPlayersRepository
import com.example.a40kcc.data.repository.PlayerRepository
import com.example.a40kcc.data.repository.PlayerWithTeamsRepository
import com.example.a40kcc.data.repository.PredictionRepository
import com.example.a40kcc.data.repository.RoundRepository
import com.example.a40kcc.data.repository.RoundWithTournamentRepository
import com.example.a40kcc.data.repository.TeamRepository
import com.example.a40kcc.data.repository.TeamWithPlayersRepository
import com.example.a40kcc.data.repository.TournamentRepository
import com.example.a40kcc.data.repository.TournamentWithRoundsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Application40kCC : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { Application40kCCDatabase.getDatabase(this, applicationScope) }
    val game by lazy { GameRepository(database.gameDao()) }
    val gameExpanded by lazy { GameExpandedRepository(database.gameExpandedDao()) }
    val liveRound by lazy { LiveRoundRepository(database.liveRoundDao()) }
    val liveRoundExpanded by lazy { LiveRoundExpandedRepository(database.liveRoundExpandedDao()) }
    val outcome by lazy { OutcomeRepository(database.outcomeDao()) }
    val outcomeWithPlayers by lazy { OutcomeWithPlayersRepository(database.outcomeWithPlayersDao()) }
    val player by lazy { PlayerRepository(database.playerDao()) }
    val playerWithTeams by lazy { PlayerWithTeamsRepository(database.playerWithTeamsDao()) }
    val prediction by lazy { PredictionRepository(database.predictionDao()) }
    val round by lazy { RoundRepository(database.roundDao()) }
    val roundWithTournament by lazy { RoundWithTournamentRepository(database.roundWithTournamentDao()) }
    val team by lazy { TeamRepository(database.teamDao()) }
    val teamWithPlayers by lazy { TeamWithPlayersRepository(database.teamWithPlayersDao()) }
    val tournament by lazy { TournamentRepository(database.tournamentDao()) }
    val tournamentWithRounds by lazy { TournamentWithRoundsRepository(database.tournamentWithRoundsDao()) }
}