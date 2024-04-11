@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.TeamWithPlayers
import com.example.a40kcc.data.repository.TeamWithPlayersRepository

class TeamWithPlayersViewModel(private val teamRepository: TeamWithPlayersRepository) :
    ViewModel() {
    val allTeams: List<TeamWithPlayers> =
        teamRepository.allTeams
    val allTeamsFlow: LiveData<List<TeamWithPlayers>> =
        teamRepository.allTeamsFlow.asLiveData()

    fun getById(teamID: Int): TeamWithPlayers {
        return teamRepository.getById(teamID)
    }

    fun getByName(teamName: String): TeamWithPlayers {
        return teamRepository.getByName(teamName)
    }

    fun insert(playerID: Int, teamID: Int) {
        teamRepository.insert(playerID, teamID)
    }
}

class TeamWithPlayersViewModelFactory(private val teamRepository: TeamWithPlayersRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamWithPlayersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TeamWithPlayersViewModel(teamRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}