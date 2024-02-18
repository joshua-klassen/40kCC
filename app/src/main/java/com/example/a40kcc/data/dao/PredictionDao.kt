package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.a40kcc.data.`object`.Prediction
import kotlinx.coroutines.flow.Flow

@Dao
interface PredictionDao {
    @Query("SELECT * FROM prediction")
    fun getAll(): Flow<List<Prediction>>

    @Query("SELECT * FROM prediction WHERE predictionID = :predictionId")
    fun getById(predictionId: Int): Flow<Prediction>

    @Query(
        "SELECT * FROM prediction WHERE name LIKE :name"
    )
    fun getByName(name: String): Flow<Prediction>

    @Insert
    suspend fun insert(vararg prediction: Prediction)

    @Update
    fun update(vararg prediction: Prediction)

    @Query("DELETE FROM prediction WHERE predictionID = :predictionId")
    fun delete(predictionId: Int)

    @Query("DELETE FROM prediction")
    suspend fun deleteAll()
}