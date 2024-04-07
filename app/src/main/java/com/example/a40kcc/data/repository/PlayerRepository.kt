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
    fun getPlayer(playerId: Int): Flow<Player> {
        return playerDao.getById(playerId)
    }

    @WorkerThread
    fun getPlayer(playerName: String): Flow<Player> {
        return playerDao.getPlayerByName(playerName)
    }

    @WorkerThread
    fun getPlayersByFaction(factionName: String): Flow<List<Player>> {
        return playerDao.getPlayersByFaction(factionName)
    }

    @WorkerThread
    fun getPlayerByNickname(playerNickname: String): Flow<Player> {
        return playerDao.getPlayerByNickname(playerNickname)
    }

    @WorkerThread
    fun getPlayerExpanded(playerId: Int): Flow<PlayerExpanded> {
        return playerDao.getByIdExpanded(playerId)
    }

    @WorkerThread
    fun getPlayerExpanded(playerName: String): Flow<PlayerExpanded> {
        return playerDao.getPlayerByNameExpanded(playerName)
    }

    @WorkerThread
    fun getPlayersByFactionExpanded(factionName: String): Flow<List<PlayerExpanded>> {
        return playerDao.getPlayersByFactionExpanded(factionName)
    }

    @WorkerThread
    fun getPlayerByNicknameExpanded(playerNickname: String): Flow<PlayerExpanded> {
        return playerDao.getPlayerByNicknameExpanded(playerNickname)
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