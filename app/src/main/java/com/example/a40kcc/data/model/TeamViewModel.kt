package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a40kcc.data.`object`.Team
import com.example.a40kcc.data.`object`.TeamExpanded
import com.example.a40kcc.data.repository.TeamRepository
import kotlinx.coroutines.launch

class TeamViewModel(private val teamRepository: TeamRepository) : ViewModel() {
    val allTeams: LiveData<List<Team>> = teamRepository.allTeams.asLiveData()
    val allTeamsExpanded: LiveData<List<TeamExpanded>> =
        teamRepository.allTeamsExpanded.asLiveData()

    fun insert(team: Team) = viewModelScope.launch {
        teamRepository.insert(team)
    }

    fun update(team: Team) = viewModelScope.launch {
        teamRepository.update(team)
    }

    fun delete(team: Team) = viewModelScope.launch {
        teamRepository.delete(team)
    }

    fun getByName(teamName: String): LiveData<Team> {
        return teamRepository.getTeam(teamName).asLiveData()
    }

    fun getByNameExpanded(teamName: String): LiveData<TeamExpanded> {
        return teamRepository.getTeamExpanded(teamName).asLiveData()
    }

    fun getByID(teamID: Int): LiveData<Team> {
        return teamRepository.getTeam(teamID).asLiveData()
    }

    fun getByIDExpanded(teamID: Int): LiveData<TeamExpanded> {
        return teamRepository.getTeamExpanded(teamID).asLiveData()
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