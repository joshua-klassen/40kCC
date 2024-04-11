package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a40kcc.data.`object`.Player
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao : BaseDao<Player> {
    @Query("SELECT * FROM player")
    fun getAll(): List<Player>

    @Query("SELECT * FROM player")
    fun getAllFlow(): Flow<List<Player>>

    @Query("SELECT * FROM player WHERE playerID = :playerId")
    fun getById(playerId: Int): Player

    @Query(
        "SELECT * FROM player WHERE name LIKE :playerName LIMIT 1"
    )
    fun getByName(playerName: String): Player

    @Query(
        "SELECT * FROM player WHERE preferred_faction LIKE :factionName"
    )
    fun getByFactionName(factionName: String): List<Player>

    @Query(
        "SELECT * FROM player WHERE nickname LIKE :playerNickname LIMIT 1"
    )
    fun getByNickname(playerNickname: String): Player
}