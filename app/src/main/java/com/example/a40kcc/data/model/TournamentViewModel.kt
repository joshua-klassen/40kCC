package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.Tournament
import com.example.a40kcc.data.repository.TournamentRepository

class TournamentViewModel(private val tournamentRepository: TournamentRepository) : ViewModel() {
    val allTournaments: LiveData<List<Tournament>> =
        tournamentRepository.allTournaments.asLiveData()
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