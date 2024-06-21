package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "historical_round_data"
)
data class HistoricalRoundData(
    @PrimaryKey(autoGenerate = true) val historicalRoundDataID: Int = 0,
    @ColumnInfo(
        name = "inserted_timestamp",
        defaultValue = "CURRENT_TIMESTAMP"
    ) val insertedTimestamp: Date = Date(),
    @ColumnInfo(name = "tournament", defaultValue = "") val tournament: String = "",
    @ColumnInfo(name = "round", defaultValue = "-1") val round: Int = -1,
    @ColumnInfo(name = "primary_mission", defaultValue = "") val primaryMission: String = "",
    @ColumnInfo(name = "secondary_mission", defaultValue = "") val secondaryMission: String = "",
    @ColumnInfo(name = "deployment", defaultValue = "") val deployment: String = "",
    @ColumnInfo(name = "player_01", defaultValue = "") val player01: String = "",
    @ColumnInfo(name = "player_01_faction", defaultValue = "") val player01Faction: String = "",
    @ColumnInfo(
        name = "player_01_detachment",
        defaultValue = ""
    ) val player01Detachment: String = "",
    @ColumnInfo(name = "player_01_team", defaultValue = "") val player01Team: String = "",
    @ColumnInfo(name = "player_02", defaultValue = "") val player02: String = "",
    @ColumnInfo(name = "player_02_faction", defaultValue = "") val player02Faction: String = "",
    @ColumnInfo(
        name = "player_02_detachment",
        defaultValue = ""
    ) val player02Detachment: String = "",
    @ColumnInfo(name = "player_02_team", defaultValue = "") val player02Team: String = "",
    @ColumnInfo(name = "initial_prediction", defaultValue = "") val initialPrediction: String = "",
    @ColumnInfo(name = "prediction", defaultValue = "") val prediction: String = "",
    @ColumnInfo(name = "player_01_points", defaultValue = "-1") val player01Points: Int = -1,
    @ColumnInfo(name = "player_02_points", defaultValue = "-1") val player02Points: Int = -1,
    @ColumnInfo(
        name = "player_01_team_points",
        defaultValue = "-1"
    ) val player01TeamPoints: Int = -1,
    @ColumnInfo(
        name = "player_02_team_points",
        defaultValue = "-1"
    ) val player02TeamPoints: Int = -1,
    @ColumnInfo(name = "point_differential", defaultValue = "-1") val pointDifferential: Int = -1,
    @ColumnInfo(name = "is_complete", defaultValue = "false") val isComplete: Boolean = false
) : CoreObject {
    override fun getCoreColumns(): Map<String, String> {
        return mapOf(
            Pair("Inserted Timestamp", insertedTimestamp.toString()),
            Pair("Tournament", tournament),
            Pair("Round", round.toString()),
            Pair("Primary Mission", primaryMission),
            Pair("Secondary Mission", secondaryMission),
            Pair("Deployment", deployment),
            Pair("Initial Prediction", initialPrediction),
            Pair("Prediction", prediction),
            Pair("Is Complete", isComplete.toString())
        )
    }

    override fun getDetailColumns(): Map<String, String> {
        return mapOf(
            Pair("Player 01", player01),
            Pair("Player 01 Faction", player01Faction),
            Pair("Player 01 Detachment", player01Detachment),
            Pair("Player 01 Team", player01Team),
            Pair("Player 02", player02),
            Pair("Player 02 Faction", player02Faction),
            Pair("Player 02 Detachment", player02Detachment),
            Pair("Player 02 Team", player02Team),
            Pair("Player 01 Points", player01Points.toString()),
            Pair("Player 01 Team Points", player01TeamPoints.toString()),
            Pair("Player 02 Points", player02Points.toString()),
            Pair("Player 02 Team Points", player02TeamPoints.toString()),
            Pair("Point Differential", pointDifferential.toString())
        )
    }

    override fun getDisplayName(): String {
        return historicalRoundDataID.toString()
    }
}