package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.TeamDao
import com.example.a40kcc.data.`object`.Team
import kotlinx.coroutines.flow.Flow

class TeamRepository(private val teamDao: TeamDao) {
    val allTeams: Flow<List<Team>> = teamDao.getAll()

    @WorkerThread
    fun getTeam(teamId: Int): Flow<Team> {
        return teamDao.getById(teamId)
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