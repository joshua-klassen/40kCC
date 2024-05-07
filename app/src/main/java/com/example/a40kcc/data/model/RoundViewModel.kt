@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.data.repository.RoundRepository

class RoundViewModel(private val roundRepository: RoundRepository) : ViewModel() {
    val allRoundsFlow: LiveData<List<Round>> = roundRepository.allRoundsFlow.asLiveData()

    fun allRounds(): List<Round> {
        return roundRepository.allRounds()
    }

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

    suspend fun insert(round: Round) {
        roundRepository.insert(round)
    }

    suspend fun update(round: Round) {
        roundRepository.update(round)
    }

    suspend fun delete(round: Round) {
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