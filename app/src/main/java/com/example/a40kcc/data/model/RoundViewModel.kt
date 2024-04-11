@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.data.repository.RoundRepository
import kotlinx.coroutines.launch

class RoundViewModel(private val roundRepository: RoundRepository) : ViewModel() {
    val allRounds: List<Round> = roundRepository.allRounds
    val allRoundsFlow: LiveData<List<Round>> = roundRepository.allRoundsFlow.asLiveData()

    fun getById(roundID: Int): Round {
        return roundRepository.getById(roundID)
    }

    fun getByRoundNumber(roundNumber: Int): List<Round> {
        return roundRepository.getByRoundNumber(roundNumber)
    }

    fun getByPrimaryMission(primaryMissionName: String): List<Round> {
        return roundRepository.getByPrimaryMission(primaryMissionName)
    }

    fun getBySecondaryMission(secondaryMissionName: String): List<Round> {
        return roundRepository.getBySecondaryMission(secondaryMissionName)
    }

    fun getByDeployment(deploymentName: String): List<Round> {
        return roundRepository.getByDeployment(deploymentName)
    }

    fun getByTournamentId(tournamentID: Int): List<Round> {
        return roundRepository.getByTournamentId(tournamentID)
    }

    fun insert(round: Round) = viewModelScope.launch {
        roundRepository.insert(round)
    }

    fun update(round: Round) = viewModelScope.launch {
        roundRepository.update(round)
    }

    fun delete(round: Round) = viewModelScope.launch {
        roundRepository.delete(round)
    }
}

class RoundViewModelFactory(private val roundRepository: RoundRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoundViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RoundViewModel(roundRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}