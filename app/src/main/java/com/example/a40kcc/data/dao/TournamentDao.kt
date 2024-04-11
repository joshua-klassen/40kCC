package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a40kcc.data.`object`.Tournament
import kotlinx.coroutines.flow.Flow
import java.sql.Date

@Dao
interface TournamentDao : BaseDao<Tournament> {
    @Query("SELECT * FROM tournament")
    fun getAll(): List<Tournament>

    @Query("SELECT * FROM tournament")
    fun getAllFlow(): Flow<List<Tournament>>

    @Query("SELECT * FROM tournament WHERE tournamentID = :tournamentId")
    fun getById(tournamentId: Int): Tournament

    @Query(
        "SELECT * FROM tournament WHERE name LIKE :tournamentName"
    )
    fun getByName(tournamentName: String): List<Tournament>

    @Query(
        "SELECT * FROM tournament WHERE date LIKE :tournamentDate"
    )
    fun getByDate(tournamentDate: Date): List<Tournament>
}