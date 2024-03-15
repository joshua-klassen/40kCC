package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.Objective
import com.example.a40kcc.data.repository.ObjectiveRepository

class ObjectiveViewModel(objectiveRepository: ObjectiveRepository) : ViewModel() {
    val allObjectives: LiveData<List<Objective>> = objectiveRepository.allObjectives.asLiveData()
}

class ObjectiveViewModelFactory(private val objectiveRepository: ObjectiveRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ObjectiveViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ObjectiveViewModel(objectiveRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}