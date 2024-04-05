package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.data.`object`.RoundExpanded
import kotlinx.coroutines.flow.Flow

@Dao
interface RoundDao : BaseDao<Round> {
    @Query("SELECT * FROM round")
    fun getAll(): Flow<List<Round>>

    @Transaction
    @Query("SELECT * FROM round")
    fun getAllExpanded(): Flow<List<RoundExpanded>>

    @Query("SELECT * FROM round WHERE roundID = :roundId")
    fun getById(roundId: Int): Flow<Round>

    @Transaction
    @Query("SELECT * FROM round WHERE roundID = :roundId")
    fun getByIdExpanded(roundId: Int): Flow<RoundExpanded>

    @Query(
        "SELECT * FROM round WHERE number = :roundNumber"
    )
    fun getRoundByNumber(roundNumber: Int): Flow<List<Round>>

    @Transaction
    @Query(
        "SELECT * FROM round WHERE number = :roundNumber"
    )
    fun getRoundByNumberExpanded(roundNumber: Int): Flow<List<RoundExpanded>>

    @Query(
        "SELECT * FROM round WHERE primary_mission LIKE :primaryMissionName"
    )
    fun getRoundByPrimaryMission(primaryMissionName: String): Flow<List<Round>>

    @Transaction
    @Query(
        "SELECT * FROM round WHERE primary_mission LIKE :primaryMissionName"
    )
    fun getRoundByPrimaryMissionExpanded(primaryMissionName: String): Flow<List<RoundExpanded>>

    @Query(
        "SELECT * FROM round WHERE secondary_mission LIKE :secondaryMissionName"
    )
    fun getRoundBySecondaryMission(secondaryMissionName: String): Flow<List<Round>>

    @Transaction
    @Query(
        "SELECT * FROM round WHERE secondary_mission LIKE :secondaryMissionName"
    )
    fun getRoundBySecondaryMissionExpanded(secondaryMissionName: String): Flow<List<RoundExpanded>>

    @Query(
        "SELECT * FROM round WHERE deployment LIKE :deploymentName"
    )
    fun getRoundByDeployment(deploymentName: String): Flow<List<Round>>

    @Transaction
    @Query(
        "SELECT * FROM round WHERE deployment LIKE :deploymentName"
    )
    fun getRoundByDeploymentExpanded(deploymentName: String): Flow<List<RoundExpanded>>

    @Query(
        "SELECT * FROM round WHERE tournament = :tournamentId"
    )
    fun getRoundByTournament(tournamentId: Int): Flow<List<Round>>

    @Transaction
    @Query(
        "SELECT * FROM round WHERE tournament = :tournamentId"
    )
    fun getRoundByTournamentExpanded(tournamentId: Int): Flow<List<RoundExpanded>>
}