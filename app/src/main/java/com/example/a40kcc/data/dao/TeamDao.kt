package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.a40kcc.data.`object`.Team

@Dao
interface TeamDao {
    @Query("SELECT * FROM team")
    fun getAll(): List<Team>

    @Query("SELECT * FROM team WHERE teamID = :teamId")
    fun getById(teamId: Int): Team

    @Insert
    suspend fun insert(vararg team: Team)

    @Update
    fun update(vararg team: Team)

    @Query("DELETE FROM team WHERE teamID = :teamId")
    fun delete(teamId: Int)

    @Query("DELETE FROM team")
    suspend fun deleteAll()
}