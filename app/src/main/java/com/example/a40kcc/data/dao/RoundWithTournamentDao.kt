package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.data.`object`.RoundWithTournament
import kotlinx.coroutines.flow.Flow

@Dao
interface RoundWithTournamentDao : BaseDao<Round> {
    @Transaction
    @Query("SELECT * FROM round")
    fun getAll(): List<RoundWithTournament>

    @Transaction
    @Query("SELECT * FROM round")
    fun getAllFlow(): Flow<List<RoundWithTournament>>

    @Transaction
    @Query("SELECT * FROM round WHERE roundID = :roundId")
    fun getById(roundId: Int): RoundWithTournament

    @Transaction
    @Query(
        "SELECT * FROM round WHERE number = :roundNumber"
    )
    fun getByRoundNumber(roundNumber: Int): List<RoundWithTournament>

    @Transaction
    @Query(
        "SELECT * FROM round WHERE primary_mission LIKE :primaryMissionName"
    )
    fun getByPrimaryMission(primaryMissionName: String): List<RoundWithTournament>

    @Transaction
    @Query(
        "SELECT * FROM round WHERE secondary_mission LIKE :secondaryMissionName"
    )
    fun getBySecondaryMission(secondaryMissionName: String): List<RoundWithTournament>

    @Transaction
    @Query(
        "SELECT * FROM round WHERE deployment LIKE :deploymentName"
    )
    fun getByDeployment(deploymentName: String): List<RoundWithTournament>

    @Transaction
    @Query(
        "INSERT INTO tournamentRoundCrossRef (tournamentID, roundID) VALUES(:tournamentID, :roundID)"
    )
    suspend fun insert(tournamentID: Int, roundID: Int)
}