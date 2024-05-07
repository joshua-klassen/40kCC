@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.Player
import com.example.a40kcc.data.repository.PlayerRepository

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

    suspend fun insert(player: Player) {
        playerRepository.insert(player)
    }

    suspend fun update(player: Player) {
        playerRepository.update(player)
    }

    suspend fun delete(player: Player) {
        playerRepository.delete(player)
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