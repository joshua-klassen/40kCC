package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.RoundWithTournamentDao
import com.example.a40kcc.data.`object`.RoundWithTournament
import kotlinx.coroutines.flow.Flow

class RoundWithTournamentRepository(private val roundWithTournamentDao: RoundWithTournamentDao) {
    val allRoundsFlow: Flow<List<RoundWithTournament>> = roundWithTournamentDao.getAllFlow()

    @WorkerThread
    fun allRounds(): List<RoundWithTournament> {
        return roundWithTournamentDao.getAll()
    }

    @WorkerThread
    fun getById(roundId: Int): RoundWithTournament {
        return roundWithTournamentDao.getById(roundId)
    }

    @WorkerThread
    fun getByRoundNumber(roundNumber: Int): List<RoundWithTournament> {
        return roundWithTournamentDao.getByRoundNumber(roundNumber)
    }

    @WorkerThread
    fun getByPrimaryMission(primaryMissionName: String): List<RoundWithTournament> {
        return roundWithTournamentDao.getByPrimaryMission(primaryMissionName)
    }

    @WorkerThread
    fun getBySecondaryMission(secondaryMissionName: String): List<RoundWithTournament> {
        return roundWithTournamentDao.getBySecondaryMission(secondaryMissionName)
    }

    @WorkerThread
    fun getByDeployment(deploymentName: String): List<RoundWithTournament> {
        return roundWithTournamentDao.getByDeployment(deploymentName)
    }

    @WorkerThread
    suspend fun insert(tournamentID: Int, roundID: Int) {
        roundWithTournamentDao.insert(tournamentID, roundID)
    }
}