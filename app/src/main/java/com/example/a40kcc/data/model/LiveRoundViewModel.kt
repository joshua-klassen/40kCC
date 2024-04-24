@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a40kcc.data.`object`.LiveRound
import com.example.a40kcc.data.repository.LiveRoundRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class LiveRoundViewModel(private val liveRoundRepository: LiveRoundRepository) : ViewModel() {
    val allLiveRounds: List<LiveRound> = liveRoundRepository.allLiveRounds
    val allLiveRoundsFlow: LiveData<List<LiveRound>> =
        liveRoundRepository.allLiveRoundsFlow.asLiveData()

    fun getById(liveRoundId: Int): LiveRound {
        return liveRoundRepository.getById(liveRoundId)
    }

    fun getByGameId(gameId: Int): LiveRound? {
        return liveRoundRepository.getByGameId(gameId)
    }

    fun getByExpectedResultId(predictionId: Int): LiveRound? {
        return liveRoundRepository.getByExpectedResultId(predictionId)
    }

    fun insert(liveRound: LiveRound, exceptionHandler: CoroutineExceptionHandler) {
        viewModelScope.launch(exceptionHandler) {
            liveRoundRepository.insert(liveRound)
        }
    }

    fun update(liveRound: LiveRound, exceptionHandler: CoroutineExceptionHandler) {
        viewModelScope.launch(exceptionHandler) {
            liveRoundRepository.update(liveRound)
        }
    }

    fun delete(liveRound: LiveRound, exceptionHandler: CoroutineExceptionHandler) {
        viewModelScope.launch(exceptionHandler) {
            liveRoundRepository.delete(liveRound)
        }
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