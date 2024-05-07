package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.OutcomeDao
import com.example.a40kcc.data.`object`.Outcome
import kotlinx.coroutines.flow.Flow

class OutcomeRepository(private val outcomeDao: OutcomeDao) {
    val allOutcomesFlow: Flow<List<Outcome>> = outcomeDao.getAllFlow()

    @WorkerThread
    fun allOutcomes(): List<Outcome> {
        return outcomeDao.getAll()
    }

    @WorkerThread
    fun getById(outcomeId: Int): Outcome {
        return outcomeDao.getById(outcomeId)
    }

    @WorkerThread
    fun getByPlayerId(playerId: Int): List<Outcome> {
        return outcomeDao.getByPlayerId(playerId)
    }

    @WorkerThread
    fun getByPlayers(player01Id: Int, player02Id: Int): List<Outcome> {
        return outcomeDao.getByPlayers(player01Id, player02Id)
    }

    @WorkerThread
    suspend fun insert(outcome: Outcome) {
        outcomeDao.insert(outcome)
    }

    @WorkerThread
    suspend fun update(outcome: Outcome) {
        outcomeDao.update(outcome)
    }
}