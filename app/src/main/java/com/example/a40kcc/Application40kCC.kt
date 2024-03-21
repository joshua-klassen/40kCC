package com.example.a40kcc

import android.app.Application
import com.example.a40kcc.data.Application40kCCDatabase
import com.example.a40kcc.data.repository.GameRepository
import com.example.a40kcc.data.repository.OutcomeRepository
import com.example.a40kcc.data.repository.PlayerRepository
import com.example.a40kcc.data.repository.PredictionRepository
import com.example.a40kcc.data.repository.RoundRepository
import com.example.a40kcc.data.repository.TeamRepository
import com.example.a40kcc.data.repository.TournamentRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Application40kCC : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    private val database by lazy { Application40kCCDatabase.getDatabase(this, applicationScope) }
    val game by lazy { GameRepository(database.gameDao()) }
    val outcome by lazy { OutcomeRepository(database.outcomeDao()) }
    val player by lazy { PlayerRepository(database.playerDao()) }
    val prediction by lazy { PredictionRepository(database.predictionDao()) }
    val round by lazy { RoundRepository(database.roundDao()) }
    val team by lazy { TeamRepository(database.teamDao()) }
    val tournament by lazy { TournamentRepository(database.tournamentDao()) }
}