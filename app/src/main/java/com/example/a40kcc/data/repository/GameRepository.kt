package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.GameDao
import com.example.a40kcc.data.`object`.Game
import kotlinx.coroutines.flow.Flow

class GameRepository(private val gameDao: GameDao) {
    val allGamesFlow: Flow<List<Game>> = gameDao.getAllFlow()

    @WorkerThread
    fun allGames(): List<Game> {
        return gameDao.getAll()
    }

    @WorkerThread
    fun getById(gameId: Int): Game {
        return gameDao.getById(gameId)
    }

    @WorkerThread
    fun getByPlayerId(playerId: Int): List<Game> {
        return gameDao.getByPlayerId(playerId)
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