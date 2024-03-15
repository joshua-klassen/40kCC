package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a40kcc.data.`object`.Prediction
import kotlinx.coroutines.flow.Flow

@Dao
interface PredictionDao : BaseDao<Prediction> {
    @Query("SELECT * FROM prediction")
    fun getAll(): Flow<List<Prediction>>

    @Query("SELECT * FROM prediction WHERE predictionID = :predictionId")
    fun getById(predictionId: Int): Flow<Prediction>

    @Query(
        "SELECT * FROM prediction WHERE name LIKE :predictionName"
    )
    fun getByName(predictionName: String): Flow<Prediction>
}