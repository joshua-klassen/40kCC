package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.PlayerDao
import com.example.a40kcc.data.`object`.Player
import com.example.a40kcc.data.`object`.PlayerExpanded
import kotlinx.coroutines.flow.Flow

class PlayerRepository(private val playerDao: PlayerDao) {
    val allPlayers: Flow<List<Player>> = playerDao.getAll()
    val allPlayersExpanded: Flow<List<PlayerExpanded>> = playerDao.getAllExpanded()

    @WorkerThread
    fun getPlayer(playerId: Int): Player {
        return playerDao.getById(playerId)
    }

    @WorkerThread
    fun getPlayer(playerName: String): Flow<List<Player>> {
        return playerDao.getPlayersByName(playerName)
    }

    @WorkerThread
    fun getPlayersByFaction(factionId: Int): Flow<List<Player>> {
        return playerDao.getPlayersByFaction(factionId)
    }

    @WorkerThread
    fun getPlayerByNickname(playerNickname: String): Player {
        return playerDao.getPlayerByNickname(playerNickname)
    }

    @WorkerThread
    fun getPlayerExpanded(playerId: Int): PlayerExpanded {
        return playerDao.getByIdExpanded(playerId)
    }

    @WorkerThread
    fun getPlayerExpanded(playerName: String): Flow<List<PlayerExpanded>> {
        return playerDao.getPlayersByNameExpanded(playerName)
    }

    @WorkerThread
    fun getPlayersByFactionExpanded(factionId: Int): Flow<List<PlayerExpanded>> {
        return playerDao.getPlayersByFactionExpanded(factionId)
    }

    @WorkerThread
    fun getPlayerByNicknameExpanded(playerNickname: String): PlayerExpanded {
        return playerDao.getPlayerByNicknameExpanded(playerNickname)
    }

    @WorkerThread
    suspend fun insert(player: Player) {
        playerDao.insert(player)
    }

    @WorkerThread
    fun update(player: Player) {
        playerDao.update(player)
    }

    @WorkerThread
    fun delete(playerId: Int) {
        playerDao.delete(playerId)
    }
}