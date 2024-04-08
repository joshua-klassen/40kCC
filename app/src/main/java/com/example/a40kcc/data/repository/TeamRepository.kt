package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.TeamDao
import com.example.a40kcc.data.`object`.Team
import com.example.a40kcc.data.`object`.TeamExpanded
import kotlinx.coroutines.flow.Flow

class TeamRepository(private val teamDao: TeamDao) {
    val allTeams: Flow<List<Team>> = teamDao.getAll()
    val allTeamsExpanded: Flow<List<TeamExpanded>> = teamDao.getAllExpanded()

    @WorkerThread
    fun getTeam(teamId: Int): Flow<Team> {
        return teamDao.getTeam(teamId)
    }

    @WorkerThread
    fun getTeamExpanded(teamId: Int): Flow<TeamExpanded> {
        return teamDao.getTeamExpanded(teamId)
    }

    @WorkerThread
    fun getTeam(teamName: String): Flow<Team> {
        return teamDao.getTeam(teamName)
    }

    @WorkerThread
    fun getTeamExpanded(teamName: String): Flow<TeamExpanded> {
        return teamDao.getTeamExpanded(teamName)
    }

    @WorkerThread
    suspend fun insert(team: Team) {
        teamDao.insert(team)
    }

    @WorkerThread
    suspend fun update(team: Team) {
        teamDao.update(team)
    }

    @WorkerThread
    suspend fun delete(team: Team) {
        teamDao.delete(team)
    }
}