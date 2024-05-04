package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.LiveRoundDao
import com.example.a40kcc.data.`object`.LiveRound
import kotlinx.coroutines.flow.Flow

class LiveRoundRepository(private val liveRoundDao: LiveRoundDao) {
    val allLiveRoundsFlow: Flow<List<LiveRound>> = liveRoundDao.getAllFlow()

    @WorkerThread
    fun allLiveRounds(): List<LiveRound> {
        return liveRoundDao.getAll()
    }

    @WorkerThread
    fun getById(liveRoundId: Int): LiveRound {
        return liveRoundDao.getById(liveRoundId)
    }

    @WorkerThread
    fun getByGameId(gameId: Int): LiveRound? {
        return liveRoundDao.getByGameId(gameId)
    }

    @WorkerThread
    fun getByExpectedResultId(predictionId: Int): LiveRound? {
        return liveRoundDao.getByExpectedResultId(predictionId)
    }

    @WorkerThread
    suspend fun insert(liveRound: LiveRound) {
        liveRoundDao.insert(liveRound)
    }

    @WorkerThread
    suspend fun update(liveRound: LiveRound) {
        liveRoundDao.update(liveRound)
    }

    @WorkerThread
    suspend fun delete(liveRound: LiveRound) {
        liveRoundDao.delete(liveRound)
    }
}