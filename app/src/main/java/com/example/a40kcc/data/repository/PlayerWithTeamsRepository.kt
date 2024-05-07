package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.PlayerWithTeamsDao
import com.example.a40kcc.data.`object`.PlayerWithTeams
import kotlinx.coroutines.flow.Flow

class PlayerWithTeamsRepository(private val playerWithTeamsDao: PlayerWithTeamsDao) {
    val allPlayersFlow: Flow<List<PlayerWithTeams>> = playerWithTeamsDao.getAllFlow()

    @WorkerThread
    fun allPlayers(): List<PlayerWithTeams> {
        return playerWithTeamsDao.getAll()
    }

    @WorkerThread
    fun getById(playerId: Int): PlayerWithTeams {
        return playerWithTeamsDao.getById(playerId)
    }

    @WorkerThread
    fun getByName(playerName: String): PlayerWithTeams {
        return playerWithTeamsDao.getByName(playerName)
    }

    @WorkerThread
    fun getByFactionName(factionName: String): List<PlayerWithTeams> {
        return playerWithTeamsDao.getByFactionName(factionName)
    }

    @WorkerThread
    fun getByNickname(playerNickname: String): PlayerWithTeams {
        return playerWithTeamsDao.getByNickname(playerNickname)
    }

    @WorkerThread
    suspend fun insert(playerID: Int, teamID: Int) {
        playerWithTeamsDao.insert(playerID, teamID)
    }
}