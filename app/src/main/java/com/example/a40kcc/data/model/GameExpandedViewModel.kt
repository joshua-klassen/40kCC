@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.GameExpanded
import com.example.a40kcc.data.repository.GameExpandedRepository

class GameExpandedViewModel(private val gameExpandedRepository: GameExpandedRepository) :
    ViewModel() {
    val allGamesFlow: LiveData<List<GameExpanded>> =
        gameExpandedRepository.allGamesFlow.asLiveData()

    fun allGames(): List<GameExpanded> {
        return gameExpandedRepository.allGames()
    }

    fun getById(gameId: Int): GameExpanded {
        return gameExpandedRepository.getById(gameId)
    }
}

class GameExpandedViewModelFactory(private val gameExpandedRepository: GameExpandedRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameExpandedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameExpandedViewModel(gameExpandedRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}