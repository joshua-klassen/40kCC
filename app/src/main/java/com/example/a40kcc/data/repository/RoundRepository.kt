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
    fun getRoundByMission(missionId: Int): Flow<List<Round>> {
        return roundDao.getRoundByMission(missionId)
    }

    @WorkerThread
    fun getRoundByObjective(objectiveId: Int): Flow<List<Round>> {
        return roundDao.getRoundByObjective(objectiveId)
    }

    @WorkerThread
    fun getRoundByDeployment(deploymentId: Int): Flow<List<Round>> {
        return roundDao.getRoundByDeployment(deploymentId)
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
    fun getRoundByMissionExpanded(missionId: Int): Flow<List<RoundExpanded>> {
        return roundDao.getRoundByMissionExpanded(missionId)
    }

    @WorkerThread
    fun getRoundByObjectiveExpanded(objectiveId: Int): Flow<List<RoundExpanded>> {
        return roundDao.getRoundByObjectiveExpanded(objectiveId)
    }

    @WorkerThread
    fun getRoundByDeploymentExpanded(deploymentId: Int): Flow<List<RoundExpanded>> {
        return roundDao.getRoundByDeploymentExpanded(deploymentId)
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