package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.a40kcc.data.`object`.Game
import com.example.a40kcc.data.`object`.GameExpanded
import kotlinx.coroutines.flow.Flow

@Dao
interface GameExpandedDao : BaseDao<Game> {
    @Transaction
    @Query("SELECT * FROM game")
    fun getAll(): List<GameExpanded>

    @Transaction
    @Query("SELECT * FROM game")
    fun getAllFlow(): Flow<List<GameExpanded>>

    @Transaction
    @Query("SELECT * FROM game WHERE gameID = :gameId")
    fun getById(gameId: Int): GameExpanded
}