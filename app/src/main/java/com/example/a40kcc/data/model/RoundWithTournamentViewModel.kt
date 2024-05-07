@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.RoundWithTournament
import com.example.a40kcc.data.repository.RoundWithTournamentRepository

class RoundWithTournamentViewModel(private val roundWithTournamentRepository: RoundWithTournamentRepository) :
    ViewModel() {
    val allRoundsFlow: LiveData<List<RoundWithTournament>> =
        roundWithTournamentRepository.allRoundsFlow.asLiveData()

    fun allRounds(): List<RoundWithTournament> {
        return roundWithTournamentRepository.allRounds()
    }

    fun getById(roundID: Int): RoundWithTournament {
        return roundWithTournamentRepository.getById(roundID)
    }

    fun getByRoundNumber(roundNumber: Int): List<RoundWithTournament> {
        return roundWithTournamentRepository.getByRoundNumber(roundNumber)
    }

    fun getByPrimaryMission(primaryMissionName: String): List<RoundWithTournament> {
        return roundWithTournamentRepository.getByPrimaryMission(primaryMissionName)
    }

    fun getBySecondaryMission(secondaryMissionName: String): List<RoundWithTournament> {
        return roundWithTournamentRepository.getBySecondaryMission(secondaryMissionName)
    }

    fun getByDeployment(deploymentName: String): List<RoundWithTournament> {
        return roundWithTournamentRepository.getByDeployment(deploymentName)
    }

    suspend fun insert(tournamentID: Int, roundID: Int) {
        roundWithTournamentRepository.insert(tournamentID, roundID)
    }
}

class RoundWithTournamentViewModelFactory(private val roundWithTournamentRepository: RoundWithTournamentRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoundWithTournamentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RoundWithTournamentViewModel(roundWithTournamentRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}