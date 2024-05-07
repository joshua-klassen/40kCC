package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a40kcc.data.`object`.Round
import kotlinx.coroutines.flow.Flow

@Dao
interface RoundDao : BaseDao<Round> {
    @Query("SELECT * FROM round")
    fun getAll(): List<Round>

    @Query("SELECT * FROM round")
    fun getAllFlow(): Flow<List<Round>>

    @Query("SELECT * FROM round WHERE roundID = :roundId")
    fun getById(roundId: Int): Round

    @Query(
        "SELECT * FROM round WHERE number = :roundNumber"
    )
    fun getByRoundNumber(roundNumber: Int): List<Round>

    @Query(
        "SELECT * FROM round WHERE primary_mission LIKE :primaryMissionName"
    )
    fun getByPrimaryMission(primaryMissionName: String): List<Round>

    @Query(
        "SELECT * FROM round WHERE secondary_mission LIKE :secondaryMissionName"
    )
    fun getBySecondaryMission(secondaryMissionName: String): List<Round>

    @Query(
        "SELECT * FROM round WHERE deployment LIKE :deploymentName"
    )
    fun getByDeployment(deploymentName: String): List<Round>
}