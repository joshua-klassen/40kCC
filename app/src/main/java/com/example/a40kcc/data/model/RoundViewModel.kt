package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.data.`object`.RoundExpanded
import com.example.a40kcc.data.repository.RoundRepository
import kotlinx.coroutines.launch

class RoundViewModel(private val roundRepository: RoundRepository) : ViewModel() {
    val allRounds: LiveData<List<Round>> = roundRepository.allRounds.asLiveData()
    val allRoundsExpanded: LiveData<List<RoundExpanded>> =
        roundRepository.allRoundsExpanded.asLiveData()

    fun insert(round: Round) = viewModelScope.launch {
        roundRepository.insert(round)
    }

    fun delete(round: Round) = viewModelScope.launch {
        roundRepository.delete(round)
    }

    fun getByID(roundID: Int): LiveData<Round> {
        return roundRepository.getRound(roundID).asLiveData()
    }

    fun getByTournament(tournamentID: Int): LiveData<List<Round>> {
        return roundRepository.getRoundByTournament(tournamentID).asLiveData()
    }
}

class RoundViewModelFactory(private val roundRepository: RoundRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RoundViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return RoundViewModel(roundRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}