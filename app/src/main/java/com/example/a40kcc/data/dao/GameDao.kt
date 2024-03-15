package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a40kcc.data.`object`.Game
import com.example.a40kcc.data.`object`.GameExpanded
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao : BaseDao<Game> {
    @Query("SELECT * FROM game")
    fun getAll(): Flow<List<Game>>

    @Query("SELECT * FROM game")
    fun getAllExpanded(): Flow<List<GameExpanded>>

    @Query("SELECT * FROM game WHERE gameID = :gameId")
    fun getById(gameId: Int): Flow<Game>

    @Query("SELECT * FROM game WHERE gameID = :gameId")
    fun getByIdExpanded(gameId: Int): Flow<GameExpanded>
}