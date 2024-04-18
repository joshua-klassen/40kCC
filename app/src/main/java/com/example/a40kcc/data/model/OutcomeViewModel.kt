@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a40kcc.data.`object`.Outcome
import com.example.a40kcc.data.repository.OutcomeRepository
import kotlinx.coroutines.launch

class OutcomeViewModel(private val outcomeRepository: OutcomeRepository) : ViewModel() {
    val allOutcomes: List<Outcome> = outcomeRepository.allOutcomes
    val allOutcomesFlow: LiveData<List<Outcome>> = outcomeRepository.allOutcomesFlow.asLiveData()

    fun getById(gameId: Int): Outcome {
        return outcomeRepository.getById(gameId)
    }

    fun getByPlayerId(playerId: Int): List<Outcome> {
        return outcomeRepository.getByPlayerId(playerId)
    }

    fun insert(outcome: Outcome) = viewModelScope.launch {
        outcomeRepository.insert(outcome)
    }

    fun update(outcome: Outcome) = viewModelScope.launch {
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