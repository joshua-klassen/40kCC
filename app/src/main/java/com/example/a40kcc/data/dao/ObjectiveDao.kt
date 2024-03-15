package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a40kcc.data.`object`.Objective
import kotlinx.coroutines.flow.Flow

@Dao
interface ObjectiveDao : BaseDao<Objective> {
    @Query("SELECT * FROM objective")
    fun getAll(): Flow<List<Objective>>

    @Query("SELECT * FROM objective WHERE objectiveID = :objectiveId")
    fun getById(objectiveId: Int): Flow<Objective>

    @Query(
        "SELECT * FROM objective WHERE name LIKE :objectiveName LIMIT 1"
    )
    fun getByName(objectiveName: String): Flow<Objective>

    @Query(
        "SELECT * FROM objective WHERE nickname LIKE :objectiveNickname LIMIT 1"
    )
    fun getByNickname(objectiveNickname: String): Flow<Objective>
}