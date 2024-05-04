@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a40kcc.data.`object`.Team
import com.example.a40kcc.data.repository.TeamRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

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

    fun insert(team: Team, exceptionHandler: CoroutineExceptionHandler) {
        viewModelScope.launch(exceptionHandler) {
            teamRepository.insert(team)
        }
    }

    fun update(team: Team, exceptionHandler: CoroutineExceptionHandler) {
        viewModelScope.launch(exceptionHandler) {
            teamRepository.update(team)
        }
    }

    fun delete(team: Team, exceptionHandler: CoroutineExceptionHandler) {
        viewModelScope.launch(exceptionHandler) {
            teamRepository.delete(team)
        }
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