package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.a40kcc.data.`object`.Outcome
import com.example.a40kcc.data.`object`.OutcomeExpanded

@Dao
interface OutcomeDao {
    @Query("SELECT * FROM outcome")
    fun getAll(): List<Outcome>

    @Query("SELECT * FROM outcome")
    fun getAlExpandedl(): List<OutcomeExpanded>

    @Query("SELECT * FROM outcome WHERE outcomeID = :outcomeId")
    fun getById(outcomeId: Int): Outcome

    @Query("SELECT * FROM outcome WHERE outcomeID = :outcomeId")
    fun getByIdExpanded(outcomeId: Int): OutcomeExpanded

    @Query(
        "SELECT * FROM outcome WHERE (player_01 = :playerId OR player_02 = :playerId)"
    )
    fun getOutcomesByPlayer(playerId: Int): List<Outcome>

    @Query(
        "SELECT * FROM outcome WHERE (player_01 = :playerId OR player_02 = :playerId)"
    )
    fun getOutcomesByPlayerExpanded(playerId: Int): List<OutcomeExpanded>

    @Insert
    suspend fun insert(vararg outcome: Outcome)

    @Update
    fun update(vararg outcome: Outcome)
}