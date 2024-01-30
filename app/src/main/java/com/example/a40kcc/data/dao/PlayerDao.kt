package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a40kcc.data.`object`.Player
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Query("SELECT * FROM player")
    fun getAll(): Flow<List<Player>>

    @Query("SELECT * FROM player WHERE playerID IN (:playerIds)")
    fun loadAllByIds(playerIds: IntArray): Flow<List<Player>>

    @Query(
        "SELECT * FROM player WHERE first_name LIKE :first AND " +
                "last_name LIKE :last LIMIT 1"
    )
    fun findByName(first: String, last: String): Player

    @Insert
    suspend fun insert(vararg player: Player)

    @Query("DELETE FROM player WHERE playerID = :playerId")
    fun delete(playerId: Int)

    @Query("DELETE FROM player")
    suspend fun deleteAll()
}