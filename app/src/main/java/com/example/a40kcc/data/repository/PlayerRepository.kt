package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.PlayerDao
import com.example.a40kcc.data.`object`.Player
import kotlinx.coroutines.flow.Flow

class PlayerRepository(private val playerDao: PlayerDao) {
    val allPlayersFlow: Flow<List<Player>> = playerDao.getAllFlow()

    @WorkerThread
    fun allPlayers(): List<Player> {
        return playerDao.getAll()
    }

    @WorkerThread
    fun getById(playerId: Int): Player {
        return playerDao.getById(playerId)
    }

    @WorkerThread
    fun getByName(playerName: String): Player {
        return playerDao.getByName(playerName)
    }

    @WorkerThread
    fun getByFactionName(factionName: String): List<Player> {
        return playerDao.getByFactionName(factionName)
    }

    @WorkerThread
    fun getByNickname(playerNickname: String): Player {
        return playerDao.getByNickname(playerNickname)
    }

    @WorkerThread
    suspend fun insert(player: Player) {
        playerDao.insert(player)
    }

    @WorkerThread
    suspend fun update(player: Player) {
        playerDao.update(player)
    }

    @WorkerThread
    suspend fun delete(player: Player) {
        playerDao.delete(player)
    }
}