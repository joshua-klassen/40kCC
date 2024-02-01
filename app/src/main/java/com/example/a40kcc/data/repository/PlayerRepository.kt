package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.PlayerDao
import com.example.a40kcc.data.`object`.Player
import com.example.a40kcc.data.`object`.PlayerExpanded
import kotlinx.coroutines.flow.Flow

class PlayerRepository(private val playerDao: PlayerDao) {
    val allPlayers: Flow<List<PlayerExpanded>> = playerDao.getAll()

    @WorkerThread
    suspend fun insert(player: Player) {
        playerDao.insert(player)
    }
}