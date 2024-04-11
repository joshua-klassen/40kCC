package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.GameExpandedDao
import com.example.a40kcc.data.`object`.GameExpanded
import kotlinx.coroutines.flow.Flow

class GameExpandedRepository(private val gameDao: GameExpandedDao) {
    val allGames: List<GameExpanded> = gameDao.getAll()
    val allGamesFlow: Flow<List<GameExpanded>> = gameDao.getAllFlow()

    @WorkerThread
    fun getById(gameId: Int): GameExpanded {
        return gameDao.getById(gameId)
    }
}