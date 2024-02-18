package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.a40kcc.data.`object`.Faction

@Dao
interface FactionDao {
    @Query("SELECT * FROM faction")
    fun getAll(): List<Faction>

    @Query("SELECT * FROM faction WHERE factionID = :factionId")
    fun getById(factionId: Int): Faction

    @Query(
        "SELECT * FROM faction WHERE name LIKE :factionName LIMIT 1"
    )
    fun getByName(factionName: String): Faction

    @Query(
        "SELECT * FROM faction WHERE super_faction LIKE :superFaction"
    )
    fun getBySuperFaction(superFaction: String): List<Faction>

    @Insert
    suspend fun insert(vararg faction: Faction)

    @Update
    fun update(vararg faction: Faction)

    @Query("DELETE FROM faction WHERE factionID = :factionId")
    fun delete(factionId: Int)

    @Query("DELETE FROM faction")
    suspend fun deleteAll()
}