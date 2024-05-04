package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.PredictionDao
import com.example.a40kcc.data.`object`.Prediction
import kotlinx.coroutines.flow.Flow

class PredictionRepository(private val predictionDao: PredictionDao) {
    val allPredictionsFlow: Flow<List<Prediction>> = predictionDao.getAllFlow()

    @WorkerThread
    fun allPredictions(): List<Prediction> {
        return predictionDao.getAll()
    }

    @WorkerThread
    fun getById(predictionId: Int): Prediction {
        return predictionDao.getById(predictionId)
    }

    @WorkerThread
    fun getByName(predictionName: String): Prediction {
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