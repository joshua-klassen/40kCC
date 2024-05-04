@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a40kcc.data.`object`.PlayerWithTeams
import com.example.a40kcc.data.repository.PlayerWithTeamsRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class PlayerWithTeamsViewModel(private val playerRepository: PlayerWithTeamsRepository) :
    ViewModel() {
    val allPlayersFlow: LiveData<List<PlayerWithTeams>> =
        playerRepository.allPlayersFlow.asLiveData()

    fun allPlayers(): List<PlayerWithTeams> {
        return playerRepository.allPlayers()
    }

    fun getById(playerId: Int): PlayerWithTeams {
        return playerRepository.getById(playerId)
    }

    fun getByName(playerName: String): PlayerWithTeams {
        return playerRepository.getByName(playerName)
    }

    fun getByFactionName(factionName: String): List<PlayerWithTeams> {
        return playerRepository.getByFactionName(factionName)
    }

    fun getByNickname(playerNickname: String): PlayerWithTeams {
        return playerRepository.getByNickname(playerNickname)
    }

    fun insert(playerID: Int, teamID: Int, exceptionHandler: CoroutineExceptionHandler) {
        viewModelScope.launch(exceptionHandler) {
            playerRepository.insert(playerID, teamID)
        }
    }
}

class PlayerWithTeamsViewModelFactory(private val playerRepository: PlayerWithTeamsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayerWithTeamsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PlayerWithTeamsViewModel(playerRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}