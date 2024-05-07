package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.TournamentWithRoundsDao
import com.example.a40kcc.data.`object`.TournamentWithRounds
import kotlinx.coroutines.flow.Flow
import java.sql.Date

class TournamentWithRoundsRepository(private val tournamentDao: TournamentWithRoundsDao) {
    val allTournamentsFlow: Flow<List<TournamentWithRounds>> = tournamentDao.getAllFlow()

    @WorkerThread
    fun allTournaments(): List<TournamentWithRounds> {
        return tournamentDao.getAll()
    }

    @WorkerThread
    fun getById(tournamentId: Int): TournamentWithRounds {
        return tournamentDao.getById(tournamentId)
    }

    @WorkerThread
    fun getByName(tournamentName: String): List<TournamentWithRounds> {
        return tournamentDao.getByName(tournamentName)
    }

    @WorkerThread
    fun getByDate(tournamentDate: Date): List<TournamentWithRounds> {
        return tournamentDao.getByDate(tournamentDate)
    }

    @WorkerThread
    suspend fun insert(tournamentID: Int, roundID: Int) {
        tournamentDao.insert(tournamentID, roundID)
    }
}