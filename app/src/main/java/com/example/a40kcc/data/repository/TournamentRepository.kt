package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.TournamentDao
import com.example.a40kcc.data.`object`.Tournament
import kotlinx.coroutines.flow.Flow
import java.sql.Date

class TournamentRepository(private val tournamentDao: TournamentDao) {
    val allTournamentsFlow: Flow<List<Tournament>> = tournamentDao.getAllFlow()

    @WorkerThread
    fun allTournaments(): List<Tournament> {
        return tournamentDao.getAll()
    }

    @WorkerThread
    fun getById(tournamentId: Int): Tournament {
        return tournamentDao.getById(tournamentId)
    }

    @WorkerThread
    fun getByName(tournamentName: String): List<Tournament> {
        return tournamentDao.getByName(tournamentName)
    }

    @WorkerThread
    fun getByDate(tournamentDate: Date): List<Tournament> {
        return tournamentDao.getByDate(tournamentDate)
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