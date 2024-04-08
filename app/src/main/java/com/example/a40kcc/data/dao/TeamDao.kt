package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.a40kcc.data.`object`.Team
import com.example.a40kcc.data.`object`.TeamExpanded
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao : BaseDao<Team> {
    @Query("SELECT * FROM team")
    fun getAll(): Flow<List<Team>>

    @Transaction
    @Query("SELECT * FROM team")
    fun getAllExpanded(): Flow<List<TeamExpanded>>

    @Query("SELECT * FROM team WHERE teamID = :teamId")
    fun getTeam(teamId: Int): Flow<Team>

    @Transaction
    @Query("SELECT * FROM team WHERE teamID = :teamId")
    fun getTeamExpanded(teamId: Int): Flow<TeamExpanded>

    @Query("SELECT * FROM team WHERE name LIKE :teamName LIMIT 1")
    fun getTeam(teamName: String): Flow<Team>

    @Transaction
    @Query("SELECT * FROM team WHERE name LIKE :teamName LIMIT 1")
    fun getTeamExpanded(teamName: String): Flow<TeamExpanded>
}