package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.OutcomeWithPlayersDao
import com.example.a40kcc.data.`object`.OutcomeWithPlayers
import kotlinx.coroutines.flow.Flow

class OutcomeWithPlayersRepository(private val outcomeDao: OutcomeWithPlayersDao) {
    val allOutcomesFlow: Flow<List<OutcomeWithPlayers>> = outcomeDao.getAllFlow()

    @WorkerThread
    fun allOutcomes(): List<OutcomeWithPlayers> {
        return outcomeDao.getAll()
    }

    @WorkerThread
    fun getById(outcomeId: Int): OutcomeWithPlayers {
        return outcomeDao.getById(outcomeId)
    }

    @WorkerThread
    fun getByPlayerId(playerId: Int): List<OutcomeWithPlayers> {
        return outcomeDao.getByPlayerId(playerId)
    }
}