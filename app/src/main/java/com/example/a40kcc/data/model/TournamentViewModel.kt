package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a40kcc.data.`object`.Tournament
import com.example.a40kcc.data.repository.TournamentRepository
import kotlinx.coroutines.launch

class TournamentViewModel(private val tournamentRepository: TournamentRepository) : ViewModel() {
    val allTournaments: LiveData<List<Tournament>> =
        tournamentRepository.allTournaments.asLiveData()

    fun insert(tournament: Tournament) = viewModelScope.launch {
        tournamentRepository.insert(tournament)
    }

    fun delete(tournament: Tournament) = viewModelScope.launch {
        tournamentRepository.delete(tournament)
    }

    fun getByName(tournamentName: String): LiveData<List<Tournament>> {
        return tournamentRepository.getTournaments(tournamentName).asLiveData()
    }
}

class TournamentViewModelFactory(private val tournamentRepository: TournamentRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TournamentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TournamentViewModel(tournamentRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}