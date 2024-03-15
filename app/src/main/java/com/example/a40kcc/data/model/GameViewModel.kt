package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.Game
import com.example.a40kcc.data.repository.GameRepository

class GameViewModel(gameRepository: GameRepository) : ViewModel() {
    val allGames: LiveData<List<Game>> = gameRepository.allGames.asLiveData()
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