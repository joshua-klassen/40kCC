@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.TeamWithPlayers
import com.example.a40kcc.data.repository.TeamWithPlayersRepository

class TeamWithPlayersViewModel(private val teamWithPlayersRepository: TeamWithPlayersRepository) :
    ViewModel() {
    val allTeamsFlow: LiveData<List<TeamWithPlayers>> =
        teamWithPlayersRepository.allTeamsFlow.asLiveData()

    fun allTeams(): List<TeamWithPlayers> {
        return teamWithPlayersRepository.allTeams()
    }

    fun getById(teamID: Int): TeamWithPlayers {
        return teamWithPlayersRepository.getById(teamID)
    }

    fun getByName(teamName: String): TeamWithPlayers {
        return teamWithPlayersRepository.getByName(teamName)
    }

    suspend fun insert(playerID: Int, teamID: Int) {
        teamWithPlayersRepository.insert(playerID, teamID)
    }
}

class TeamWithPlayersViewModelFactory(private val teamWithPlayersRepository: TeamWithPlayersRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamWithPlayersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TeamWithPlayersViewModel(teamWithPlayersRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}