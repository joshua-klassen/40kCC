package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.TeamWithPlayersDao
import com.example.a40kcc.data.`object`.TeamWithPlayers
import kotlinx.coroutines.flow.Flow

class TeamWithPlayersRepository(private val teamDao: TeamWithPlayersDao) {
    val allTeams: List<TeamWithPlayers> = teamDao.getAll()
    val allTeamsFlow: Flow<List<TeamWithPlayers>> = teamDao.getAllFlow()

    @WorkerThread
    fun getById(teamId: Int): TeamWithPlayers {
        return teamDao.getById(teamId)
    }

    @WorkerThread
    fun getByName(teamName: String): TeamWithPlayers {
        return teamDao.getByName(teamName)
    }

    @WorkerThread
    fun insert(playerID: Int, teamID: Int) {
        teamDao.insert(playerID, teamID)
    }
}