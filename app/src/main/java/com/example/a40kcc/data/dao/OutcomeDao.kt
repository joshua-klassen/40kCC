package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.a40kcc.data.`object`.Outcome
import com.example.a40kcc.data.`object`.OutcomeExpanded
import kotlinx.coroutines.flow.Flow

@Dao
interface OutcomeDao : BaseDao<Outcome> {
    @Query("SELECT * FROM outcome")
    fun getAll(): Flow<List<Outcome>>

    @Transaction
    @Query("SELECT * FROM outcome")
    fun getAllExpanded(): Flow<List<OutcomeExpanded>>

    @Query("SELECT * FROM outcome WHERE outcomeID = :outcomeId")
    fun getById(outcomeId: Int): Flow<Outcome>

    @Transaction
    @Query("SELECT * FROM outcome WHERE outcomeID = :outcomeId")
    fun getByIdExpanded(outcomeId: Int): Flow<OutcomeExpanded>

    @Query(
        "SELECT * FROM outcome WHERE (player_01 = :playerId OR player_02 = :playerId)"
    )
    fun getOutcomesByPlayer(playerId: Int): Flow<List<Outcome>>

    @Transaction
    @Query(
        "SELECT * FROM outcome WHERE (player_01 = :playerId OR player_02 = :playerId)"
    )
    fun getOutcomesByPlayerExpanded(playerId: Int): Flow<List<OutcomeExpanded>>
}