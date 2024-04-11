package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.a40kcc.data.`object`.LiveRound
import com.example.a40kcc.data.`object`.LiveRoundExpanded
import kotlinx.coroutines.flow.Flow

@Dao
interface LiveRoundExpandedDao : BaseDao<LiveRound> {
    @Transaction
    @Query("SELECT * FROM live_round")
    fun getAll(): List<LiveRoundExpanded>

    @Transaction
    @Query("SELECT * FROM live_round")
    fun getAllFlow(): Flow<List<LiveRoundExpanded>>

    @Transaction
    @Query("SELECT * FROM live_round WHERE liveRoundID = :liveRoundId")
    fun getById(liveRoundId: Int): LiveRoundExpanded

    @Transaction
    @Query("SELECT * FROM live_round WHERE game = :gameId")
    fun getByGameId(gameId: Int): LiveRoundExpanded

    @Transaction
    @Query("SELECT * FROM live_round WHERE expected_result = :predictionId")
    fun getByExpectedResultId(predictionId: Int): LiveRoundExpanded
}