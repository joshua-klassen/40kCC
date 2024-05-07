package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.GameExpandedDao
import com.example.a40kcc.data.`object`.GameExpanded
import kotlinx.coroutines.flow.Flow

class GameExpandedRepository(private val gameExpandedDao: GameExpandedDao) {
    val allGamesFlow: Flow<List<GameExpanded>> = gameExpandedDao.getAllFlow()

    @WorkerThread
    fun allGames(): List<GameExpanded> {
        return gameExpandedDao.getAll()
    }

    @WorkerThread
    fun getById(gameId: Int): GameExpanded {
        return gameExpandedDao.getById(gameId)
    }
}