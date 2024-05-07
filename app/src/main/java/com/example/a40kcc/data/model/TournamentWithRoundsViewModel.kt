@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.TournamentWithRounds
import com.example.a40kcc.data.repository.TournamentWithRoundsRepository
import java.sql.Date

class TournamentWithRoundsViewModel(private val tournamentWithRoundsRepository: TournamentWithRoundsRepository) :
    ViewModel() {
    val allTournamentsFlow: LiveData<List<TournamentWithRounds>> =
        tournamentWithRoundsRepository.allTournamentsFlow.asLiveData()

    fun allTournaments(): List<TournamentWithRounds> {
        return tournamentWithRoundsRepository.allTournaments()
    }

    fun getById(tournamentId: Int): TournamentWithRounds {
        return tournamentWithRoundsRepository.getById(tournamentId)
    }

    fun getByName(tournamentName: String): List<TournamentWithRounds> {
        return tournamentWithRoundsRepository.getByName(tournamentName)
    }

    fun getByDate(tournamentDate: Date): List<TournamentWithRounds> {
        return tournamentWithRoundsRepository.getByDate(tournamentDate)
    }

    suspend fun insert(tournamentID: Int, roundID: Int) {
        tournamentWithRoundsRepository.insert(tournamentID, roundID)
    }
}

class TournamentWithRoundsViewModelFactory(private val tournamentWithRoundsRepository: TournamentWithRoundsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TournamentWithRoundsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TournamentWithRoundsViewModel(tournamentWithRoundsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}