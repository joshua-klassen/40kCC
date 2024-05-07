@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.LiveRound
import com.example.a40kcc.data.repository.LiveRoundRepository

class LiveRoundViewModel(private val liveRoundRepository: LiveRoundRepository) : ViewModel() {
    val allLiveRoundsFlow: LiveData<List<LiveRound>> =
        liveRoundRepository.allLiveRoundsFlow.asLiveData()

    fun allLiveRounds(): List<LiveRound> {
        return liveRoundRepository.allLiveRounds()
    }

    fun getById(liveRoundId: Int): LiveRound {
        return liveRoundRepository.getById(liveRoundId)
    }

    fun getByGameId(gameId: Int): LiveRound? {
        return liveRoundRepository.getByGameId(gameId)
    }

    fun getByExpectedResultId(predictionId: Int): LiveRound? {
        return liveRoundRepository.getByExpectedResultId(predictionId)
    }

    suspend fun insert(liveRound: LiveRound) {
        liveRoundRepository.insert(liveRound)
    }

    suspend fun update(liveRound: LiveRound) {
        liveRoundRepository.update(liveRound)
    }

    suspend fun delete(liveRound: LiveRound) {
        liveRoundRepository.delete(liveRound)
    }
}

class LiveRoundViewModelFactory(private val liveRoundRepository: LiveRoundRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LiveRoundViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LiveRoundViewModel(liveRoundRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}