package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.OutcomeDao
import com.example.a40kcc.data.`object`.Outcome
import com.example.a40kcc.data.`object`.OutcomeExpanded
import kotlinx.coroutines.flow.Flow

class OutcomeRepository(private val outcomeDao: OutcomeDao) {
    val allOutcomes: Flow<List<Outcome>> = outcomeDao.getAll()
    val allOutcomesExpanded: Flow<List<OutcomeExpanded>> = outcomeDao.getAllExpanded()

    @WorkerThread
    fun getOutcome(outcomeId: Int): Flow<Outcome> {
        return outcomeDao.getById(outcomeId)
    }

    @WorkerThread
    fun getOutcomeExpanded(outcomeId: Int): Flow<OutcomeExpanded> {
        return outcomeDao.getByIdExpanded(outcomeId)
    }

    @WorkerThread
    fun getOutcomeByPlayer(playerId: Int): Flow<List<Outcome>> {
        return outcomeDao.getOutcomesByPlayer(playerId)
    }

    @WorkerThread
    fun getOutcomeByPlayerExpanded(playerId: Int): Flow<List<OutcomeExpanded>> {
        return outcomeDao.getOutcomesByPlayerExpanded(playerId)
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