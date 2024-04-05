package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a40kcc.data.`object`.Game
import com.example.a40kcc.data.`object`.GameExpanded
import com.example.a40kcc.data.repository.GameRepository
import kotlinx.coroutines.launch

class GameViewModel(private val gameRepository: GameRepository) : ViewModel() {
    val allGames: LiveData<List<Game>> = gameRepository.allGames.asLiveData()
    val allGamesExpanded: LiveData<List<GameExpanded>> =
        gameRepository.allGamesExpanded.asLiveData()

    fun insert(game: Game) = viewModelScope.launch {
        gameRepository.insert(game)
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