package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.RoundDao
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.data.`object`.RoundExpanded
import kotlinx.coroutines.flow.Flow

class RoundRepository(private val roundDao: RoundDao) {
    val allRounds: Flow<List<Round>> = roundDao.getAll()
    val allRoundsExpanded: Flow<List<RoundExpanded>> = roundDao.getAllExpanded()

    @WorkerThread
    fun getRound(roundId: Int): Flow<Round> {
        return roundDao.getById(roundId)
    }

    @WorkerThread
    fun getRoundByNumber(roundNumber: Int): Flow<List<Round>> {
        return roundDao.getRoundByNumber(roundNumber)
    }

    @WorkerThread
    fun getRoundByPrimaryMission(primaryMissionName: String): Flow<List<Round>> {
        return roundDao.getRoundByPrimaryMission(primaryMissionName)
    }

    @WorkerThread
    fun getRoundBySecondaryMission(secondaryMissionName: String): Flow<List<Round>> {
        return roundDao.getRoundBySecondaryMission(secondaryMissionName)
    }

    @WorkerThread
    fun getRoundByDeployment(deploymentName: String): Flow<List<Round>> {
        return roundDao.getRoundByDeployment(deploymentName)
    }

    @WorkerThread
    fun getRoundByTournament(tournamentId: Int): Flow<List<Round>> {
        return roundDao.getRoundByTournament(tournamentId)
    }

    @WorkerThread
    fun getRoundExpanded(roundId: Int): Flow<RoundExpanded> {
        return roundDao.getByIdExpanded(roundId)
    }

    @WorkerThread
    fun getRoundByNumberExpanded(roundNumber: Int): Flow<List<RoundExpanded>> {
        return roundDao.getRoundByNumberExpanded(roundNumber)
    }

    @WorkerThread
    fun getRoundByPrimaryMissionExpanded(primaryMissionName: String): Flow<List<RoundExpanded>> {
        return roundDao.getRoundByPrimaryMissionExpanded(primaryMissionName)
    }

    @WorkerThread
    fun getRoundBySecondaryMissionExpanded(secondaryMissionName: String): Flow<List<RoundExpanded>> {
        return roundDao.getRoundBySecondaryMissionExpanded(secondaryMissionName)
    }

    @WorkerThread
    fun getRoundByDeploymentExpanded(deploymentName: String): Flow<List<RoundExpanded>> {
        return roundDao.getRoundByDeploymentExpanded(deploymentName)
    }

    @WorkerThread
    fun getRoundByTournamentExpanded(tournamentId: Int): Flow<List<RoundExpanded>> {
        return roundDao.getRoundByTournamentExpanded(tournamentId)
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