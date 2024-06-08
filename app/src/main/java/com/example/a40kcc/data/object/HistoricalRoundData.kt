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
    @ColumnInfo(name = "player_02", defaultValue = "") val player02: String = "",
    @ColumnInfo(name = "player_02_faction", defaultValue = "") val player02Faction: String = "",
    @ColumnInfo(
        name = "player_02_detachment",
        defaultValue = ""
    ) val player02Detachment: String = "",
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
)