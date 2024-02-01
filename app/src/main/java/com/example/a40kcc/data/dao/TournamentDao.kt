package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a40kcc.data.`object`.Tournament
import kotlinx.coroutines.flow.Flow
import java.sql.Date

@Dao
interface TournamentDao {
    @Query("SELECT * FROM tournament")
    fun getAll(): List<Tournament>

    @Query("SELECT * FROM tournament WHERE tournamentID = :tournamentId")
    fun getById(tournamentId: Int): Tournament

    @Query(
        "SELECT * FROM tournament WHERE name LIKE :name"
    )
    fun getTournamentsByName(name: String): Flow<List<Tournament>>

    @Query(
        "SELECT * FROM tournament WHERE date LIKE :date"
    )
    fun getTournamentsByDate(date: Date): Flow<List<Tournament>>

    @Insert
    suspend fun insert(vararg tournament: Tournament)

    @Query("DELETE FROM tournament WHERE tournamentID = :tournamentId")
    fun delete(tournamentId: Int)

    @Query("DELETE FROM tournament")
    suspend fun deleteAll()
}