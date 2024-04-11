package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a40kcc.data.`object`.Outcome
import kotlinx.coroutines.flow.Flow

@Dao
interface OutcomeDao : BaseDao<Outcome> {
    @Query("SELECT * FROM outcome")
    fun getAll(): List<Outcome>

    @Query("SELECT * FROM outcome")
    fun getAllFlow(): Flow<List<Outcome>>

    @Query("SELECT * FROM outcome WHERE outcomeID = :outcomeId")
    fun getById(outcomeId: Int): Outcome

    @Query(
        "SELECT * FROM outcome WHERE (player_01 = :playerId OR player_02 = :playerId)"
    )
    fun getByPlayerId(playerId: Int): List<Outcome>
}