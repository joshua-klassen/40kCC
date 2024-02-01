package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.data.`object`.RoundExpanded

@Dao
interface RoundDao {
    @Query("SELECT * FROM round")
    fun getAll(): List<RoundExpanded>

    @Query("SELECT * FROM round WHERE roundID = :roundId")
    fun getById(roundId: Int): RoundExpanded

    @Query(
        "SELECT * FROM round WHERE number = :round"
    )
    fun getRoundByNumber(round: Int): List<RoundExpanded>

    @Query(
        "SELECT * FROM round WHERE mission = :missionId"
    )
    fun getRoundByMission(missionId: Int): List<RoundExpanded>

    @Query(
        "SELECT * FROM round WHERE objective = :objectiveId"
    )
    fun getRoundByObjective(objectiveId: Int): List<RoundExpanded>

    @Query(
        "SELECT * FROM round WHERE deployment = :deploymentId"
    )
    fun getRoundByDeployment(deploymentId: Int): List<RoundExpanded>

    @Query(
        "SELECT * FROM round WHERE tournament = :tournamentId"
    )
    fun getRoundByTournament(tournamentId: Int): List<RoundExpanded>

    @Insert
    suspend fun insert(vararg round: Round)

    @Query("DELETE FROM round WHERE roundID = :roundId")
    fun delete(roundId: Int)

    @Query("DELETE FROM round")
    suspend fun deleteAll()
}