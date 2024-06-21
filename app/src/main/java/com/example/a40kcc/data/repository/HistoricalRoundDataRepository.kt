package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.HistoricalRoundDataDao
import com.example.a40kcc.data.`object`.HistoricalRoundData

class HistoricalRoundDataRepository(private val historicalRoundDataDao: HistoricalRoundDataDao) {
    @WorkerThread
    fun getById(historicalRoundDataId: Int): HistoricalRoundData {
        return historicalRoundDataDao.getById(historicalRoundDataId)
    }

    @WorkerThread
    fun getByPlayerName(playerName: String): List<HistoricalRoundData> {
        return historicalRoundDataDao.getByPlayerName(playerName)
    }

    @WorkerThread
    fun getByTournamentName(tournamentName: String): List<HistoricalRoundData> {
        return historicalRoundDataDao.getByTournamentName(tournamentName)
    }

    @WorkerThread
    fun getByTeamName(teamName: String): List<HistoricalRoundData> {
        return historicalRoundDataDao.getByTeamName(teamName)
    }

    @WorkerThread
    suspend fun insert(historicalRoundData: HistoricalRoundData) {
        historicalRoundDataDao.insert(historicalRoundData)
    }
}