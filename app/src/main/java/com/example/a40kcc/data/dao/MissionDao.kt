package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a40kcc.data.`object`.Mission

@Dao
interface MissionDao {
    @Query("SELECT * FROM mission")
    fun getAll(): List<Mission>

    @Query("SELECT * FROM mission WHERE missionID = :missionId")
    fun getById(missionId: Int): Mission

    @Query(
        "SELECT * FROM mission WHERE name LIKE :name LIMIT 1"
    )
    fun getByName(name: String): Mission

    @Query(
        "SELECT * FROM mission WHERE nickname LIKE :nickname LIMIT 1"
    )
    fun getByNickname(nickname: String): Mission

    @Insert
    suspend fun insert(vararg mission: Mission)
}