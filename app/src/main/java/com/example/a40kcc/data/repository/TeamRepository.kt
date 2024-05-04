package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.TeamDao
import com.example.a40kcc.data.`object`.Team
import kotlinx.coroutines.flow.Flow

class TeamRepository(private val teamDao: TeamDao) {
    val allTeamsFlow: Flow<List<Team>> = teamDao.getAllFlow()

    @WorkerThread
    fun allTeams(): List<Team> {
        return teamDao.getAll()
    }

    @WorkerThread
    fun getById(teamId: Int): Team {
        return teamDao.getById(teamId)
    }

    @WorkerThread
    fun getByName(teamName: String): Team {
        return teamDao.getByName(teamName)
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