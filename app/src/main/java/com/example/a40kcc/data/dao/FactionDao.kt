package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a40kcc.data.`object`.Faction
import kotlinx.coroutines.flow.Flow

@Dao
interface FactionDao : BaseDao<Faction> {
    @Query("SELECT * FROM faction")
    fun getAll(): Flow<List<Faction>>

    @Query("SELECT * FROM faction WHERE factionID = :factionId")
    fun getById(factionId: Int): Flow<Faction>

    @Query(
        "SELECT * FROM faction WHERE name LIKE :factionName LIMIT 1"
    )
    fun getByName(factionName: String): Flow<Faction>

    @Query(
        "SELECT * FROM faction WHERE super_faction LIKE :superFaction"
    )
    fun getBySuperFaction(superFaction: String): Flow<List<Faction>>
}