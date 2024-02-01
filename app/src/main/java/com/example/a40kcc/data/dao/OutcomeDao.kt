package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a40kcc.data.`object`.Outcome

@Dao
interface OutcomeDao {
    @Query("SELECT * FROM outcome")
    fun getAll(): List<Outcome>

    @Query("SELECT * FROM outcome WHERE outcomeID = :outcomeId")
    fun getById(outcomeId: Int): Outcome

    @Query(
        "SELECT * FROM outcome WHERE (player_01 = :playerId OR player_02 = :playerId)"
    )
    fun getOutcomesByPlayer(playerId: Int): List<Outcome>

    @Insert
    suspend fun insert(vararg outcome: Outcome)
}