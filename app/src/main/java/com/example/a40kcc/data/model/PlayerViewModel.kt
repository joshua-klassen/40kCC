@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a40kcc.data.`object`.Player
import com.example.a40kcc.data.repository.PlayerRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class PlayerViewModel(private val playerRepository: PlayerRepository) : ViewModel() {
    val allPlayersFlow: LiveData<List<Player>> = playerRepository.allPlayersFlow.asLiveData()

    fun allPlayers(): List<Player> {
        return playerRepository.allPlayers()
    }

    fun getById(playerId: Int): Player {
        return playerRepository.getById(playerId)
    }

    fun getByName(playerName: String): Player {
        return playerRepository.getByName(playerName)
    }

    fun getByFactionName(factionName: String): List<Player> {
        return playerRepository.getByFactionName(factionName)
    }

    fun getByNickname(playerNickname: String): Player {
        return playerRepository.getByNickname(playerNickname)
    }

    fun insert(player: Player, exceptionHandler: CoroutineExceptionHandler) {
        viewModelScope.launch(exceptionHandler) {
            playerRepository.insert(player)
        }
    }

    fun update(player: Player, exceptionHandler: CoroutineExceptionHandler) {
        viewModelScope.launch(exceptionHandler) {
            playerRepository.update(player)
        }
    }

    fun delete(player: Player, exceptionHandler: CoroutineExceptionHandler) {
        viewModelScope.launch(exceptionHandler) {
            playerRepository.delete(player)
        }
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