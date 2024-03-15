package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.PredictionDao
import com.example.a40kcc.data.`object`.Prediction
import kotlinx.coroutines.flow.Flow

class PredictionRepository(private val predictionDao: PredictionDao) {
    val allPredictions: Flow<List<Prediction>> = predictionDao.getAll()

    @WorkerThread
    fun getPrediction(predictionId: Int): Flow<Prediction> {
        return predictionDao.getById(predictionId)
    }

    @WorkerThread
    fun getPrediction(predictionName: String): Flow<Prediction> {
        return predictionDao.getByName(predictionName)
    }

    @WorkerThread
    suspend fun insert(prediction: Prediction) {
        predictionDao.insert(prediction)
    }

    @WorkerThread
    suspend fun update(prediction: Prediction) {
        predictionDao.update(prediction)
    }

    @WorkerThread
    suspend fun delete(prediction: Prediction) {
        predictionDao.delete(prediction)
    }
}