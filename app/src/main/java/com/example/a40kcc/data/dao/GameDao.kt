package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.a40kcc.data.`object`.Game
import com.example.a40kcc.data.`object`.GameExpanded
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM game")
    fun getAll(): Flow<List<Game>>

    @Query("SELECT * FROM game")
    fun getAllExpanded(): Flow<List<GameExpanded>>

    @Query("SELECT * FROM game WHERE gameID = :gameId")
    fun getById(gameId: Int): Game

    @Query("SELECT * FROM game WHERE gameID = :gameId")
    fun getByIdExpanded(gameId: Int): GameExpanded

    @Insert
    suspend fun insert(vararg game: Game)

    @Update
    fun update(vararg game: Game)

    @Query("DELETE FROM game WHERE gameID = :gameId")
    fun delete(gameId: Int)

    @Query("DELETE FROM game")
    suspend fun deleteAll()
}