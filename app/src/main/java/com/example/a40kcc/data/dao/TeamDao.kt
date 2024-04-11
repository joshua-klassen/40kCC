package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a40kcc.data.`object`.Team
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao : BaseDao<Team> {
    @Query("SELECT * FROM team")
    fun getAll(): List<Team>

    @Query("SELECT * FROM team")
    fun getAllFlow(): Flow<List<Team>>

    @Query("SELECT * FROM team WHERE teamID = :teamId")
    fun getById(teamId: Int): Team

    @Query("SELECT * FROM team WHERE name LIKE :teamName LIMIT 1")
    fun getByName(teamName: String): Team
}