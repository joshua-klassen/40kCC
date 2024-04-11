package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.LiveRoundExpandedDao
import com.example.a40kcc.data.`object`.LiveRoundExpanded
import kotlinx.coroutines.flow.Flow

class LiveRoundExpandedRepository(private val liveRoundDao: LiveRoundExpandedDao) {
    val allLiveRounds: List<LiveRoundExpanded> = liveRoundDao.getAll()
    val allLiveRoundsFlow: Flow<List<LiveRoundExpanded>> = liveRoundDao.getAllFlow()

    @WorkerThread
    fun getById(liveRoundId: Int): LiveRoundExpanded {
        return liveRoundDao.getById(liveRoundId)
    }

    @WorkerThread
    fun getByGameId(gameId: Int): LiveRoundExpanded {
        return liveRoundDao.getByGameId(gameId)
    }

    @WorkerThread
    fun getByExpectedResultId(predictionId: Int): LiveRoundExpanded {
        return liveRoundDao.getByExpectedResultId(predictionId)
    }
}