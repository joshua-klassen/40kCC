package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a40kcc.data.`object`.Prediction
import com.example.a40kcc.data.repository.PredictionRepository
import kotlinx.coroutines.launch

class PredictionViewModel(private val predictionRepository: PredictionRepository) : ViewModel() {
    val allPredictions: LiveData<List<Prediction>> =
        predictionRepository.allPredictions.asLiveData()

    fun insert(prediction: Prediction) = viewModelScope.launch {
        predictionRepository.insert(prediction)
    }

    fun delete(prediction: Prediction) = viewModelScope.launch {
        predictionRepository.delete(prediction)
    }

    fun getByName(predictionName: String): LiveData<Prediction> {
        return predictionRepository.getPrediction(predictionName).asLiveData()
    }
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