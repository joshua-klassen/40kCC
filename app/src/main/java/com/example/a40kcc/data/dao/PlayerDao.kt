package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a40kcc.data.`object`.Player
import com.example.a40kcc.data.`object`.PlayerExpanded
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Query("SELECT * FROM player")
    fun getAll(): Flow<List<PlayerExpanded>>

    @Query("SELECT * FROM player WHERE playerID = :playerId")
    fun getById(playerId: Int): PlayerExpanded

    @Query(
        "SELECT * FROM player WHERE name LIKE :name"
    )
    fun getPlayersByName(name: String): Flow<List<PlayerExpanded>>

    @Query(
        "SELECT * FROM player WHERE preferred_faction = :factionId"
    )
    fun getPlayersByFaction(factionId: Int): Flow<List<PlayerExpanded>>

    @Query(
        "SELECT * FROM player WHERE nickname LIKE :nickname LIMIT 1"
    )
    fun getPlayerByNickname(nickname: String): PlayerExpanded

    @Insert
    suspend fun insert(vararg player: Player)

    @Query("DELETE FROM player WHERE playerID = :playerId")
    fun delete(playerId: Int)

    @Query("DELETE FROM player")
    suspend fun deleteAll()
}