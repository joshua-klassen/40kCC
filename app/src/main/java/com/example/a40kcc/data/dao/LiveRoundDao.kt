package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a40kcc.data.`object`.LiveRound
import kotlinx.coroutines.flow.Flow

@Dao
interface LiveRoundDao : BaseDao<LiveRound> {
    @Query("SELECT * FROM live_round")
    fun getAll(): List<LiveRound>

    @Query("SELECT * FROM live_round")
    fun getAllFlow(): Flow<List<LiveRound>>

    @Query("SELECT * FROM live_round WHERE liveRoundID = :liveRoundId")
    fun getById(liveRoundId: Int): LiveRound

    @Query("SELECT * FROM live_round WHERE game = :gameId")
    fun getByGameId(gameId: Int): LiveRound?

    @Query("SELECT * FROM live_round WHERE expected_result = :predictionId")
    fun getByExpectedResultId(predictionId: Int): LiveRound?
}