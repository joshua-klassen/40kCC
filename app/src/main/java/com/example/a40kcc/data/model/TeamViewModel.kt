package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.Team
import com.example.a40kcc.data.repository.TeamRepository

class TeamViewModel(private val teamRepository: TeamRepository) : ViewModel() {
    val allTeams: LiveData<List<Team>> = teamRepository.allTeams.asLiveData()
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