package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.GameDao
import com.example.a40kcc.data.`object`.Game
import com.example.a40kcc.data.`object`.GameExpanded
import kotlinx.coroutines.flow.Flow

class GameRepository(private val gameDao: GameDao) {
    val allGames: Flow<List<Game>> = gameDao.getAll()
    val allGamesExpanded: Flow<List<GameExpanded>> = gameDao.getAllExpanded()

    @WorkerThread
    fun getGame(gameId: Int): Flow<Game> {
        return gameDao.getById(gameId)
    }

    @WorkerThread
    fun getGameExpanded(gameId: Int): Flow<GameExpanded> {
        return gameDao.getByIdExpanded(gameId)
    }

    @WorkerThread
    suspend fun insert(game: Game) {
        gameDao.insert(game)
    }

    @WorkerThread
    suspend fun update(game: Game) {
        gameDao.update(game)
    }

    @WorkerThread
    suspend fun delete(game: Game) {
        gameDao.delete(game)
    }
}