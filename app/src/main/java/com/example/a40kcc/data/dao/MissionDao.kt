package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a40kcc.data.`object`.Mission
import kotlinx.coroutines.flow.Flow

@Dao
interface MissionDao : BaseDao<Mission> {
    @Query("SELECT * FROM mission")
    fun getAll(): Flow<List<Mission>>

    @Query("SELECT * FROM mission WHERE missionID = :missionId")
    fun getById(missionId: Int): Flow<Mission>

    @Query(
        "SELECT * FROM mission WHERE name LIKE :missionName LIMIT 1"
    )
    fun getByName(missionName: String): Flow<Mission>

    @Query(
        "SELECT * FROM mission WHERE nickname LIKE :missionNickname LIMIT 1"
    )
    fun getByNickname(missionNickname: String): Flow<Mission>
}