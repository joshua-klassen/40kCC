package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.OutcomeWithPlayersDao
import com.example.a40kcc.data.`object`.OutcomeWithPlayers
import kotlinx.coroutines.flow.Flow

class OutcomeWithPlayersRepository(private val outcomeWithPlayersDao: OutcomeWithPlayersDao) {
    val allOutcomesFlow: Flow<List<OutcomeWithPlayers>> = outcomeWithPlayersDao.getAllFlow()

    @WorkerThread
    fun allOutcomes(): List<OutcomeWithPlayers> {
        return outcomeWithPlayersDao.getAll()
    }

    @WorkerThread
    fun getById(outcomeId: Int): OutcomeWithPlayers {
        return outcomeWithPlayersDao.getById(outcomeId)
    }

    @WorkerThread
    fun getByPlayerId(playerId: Int): List<OutcomeWithPlayers> {
        return outcomeWithPlayersDao.getByPlayerId(playerId)
    }
}