package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.a40kcc.data.`object`.Team
import com.example.a40kcc.data.`object`.TeamWithPlayers
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamWithPlayersDao : BaseDao<Team> {
    @Transaction
    @Query("SELECT * FROM team")
    fun getAll(): List<TeamWithPlayers>

    @Transaction
    @Query("SELECT * FROM team")
    fun getAllFlow(): Flow<List<TeamWithPlayers>>

    @Transaction
    @Query("SELECT * FROM team WHERE teamID = :teamId")
    fun getById(teamId: Int): TeamWithPlayers

    @Transaction
    @Query("SELECT * FROM team WHERE name LIKE :teamName LIMIT 1")
    fun getByName(teamName: String): TeamWithPlayers

    @Transaction
    @Query(
        "INSERT INTO playerTeamCrossRef (playerID, teamID) VALUES(:playerID, :teamID)"
    )
    fun insert(playerID: Int, teamID: Int)
}