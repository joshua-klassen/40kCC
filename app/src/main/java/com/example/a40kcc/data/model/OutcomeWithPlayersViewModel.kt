@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.OutcomeWithPlayers
import com.example.a40kcc.data.repository.OutcomeWithPlayersRepository

class OutcomeWithPlayersViewModel(private val outcomeWithPlayersRepository: OutcomeWithPlayersRepository) :
    ViewModel() {
    val allOutcomesFlow: LiveData<List<OutcomeWithPlayers>> =
        outcomeWithPlayersRepository.allOutcomesFlow.asLiveData()

    fun allOutcomes(): List<OutcomeWithPlayers> {
        return outcomeWithPlayersRepository.allOutcomes()
    }

    fun getById(gameId: Int): OutcomeWithPlayers {
        return outcomeWithPlayersRepository.getById(gameId)
    }

    fun getByPlayerId(gameId: Int): List<OutcomeWithPlayers> {
        return outcomeWithPlayersRepository.getByPlayerId(gameId)
    }
}

class OutcomeWithPlayersViewModelFactory(private val outcomeWithPlayersRepository: OutcomeWithPlayersRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OutcomeWithPlayersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OutcomeWithPlayersViewModel(outcomeWithPlayersRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}