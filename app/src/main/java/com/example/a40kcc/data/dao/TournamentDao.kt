package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a40kcc.data.`object`.Tournament
import kotlinx.coroutines.flow.Flow
import java.sql.Date

@Dao
interface TournamentDao : BaseDao<Tournament> {
    @Query("SELECT * FROM tournament")
    fun getAll(): Flow<List<Tournament>>

    @Query("SELECT * FROM tournament WHERE tournamentID = :tournamentId")
    fun getById(tournamentId: Int): Flow<Tournament>

    @Query(
        "SELECT * FROM tournament WHERE name LIKE :tournamentName"
    )
    fun getTournamentsByName(tournamentName: String): Flow<List<Tournament>>

    @Query(
        "SELECT * FROM tournament WHERE date LIKE :tournamentDate"
    )
    fun getTournamentsByDate(tournamentDate: Date): Flow<List<Tournament>>
}