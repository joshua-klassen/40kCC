package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.RoundWithTournamentDao
import com.example.a40kcc.data.`object`.RoundWithTournament
import kotlinx.coroutines.flow.Flow

class RoundWithTournamentRepository(private val roundDao: RoundWithTournamentDao) {
    val allRoundsFlow: Flow<List<RoundWithTournament>> = roundDao.getAllFlow()

    @WorkerThread
    fun allRounds(): List<RoundWithTournament> {
        return roundDao.getAll()
    }

    @WorkerThread
    fun getById(roundId: Int): RoundWithTournament {
        return roundDao.getById(roundId)
    }

    @WorkerThread
    fun getByRoundNumber(roundNumber: Int): List<RoundWithTournament> {
        return roundDao.getByRoundNumber(roundNumber)
    }

    @WorkerThread
    fun getByPrimaryMission(primaryMissionName: String): List<RoundWithTournament> {
        return roundDao.getByPrimaryMission(primaryMissionName)
    }

    @WorkerThread
    fun getBySecondaryMission(secondaryMissionName: String): List<RoundWithTournament> {
        return roundDao.getBySecondaryMission(secondaryMissionName)
    }

    @WorkerThread
    fun getByDeployment(deploymentName: String): List<RoundWithTournament> {
        return roundDao.getByDeployment(deploymentName)
    }

    @WorkerThread
    fun getByTournamentId(tournamentId: Int): List<RoundWithTournament> {
        return roundDao.getByTournamentId(tournamentId)
    }
}