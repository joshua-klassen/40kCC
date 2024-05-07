package com.example.a40kcc.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a40kcc.data.`object`.Prediction
import com.example.a40kcc.data.repository.PredictionRepository

class PredictionViewModel(private val predictionRepository: PredictionRepository) : ViewModel() {
    fun allPredictions(): List<Prediction> {
        return predictionRepository.allPredictions()
    }

    fun getByName(predictionName: String): Prediction {
        return predictionRepository.getByName(predictionName)
    }

    suspend fun insert(prediction: Prediction) {
        predictionRepository.insert(prediction)
    }

    suspend fun update(prediction: Prediction) {
        predictionRepository.update(prediction)
    }

    suspend fun delete(prediction: Prediction) {
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