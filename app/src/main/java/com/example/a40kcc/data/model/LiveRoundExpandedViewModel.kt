@file:Suppress("unused")

package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.LiveRoundExpanded
import com.example.a40kcc.data.repository.LiveRoundExpandedRepository

class LiveRoundExpandedViewModel(private val liveRoundRepository: LiveRoundExpandedRepository) :
    ViewModel() {
    val allLiveRoundsFlow: LiveData<List<LiveRoundExpanded>> =
        liveRoundRepository.allLiveRoundsFlow.asLiveData()

    fun allLiveRounds(): List<LiveRoundExpanded> {
        return liveRoundRepository.allLiveRounds()
    }

    fun getById(liveRoundId: Int): LiveRoundExpanded {
        return liveRoundRepository.getById(liveRoundId)
    }

    fun getByGameId(gameId: Int): LiveRoundExpanded {
        return liveRoundRepository.getByGameId(gameId)
    }

    fun getByExpectedResultId(predictionId: Int): LiveRoundExpanded {
        return liveRoundRepository.getByExpectedResultId(predictionId)
    }
}

class LiveRoundExpandedViewModelFactory(private val liveRoundRepository: LiveRoundExpandedRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LiveRoundExpandedViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LiveRoundExpandedViewModel(liveRoundRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}