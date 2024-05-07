package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.a40kcc.data.`object`.Tournament
import com.example.a40kcc.data.`object`.TournamentWithRounds
import kotlinx.coroutines.flow.Flow
import java.sql.Date

@Dao
interface TournamentWithRoundsDao : BaseDao<Tournament> {
    @Transaction
    @Query("SELECT * FROM tournament")
    fun getAll(): List<TournamentWithRounds>

    @Transaction
    @Query("SELECT * FROM tournament")
    fun getAllFlow(): Flow<List<TournamentWithRounds>>

    @Transaction
    @Query("SELECT * FROM tournament WHERE tournamentID = :tournamentId")
    fun getById(tournamentId: Int): TournamentWithRounds

    @Transaction
    @Query(
        "SELECT * FROM tournament WHERE name LIKE :tournamentName"
    )
    fun getByName(tournamentName: String): List<TournamentWithRounds>

    @Transaction
    @Query(
        "SELECT * FROM tournament WHERE date LIKE :tournamentDate"
    )
    fun getByDate(tournamentDate: Date): List<TournamentWithRounds>

    @Transaction
    @Query(
        "INSERT INTO tournamentRoundCrossRef (tournamentID, roundID) VALUES(:tournamentID, :roundID)"
    )
    suspend fun insert(tournamentID: Int, roundID: Int)
}