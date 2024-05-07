package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.LiveRoundExpandedDao
import com.example.a40kcc.data.`object`.LiveRoundExpanded
import kotlinx.coroutines.flow.Flow

class LiveRoundExpandedRepository(private val liveRoundExpandedDao: LiveRoundExpandedDao) {
    val allLiveRoundsFlow: Flow<List<LiveRoundExpanded>> = liveRoundExpandedDao.getAllFlow()

    @WorkerThread
    fun allLiveRounds(): List<LiveRoundExpanded> {
        return liveRoundExpandedDao.getAll()
    }

    @WorkerThread
    fun getById(liveRoundId: Int): LiveRoundExpanded {
        return liveRoundExpandedDao.getById(liveRoundId)
    }

    @WorkerThread
    fun getByGameId(gameId: Int): LiveRoundExpanded {
        return liveRoundExpandedDao.getByGameId(gameId)
    }

    @WorkerThread
    fun getByExpectedResultId(predictionId: Int): LiveRoundExpanded {
        return liveRoundExpandedDao.getByExpectedResultId(predictionId)
    }
}