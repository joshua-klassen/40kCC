package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.RoundDao
import com.example.a40kcc.data.`object`.Round
import kotlinx.coroutines.flow.Flow

class RoundRepository(private val roundDao: RoundDao) {
    val allRoundsFlow: Flow<List<Round>> = roundDao.getAllFlow()

    @WorkerThread
    fun allRounds(): List<Round> {
        return roundDao.getAll()
    }

    @WorkerThread
    fun getById(roundId: Int): Round {
        return roundDao.getById(roundId)
    }

    @WorkerThread
    fun getByRoundNumber(roundNumber: Int): List<Round> {
        return roundDao.getByRoundNumber(roundNumber)
    }

    @WorkerThread
    fun getByPrimaryMission(primaryMissionName: String): List<Round> {
        return roundDao.getByPrimaryMission(primaryMissionName)
    }

    @WorkerThread
    fun getBySecondaryMission(secondaryMissionName: String): List<Round> {
        return roundDao.getBySecondaryMission(secondaryMissionName)
    }

    @WorkerThread
    fun getByDeployment(deploymentName: String): List<Round> {
        return roundDao.getByDeployment(deploymentName)
    }

    @WorkerThread
    fun getByTournamentId(tournamentId: Int): List<Round> {
        return roundDao.getByTournamentId(tournamentId)
    }

    @WorkerThread
    suspend fun insert(round: Round) {
        roundDao.insert(round)
    }

    @WorkerThread
    suspend fun update(round: Round) {
        roundDao.update(round)
    }

    @WorkerThread
    suspend fun delete(round: Round) {
        roundDao.delete(round)
    }
}