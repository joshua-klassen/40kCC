@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.Game
import com.example.a40kcc.data.repository.GameRepository

class GameViewModel(private val gameRepository: GameRepository) : ViewModel() {
    val allGamesFlow: LiveData<List<Game>> = gameRepository.allGamesFlow.asLiveData()

    fun allGames(): List<Game> {
        return gameRepository.allGames()
    }

    fun getById(gameId: Int): Game {
        return gameRepository.getById(gameId)
    }

    fun getByPlayerId(playerId: Int): List<Game> {
        return gameRepository.getByPlayerId(playerId)
    }

    suspend fun insert(game: Game) {
        gameRepository.insert(game)
    }

    suspend fun update(game: Game) {
        gameRepository.update(game)
    }

    suspend fun delete(game: Game) {
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