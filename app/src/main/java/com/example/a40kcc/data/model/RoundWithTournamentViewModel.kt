@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.RoundWithTournament
import com.example.a40kcc.data.repository.RoundWithTournamentRepository

class RoundWithTournamentViewModel(private val roundRepository: RoundWithTournamentRepository) :
    ViewModel() {
    val allRoundsFlow: LiveData<List<RoundWithTournament>> =
        roundRepository.allRoundsFlow.asLiveData()

    fun allRounds(): List<RoundWithTournament> {
        return roundRepository.allRounds()
    }

    fun getById(roundID: Int): RoundWithTournament {
        return roundRepository.getById(roundID)
    }

    fun getByRoundNumber(roundNumber: Int): List<RoundWithTournament> {
        return roundRepository.getByRoundNumber(roundNumber)
    }

    fun getByPrimaryMission(primaryMissionName: String): List<RoundWithTournament> {
        return roundRepository.getByPrimaryMission(primaryMissionName)
    }

    fun getBySecondaryMission(secondaryMissionName: String): List<RoundWithTournament> {
        return roundRepository.getBySecondaryMission(secondaryMissionName)
    }

    fun getByDeployment(deploymentName: String): List<RoundWithTournament> {
        return roundRepository.getByDeployment(deploymentName)
    }

    fun getByTournamentId(tournamentID: Int): List<RoundWithTournament> {
        return roundRepository.getByTournamentId(tournamentID)
    }
}

class RoundWithTournamentViewModelFactory(private val roundRepository: RoundWithTournamentRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoundWithTournamentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RoundWithTournamentViewModel(roundRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}