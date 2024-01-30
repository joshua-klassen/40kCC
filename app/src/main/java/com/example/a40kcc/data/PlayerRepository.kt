package com.example.a40kcc.data

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class PlayerRepository(private val playerDao: PlayerDao) {
    val allPlayers: Flow<List<Player>> = playerDao.getAll()

    @WorkerThread
    suspend fun insert(player: Player) {
        playerDao.insert(player)
    }
}