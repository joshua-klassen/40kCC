package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.data.repository.RoundRepository

class RoundViewModel(private val roundRepository: RoundRepository) : ViewModel() {
    val allRounds: LiveData<List<Round>> = roundRepository.allRounds.asLiveData()
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