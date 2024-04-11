package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.a40kcc.data.`object`.Outcome
import com.example.a40kcc.data.`object`.OutcomeWithPlayers
import kotlinx.coroutines.flow.Flow

@Dao
interface OutcomeWithPlayersDao : BaseDao<Outcome> {
    @Transaction
    @Query("SELECT * FROM outcome")
    fun getAll(): List<OutcomeWithPlayers>

    @Transaction
    @Query("SELECT * FROM outcome")
    fun getAllFlow(): Flow<List<OutcomeWithPlayers>>

    @Transaction
    @Query("SELECT * FROM outcome WHERE outcomeID = :outcomeId")
    fun getById(outcomeId: Int): OutcomeWithPlayers

    @Transaction
    @Query(
        "SELECT * FROM outcome WHERE (player_01 = :playerId OR player_02 = :playerId)"
    )
    fun getByPlayerId(playerId: Int): List<OutcomeWithPlayers>
}