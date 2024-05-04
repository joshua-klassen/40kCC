@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a40kcc.data.`object`.TeamWithPlayers
import com.example.a40kcc.data.repository.TeamWithPlayersRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class TeamWithPlayersViewModel(private val teamRepository: TeamWithPlayersRepository) :
    ViewModel() {
    val allTeamsFlow: LiveData<List<TeamWithPlayers>> =
        teamRepository.allTeamsFlow.asLiveData()

    fun allTeams(): List<TeamWithPlayers> {
        return teamRepository.allTeams()
    }

    fun getById(teamID: Int): TeamWithPlayers {
        return teamRepository.getById(teamID)
    }

    fun getByName(teamName: String): TeamWithPlayers {
        return teamRepository.getByName(teamName)
    }

    fun insert(playerID: Int, teamID: Int, exceptionHandler: CoroutineExceptionHandler) {
        viewModelScope.launch(exceptionHandler) {
            teamRepository.insert(playerID, teamID)
        }
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