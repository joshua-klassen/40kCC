package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a40kcc.data.`object`.Player
import com.example.a40kcc.data.`object`.PlayerExpanded
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao : BaseDao<Player> {
    @Query("SELECT * FROM player")
    fun getAll(): Flow<List<Player>>

    @Query("SELECT * FROM player")
    fun getAllExpanded(): Flow<List<PlayerExpanded>>

    @Query("SELECT * FROM player WHERE playerID = :playerId")
    fun getById(playerId: Int): Flow<Player>

    @Query("SELECT * FROM player WHERE playerID = :playerId")
    fun getByIdExpanded(playerId: Int): Flow<PlayerExpanded>

    @Query(
        "SELECT * FROM player WHERE name LIKE :playerName"
    )
    fun getPlayersByName(playerName: String): Flow<List<Player>>

    @Query(
        "SELECT * FROM player WHERE name LIKE :playerName"
    )
    fun getPlayersByNameExpanded(playerName: String): Flow<List<PlayerExpanded>>

    @Query(
        "SELECT * FROM player WHERE preferred_faction LIKE :factionName"
    )
    fun getPlayersByFaction(factionName: String): Flow<List<Player>>

    @Query(
        "SELECT * FROM player WHERE preferred_faction LIKE :factionName"
    )
    fun getPlayersByFactionExpanded(factionName: String): Flow<List<PlayerExpanded>>

    @Query(
        "SELECT * FROM player WHERE nickname LIKE :playerNickname LIMIT 1"
    )
    fun getPlayerByNickname(playerNickname: String): Flow<Player>

    @Query(
        "SELECT * FROM player WHERE nickname LIKE :playerNickname LIMIT 1"
    )
    fun getPlayerByNicknameExpanded(playerNickname: String): Flow<PlayerExpanded>
}