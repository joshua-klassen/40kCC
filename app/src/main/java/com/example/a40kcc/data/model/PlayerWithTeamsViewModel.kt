@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.PlayerWithTeams
import com.example.a40kcc.data.repository.PlayerWithTeamsRepository

class PlayerWithTeamsViewModel(private val playerWithTeamsRepository: PlayerWithTeamsRepository) :
    ViewModel() {
    val allPlayersFlow: LiveData<List<PlayerWithTeams>> =
        playerWithTeamsRepository.allPlayersFlow.asLiveData()

    fun allPlayers(): List<PlayerWithTeams> {
        return playerWithTeamsRepository.allPlayers()
    }

    fun getById(playerId: Int): PlayerWithTeams {
        return playerWithTeamsRepository.getById(playerId)
    }

    fun getByName(playerName: String): PlayerWithTeams {
        return playerWithTeamsRepository.getByName(playerName)
    }

    fun getByFactionName(factionName: String): List<PlayerWithTeams> {
        return playerWithTeamsRepository.getByFactionName(factionName)
    }

    fun getByNickname(playerNickname: String): PlayerWithTeams {
        return playerWithTeamsRepository.getByNickname(playerNickname)
    }

    suspend fun insert(playerID: Int, teamID: Int) {
        playerWithTeamsRepository.insert(playerID, teamID)
    }
}

class PlayerWithTeamsViewModelFactory(private val playerWithTeamsRepository: PlayerWithTeamsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerWithTeamsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlayerWithTeamsViewModel(playerWithTeamsRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}