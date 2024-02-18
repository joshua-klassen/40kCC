package com.example.a40kcc.data.repository

import androidx.annotation.WorkerThread
import com.example.a40kcc.data.dao.FactionDao
import com.example.a40kcc.data.`object`.Faction

class FactionRepository(private val factionDao: FactionDao) {
    val allFactions: List<Faction> = factionDao.getAll()

    @WorkerThread
    fun getFaction(factionId: Int): Faction {
        return factionDao.getById(factionId)
    }

    @WorkerThread
    fun getFaction(factionName: String): Faction {
        return factionDao.getByName(factionName)
    }

    @WorkerThread
    fun getSuperFaction(superFaction: String): List<Faction> {
        return factionDao.getBySuperFaction(superFaction)
    }
}