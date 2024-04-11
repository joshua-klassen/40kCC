@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a40kcc.data.`object`.Game
import com.example.a40kcc.data.repository.GameRepository
import kotlinx.coroutines.launch

class GameViewModel(private val gameRepository: GameRepository) : ViewModel() {
    val allGames: List<Game> = gameRepository.allGames
    val allGamesFlow: LiveData<List<Game>> = gameRepository.allGamesFlow.asLiveData()

    fun getById(gameId: Int): Game {
        return gameRepository.getById(gameId)
    }

    fun insert(game: Game) = viewModelScope.launch {
        gameRepository.insert(game)
    }

    fun update(game: Game) = viewModelScope.launch {
        gameRepository.update(game)
    }

    fun delete(game: Game) = viewModelScope.launch {
        gameRepository.delete(game)
    }
}

class GameViewModelFactory(private val gameRepository: GameRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameViewModel(gameRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}