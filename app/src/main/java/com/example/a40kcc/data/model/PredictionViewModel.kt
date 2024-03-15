package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.a40kcc.data.`object`.Prediction
import com.example.a40kcc.data.repository.PredictionRepository

class PredictionViewModel(predictionRepository: PredictionRepository) : ViewModel() {
    val allPredictions: LiveData<List<Prediction>> =
        predictionRepository.allPredictions.asLiveData()
}

class PredictionViewModelFactory(private val predictionRepository: PredictionRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PredictionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PredictionViewModel(predictionRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}