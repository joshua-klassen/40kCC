@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.GameExpanded
import com.example.a40kcc.data.repository.GameExpandedRepository

class GameExpandedViewModel(private val gameRepository: GameExpandedRepository) : ViewModel() {
    val allGames: List<GameExpanded> =
        gameRepository.allGames
    val allGamesFlow: LiveData<List<GameExpanded>> =
        gameRepository.allGamesFlow.asLiveData()

    fun getById(gameId: Int): GameExpanded {
        return gameRepository.getById(gameId)
    }
}

class GameExpandedViewModelFactory(private val gameRepository: GameExpandedRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameExpandedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameExpandedViewModel(gameRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}