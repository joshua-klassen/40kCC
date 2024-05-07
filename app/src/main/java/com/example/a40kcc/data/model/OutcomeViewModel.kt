@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.Outcome
import com.example.a40kcc.data.repository.OutcomeRepository

class OutcomeViewModel(private val outcomeRepository: OutcomeRepository) : ViewModel() {
    val allOutcomesFlow: LiveData<List<Outcome>> = outcomeRepository.allOutcomesFlow.asLiveData()

    fun allOutcomes(): List<Outcome> {
        return outcomeRepository.allOutcomes()
    }

    fun getById(gameId: Int): Outcome {
        return outcomeRepository.getById(gameId)
    }

    fun getByPlayerId(playerId: Int): List<Outcome> {
        return outcomeRepository.getByPlayerId(playerId)
    }

    fun getByPlayers(player01Id: Int, player02Id: Int): List<Outcome> {
        return outcomeRepository.getByPlayers(player01Id, player02Id)
    }

    suspend fun insert(outcome: Outcome) {
        outcomeRepository.insert(outcome)
    }

    suspend fun update(outcome: Outcome) {
        outcomeRepository.update(outcome)
    }
}

class OutcomeViewModelFactory(private val outcomeRepository: OutcomeRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OutcomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OutcomeViewModel(outcomeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}