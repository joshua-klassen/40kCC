package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.TournamentDao
import com.example.a40kcc.data.`object`.Tournament
import kotlinx.coroutines.flow.Flow
import java.sql.Date

class TournamentRepository(private val tournamentDao: TournamentDao) {
    val allTournaments: Flow<List<Tournament>> = tournamentDao.getAll()

    @WorkerThread
    fun getTournament(tournamentId: Int): Flow<Tournament> {
        return tournamentDao.getById(tournamentId)
    }

    @WorkerThread
    fun getTournaments(tournamentName: String): Flow<List<Tournament>> {
        return tournamentDao.getTournamentsByName(tournamentName)
    }

    @WorkerThread
    fun getTournaments(tournamentDate: Date): Flow<List<Tournament>> {
        return tournamentDao.getTournamentsByDate(tournamentDate)
    }

    @WorkerThread
    suspend fun insert(tournament: Tournament) {
        tournamentDao.insert(tournament)
    }

    @WorkerThread
    suspend fun update(tournament: Tournament) {
        tournamentDao.update(tournament)
    }

    @WorkerThread
    suspend fun delete(tournament: Tournament) {
        tournamentDao.delete(tournament)
    }
}