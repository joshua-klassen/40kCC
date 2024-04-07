package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a40kcc.data.`object`.Outcome
import com.example.a40kcc.data.`object`.OutcomeExpanded
import com.example.a40kcc.data.repository.OutcomeRepository
import kotlinx.coroutines.launch

class OutcomeViewModel(private val outcomeRepository: OutcomeRepository) : ViewModel() {
    val allOutcomes: LiveData<List<Outcome>> = outcomeRepository.allOutcomes.asLiveData()
    val allOutcomesExpanded: LiveData<List<OutcomeExpanded>> =
        outcomeRepository.allOutcomesExpanded.asLiveData()

    fun insert(outcome: Outcome) = viewModelScope.launch {
        outcomeRepository.insert(outcome)
    }

    fun update(outcome: Outcome) = viewModelScope.launch {
        outcomeRepository.update(outcome)
    }
}

class OutcomeViewModelFactory(private val outcomeRepository: OutcomeRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OutcomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OutcomeViewModel(outcomeRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}