@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.Tournament
import com.example.a40kcc.data.repository.TournamentRepository
import java.sql.Date

class TournamentViewModel(private val tournamentRepository: TournamentRepository) : ViewModel() {
    val allTournamentsFlow: LiveData<List<Tournament>> =
        tournamentRepository.allTournamentsFlow.asLiveData()

    fun allTournaments(): List<Tournament> {
        return tournamentRepository.allTournaments()
    }

    fun getById(tournamentId: Int): Tournament {
        return tournamentRepository.getById(tournamentId)
    }

    fun getByName(tournamentName: String): List<Tournament> {
        return tournamentRepository.getByName(tournamentName)
    }

    fun getByDate(tournamentDate: Date): List<Tournament> {
        return tournamentRepository.getByDate(tournamentDate)
    }

    suspend fun insert(tournament: Tournament) {
        tournamentRepository.insert(tournament)
    }

    suspend fun update(tournament: Tournament) {
        tournamentRepository.update(tournament)
    }

    suspend fun delete(tournament: Tournament) {
        tournamentRepository.delete(tournament)
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