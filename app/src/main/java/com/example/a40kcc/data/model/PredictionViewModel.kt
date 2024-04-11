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
    val allPredictions: List<Prediction> =
        predictionRepository.allPredictions
    val allPredictionsFlow: LiveData<List<Prediction>> =
        predictionRepository.allPredictionsFlow.asLiveData()

    fun getByName(predictionName: String): Prediction {
        return predictionRepository.getByName(predictionName)
    }

    fun insert(prediction: Prediction) = viewModelScope.launch {
        predictionRepository.insert(prediction)
    }

    fun update(prediction: Prediction) = viewModelScope.launch {
        predictionRepository.update(prediction)
    }

    fun delete(prediction: Prediction) = viewModelScope.launch {
        predictionRepository.delete(prediction)
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