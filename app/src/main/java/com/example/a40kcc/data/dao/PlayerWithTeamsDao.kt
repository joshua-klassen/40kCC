package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.a40kcc.data.`object`.Player
import com.example.a40kcc.data.`object`.PlayerWithTeams
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerWithTeamsDao : BaseDao<Player> {
    @Transaction
    @Query("SELECT * FROM player")
    fun getAll(): List<PlayerWithTeams>

    @Transaction
    @Query("SELECT * FROM player")
    fun getAllFlow(): Flow<List<PlayerWithTeams>>

    @Transaction
    @Query("SELECT * FROM player WHERE playerID = :playerId")
    fun getById(playerId: Int): PlayerWithTeams

    @Transaction
    @Query(
        "SELECT * FROM player WHERE name LIKE :playerName LIMIT 1"
    )
    fun getByName(playerName: String): PlayerWithTeams

    @Transaction
    @Query(
        "SELECT * FROM player WHERE preferred_faction LIKE :factionName"
    )
    fun getByFactionName(factionName: String): List<PlayerWithTeams>

    @Transaction
    @Query(
        "SELECT * FROM player WHERE nickname LIKE :playerNickname LIMIT 1"
    )
    fun getByNickname(playerNickname: String): PlayerWithTeams

    @Transaction
    @Query(
        "INSERT INTO playerTeamCrossRef (playerID, teamID) VALUES(:playerID, :teamID)"
    )
    fun insert(playerID: Int, teamID: Int)
}