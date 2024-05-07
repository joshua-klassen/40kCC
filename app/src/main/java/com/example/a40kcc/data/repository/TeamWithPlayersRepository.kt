package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.TeamWithPlayersDao
import com.example.a40kcc.data.`object`.TeamWithPlayers
import kotlinx.coroutines.flow.Flow

class TeamWithPlayersRepository(private val teamWithPlayersDao: TeamWithPlayersDao) {
    val allTeamsFlow: Flow<List<TeamWithPlayers>> = teamWithPlayersDao.getAllFlow()

    @WorkerThread
    fun allTeams(): List<TeamWithPlayers> {
        return teamWithPlayersDao.getAll()
    }

    @WorkerThread
    fun getById(teamId: Int): TeamWithPlayers {
        return teamWithPlayersDao.getById(teamId)
    }

    @WorkerThread
    fun getByName(teamName: String): TeamWithPlayers {
        return teamWithPlayersDao.getByName(teamName)
    }

    @WorkerThread
    suspend fun insert(playerID: Int, teamID: Int) {
        teamWithPlayersDao.insert(playerID, teamID)
    }
}