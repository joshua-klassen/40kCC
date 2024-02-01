package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a40kcc.data.`object`.Objective

@Dao
interface ObjectiveDao {
    @Query("SELECT * FROM objective")
    fun getAll(): List<Objective>

    @Query("SELECT * FROM objective WHERE objectiveID = :objectiveId")
    fun getById(objectiveId: Int): Objective

    @Query(
        "SELECT * FROM objective WHERE name LIKE :name LIMIT 1"
    )
    fun getByName(name: String): Objective

    @Query(
        "SELECT * FROM objective WHERE nickname LIKE :nickname LIMIT 1"
    )
    fun getByNickname(nickname: String): Objective

    @Insert
    suspend fun insert(vararg objective: Objective)
}