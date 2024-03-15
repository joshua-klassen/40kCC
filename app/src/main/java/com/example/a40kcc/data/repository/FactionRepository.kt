package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.FactionDao
import com.example.a40kcc.data.`object`.Faction
import kotlinx.coroutines.flow.Flow

class FactionRepository(private val factionDao: FactionDao) {
    val allFactions: Flow<List<Faction>> = factionDao.getAll()

    @WorkerThread
    fun getFaction(factionId: Int): Flow<Faction> {
        return factionDao.getById(factionId)
    }

    @WorkerThread
    fun getFaction(factionName: String): Flow<Faction> {
        return factionDao.getByName(factionName)
    }

    @WorkerThread
    fun getSuperFaction(superFaction: String): Flow<List<Faction>> {
        return factionDao.getBySuperFaction(superFaction)
    }

    @WorkerThread
    suspend fun insert(faction: Faction) {
        factionDao.insert(faction)
    }
}