package com.example.a40kcc.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.a40kcc.data.`object`.Prediction
import com.example.a40kcc.data.repository.PredictionRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class PredictionViewModel(private val predictionRepository: PredictionRepository) : ViewModel() {
    val allPredictionsFlow: LiveData<List<Prediction>> =
        predictionRepository.allPredictionsFlow.asLiveData()

    fun allPredictions(): List<Prediction> {
        return predictionRepository.allPredictions()
    }

    fun getByName(predictionName: String): Prediction {
        return predictionRepository.getByName(predictionName)
    }

    fun insert(prediction: Prediction, exceptionHandler: CoroutineExceptionHandler) {
        viewModelScope.launch(exceptionHandler) {
            predictionRepository.insert(prediction)
        }
    }

    fun update(prediction: Prediction, exceptionHandler: CoroutineExceptionHandler) {
        viewModelScope.launch(exceptionHandler) {
            predictionRepository.update(prediction)
        }
    }

    fun delete(prediction: Prediction, exceptionHandler: CoroutineExceptionHandler) {
        viewModelScope.launch(exceptionHandler) {
            predictionRepository.delete(prediction)
        }
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