package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.a40kcc.data.`object`.Player
import com.example.a40kcc.data.`object`.PlayerExpanded
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Query("SELECT * FROM player")
    fun getAll(): Flow<List<Player>>

    @Query("SELECT * FROM player")
    fun getAllExpanded(): Flow<List<PlayerExpanded>>

    @Query("SELECT * FROM player WHERE playerID = :playerId")
    fun getById(playerId: Int): Player

    @Query("SELECT * FROM player WHERE playerID = :playerId")
    fun getByIdExpanded(playerId: Int): PlayerExpanded

    @Query(
        "SELECT * FROM player WHERE name LIKE :playerName"
    )
    fun getPlayersByName(playerName: String): Flow<List<Player>>

    @Query(
        "SELECT * FROM player WHERE name LIKE :playerName"
    )
    fun getPlayersByNameExpanded(playerName: String): Flow<List<PlayerExpanded>>

    @Query(
        "SELECT * FROM player WHERE preferred_faction = :factionId"
    )
    fun getPlayersByFaction(factionId: Int): Flow<List<Player>>

    @Query(
        "SELECT * FROM player WHERE preferred_faction = :factionId"
    )
    fun getPlayersByFactionExpanded(factionId: Int): Flow<List<PlayerExpanded>>

    @Query(
        "SELECT * FROM player WHERE nickname LIKE :playerNickname LIMIT 1"
    )
    fun getPlayerByNickname(playerNickname: String): Player

    @Query(
        "SELECT * FROM player WHERE nickname LIKE :playerNickname LIMIT 1"
    )
    fun getPlayerByNicknameExpanded(playerNickname: String): PlayerExpanded

    @Insert
    suspend fun insert(vararg player: Player)

    @Update
    fun update(vararg player: Player)

    @Query("DELETE FROM player WHERE playerID = :playerId")
    fun delete(playerId: Int)

    @Query("DELETE FROM player")
    suspend fun deleteAll()
}