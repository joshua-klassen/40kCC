@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.Team
import com.example.a40kcc.data.repository.TeamRepository

class TeamViewModel(private val teamRepository: TeamRepository) : ViewModel() {
    val allTeamsFlow: LiveData<List<Team>> = teamRepository.allTeamsFlow.asLiveData()

    fun allTeams(): List<Team> {
        return teamRepository.allTeams()
    }

    fun getById(teamID: Int): Team {
        return teamRepository.getById(teamID)
    }

    fun getByName(teamName: String): Team {
        return teamRepository.getByName(teamName)
    }

    suspend fun insert(team: Team) {
        teamRepository.insert(team)
    }

    suspend fun update(team: Team) {
        teamRepository.update(team)
    }

    suspend fun delete(team: Team) {
        teamRepository.delete(team)
    }
}

class TeamViewModelFactory(private val teamRepository: TeamRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TeamViewModel(teamRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}