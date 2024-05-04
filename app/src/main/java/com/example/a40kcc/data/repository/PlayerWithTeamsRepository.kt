package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.PlayerWithTeamsDao
import com.example.a40kcc.data.`object`.PlayerWithTeams
import kotlinx.coroutines.flow.Flow

class PlayerWithTeamsRepository(private val playerDao: PlayerWithTeamsDao) {
    val allPlayersFlow: Flow<List<PlayerWithTeams>> = playerDao.getAllFlow()

    @WorkerThread
    fun allPlayers(): List<PlayerWithTeams> {
        return playerDao.getAll()
    }

    @WorkerThread
    fun getById(playerId: Int): PlayerWithTeams {
        return playerDao.getById(playerId)
    }

    @WorkerThread
    fun getByName(playerName: String): PlayerWithTeams {
        return playerDao.getByName(playerName)
    }

    @WorkerThread
    fun getByFactionName(factionName: String): List<PlayerWithTeams> {
        return playerDao.getByFactionName(factionName)
    }

    @WorkerThread
    fun getByNickname(playerNickname: String): PlayerWithTeams {
        return playerDao.getByNickname(playerNickname)
    }

    @WorkerThread
    fun insert(playerID: Int, teamID: Int) {
        playerDao.insert(playerID, teamID)
    }
}