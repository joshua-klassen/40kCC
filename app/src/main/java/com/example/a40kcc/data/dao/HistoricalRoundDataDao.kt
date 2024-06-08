package com.example.a40kcc.data.dao

import android.database.sqlite.SQLiteException
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.a40kcc.data.`object`.HistoricalRoundData

@Dao
interface HistoricalRoundDataDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    suspend fun insert(obj: HistoricalRoundData): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    @Throws(SQLiteException::class)
    suspend fun insert(vararg obj: HistoricalRoundData): Array<Long>

    @Query("SELECT * FROM historical_round_data WHERE historicalRoundDataID = :historicalRoundDataId")
    fun getById(historicalRoundDataId: Int): HistoricalRoundData

    @Query("SELECT * FROM historical_round_data WHERE (player_01 LIKE :playerName OR player_02 LIKE :playerName)")
    fun getByPlayerName(playerName: String): List<HistoricalRoundData>
}