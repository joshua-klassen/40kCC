package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.GameExpandedDao
import com.example.a40kcc.data.`object`.GameExpanded
import kotlinx.coroutines.flow.Flow

class GameExpandedRepository(private val gameDao: GameExpandedDao) {
    val allGamesFlow: Flow<List<GameExpanded>> = gameDao.getAllFlow()

    @WorkerThread
    fun allGames(): List<GameExpanded> {
        return gameDao.getAll()
    }

    @WorkerThread
    fun getById(gameId: Int): GameExpanded {
        return gameDao.getById(gameId)
    }
}