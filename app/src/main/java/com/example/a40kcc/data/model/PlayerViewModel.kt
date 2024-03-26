package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a40kcc.data.`object`.Player
import com.example.a40kcc.data.`object`.PlayerExpanded
import com.example.a40kcc.data.repository.PlayerRepository
import kotlinx.coroutines.launch

class PlayerViewModel(private val playerRepository: PlayerRepository) : ViewModel() {
    val allPlayers: LiveData<List<Player>> = playerRepository.allPlayers.asLiveData()
    val allPlayersExpanded: LiveData<List<PlayerExpanded>> =
        playerRepository.allPlayersExpanded.asLiveData()

    fun insert(player: Player) = viewModelScope.launch {
        playerRepository.insert(player)
    }
}

class PlayerViewModelFactory(private val playerRepository: PlayerRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlayerViewModel(playerRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}