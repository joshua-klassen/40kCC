package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a40kcc.data.`object`.Faction
import com.example.a40kcc.data.repository.FactionRepository
import kotlinx.coroutines.launch

class FactionViewModel(private val factionRepository: FactionRepository) : ViewModel() {
    val allFactions: LiveData<List<Faction>> = factionRepository.allFactions.asLiveData()

    fun insert(faction: Faction) {
        viewModelScope.launch {
            factionRepository.insert(faction)
        }
    }
}

class FactionViewModelFactory(private val factionRepository: FactionRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FactionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FactionViewModel(factionRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}