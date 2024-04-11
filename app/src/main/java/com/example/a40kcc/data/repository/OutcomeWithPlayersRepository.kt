package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.OutcomeWithPlayersDao
import com.example.a40kcc.data.`object`.OutcomeWithPlayers
import kotlinx.coroutines.flow.Flow

class OutcomeWithPlayersRepository(private val outcomeDao: OutcomeWithPlayersDao) {
    val allOutcomes: List<OutcomeWithPlayers> = outcomeDao.getAll()
    val allOutcomesFlow: Flow<List<OutcomeWithPlayers>> = outcomeDao.getAllFlow()

    @WorkerThread
    fun getById(outcomeId: Int): OutcomeWithPlayers {
        return outcomeDao.getById(outcomeId)
    }

    @WorkerThread
    fun getByPlayerId(playerId: Int): List<OutcomeWithPlayers> {
        return outcomeDao.getByPlayerId(playerId)
    }
}