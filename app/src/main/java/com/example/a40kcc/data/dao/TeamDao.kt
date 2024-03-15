package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a40kcc.data.`object`.Team
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao : BaseDao<Team> {
    @Query("SELECT * FROM team")
    fun getAll(): Flow<List<Team>>

    @Query("SELECT * FROM team WHERE teamID = :teamId")
    fun getById(teamId: Int): Flow<Team>
}