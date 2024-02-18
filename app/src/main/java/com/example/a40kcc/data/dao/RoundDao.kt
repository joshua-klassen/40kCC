package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.data.`object`.RoundExpanded

@Dao
interface RoundDao {
    @Query("SELECT * FROM round")
    fun getAll(): List<Round>

    @Query("SELECT * FROM round")
    fun getAllExpanded(): List<RoundExpanded>

    @Query("SELECT * FROM round WHERE roundID = :roundId")
    fun getById(roundId: Int): Round

    @Query("SELECT * FROM round WHERE roundID = :roundId")
    fun getByIdExpanded(roundId: Int): RoundExpanded

    @Query(
        "SELECT * FROM round WHERE number = :round"
    )
    fun getRoundByNumber(round: Int): List<Round>

    @Query(
        "SELECT * FROM round WHERE number = :round"
    )
    fun getRoundByNumberExpanded(round: Int): List<RoundExpanded>

    @Query(
        "SELECT * FROM round WHERE mission = :missionId"
    )
    fun getRoundByMission(missionId: Int): List<Round>

    @Query(
        "SELECT * FROM round WHERE mission = :missionId"
    )
    fun getRoundByMissionExpanded(missionId: Int): List<RoundExpanded>

    @Query(
        "SELECT * FROM round WHERE objective = :objectiveId"
    )
    fun getRoundByObjective(objectiveId: Int): List<Round>

    @Query(
        "SELECT * FROM round WHERE objective = :objectiveId"
    )
    fun getRoundByObjectiveExpanded(objectiveId: Int): List<RoundExpanded>

    @Query(
        "SELECT * FROM round WHERE deployment = :deploymentId"
    )
    fun getRoundByDeployment(deploymentId: Int): List<Round>

    @Query(
        "SELECT * FROM round WHERE deployment = :deploymentId"
    )
    fun getRoundByDeploymentExpanded(deploymentId: Int): List<RoundExpanded>

    @Query(
        "SELECT * FROM round WHERE tournament = :tournamentId"
    )
    fun getRoundByTournament(tournamentId: Int): List<Round>

    @Query(
        "SELECT * FROM round WHERE tournament = :tournamentId"
    )
    fun getRoundByTournamentExpanded(tournamentId: Int): List<RoundExpanded>

    @Insert
    suspend fun insert(vararg round: Round)

    @Update
    fun update(vararg round: Round)

    @Query("DELETE FROM round WHERE roundID = :roundId")
    fun delete(roundId: Int)

    @Query("DELETE FROM round")
    suspend fun deleteAll()
}