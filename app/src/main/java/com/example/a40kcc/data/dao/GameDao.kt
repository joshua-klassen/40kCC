package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a40kcc.data.`object`.Game
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao : BaseDao<Game> {
    @Query("SELECT * FROM game")
    fun getAll(): List<Game>

    @Query("SELECT * FROM game")
    fun getAllFlow(): Flow<List<Game>>

    @Query("SELECT * FROM game WHERE gameID = :gameId")
    fun getById(gameId: Int): Game
}