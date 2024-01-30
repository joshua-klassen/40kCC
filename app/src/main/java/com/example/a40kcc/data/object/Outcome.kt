package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("outcome")
data class Outcome(
    @PrimaryKey(true) val outcomeID: Int,
    @ColumnInfo("player_01") val player01ID: Int,
    @ColumnInfo("player_02") val player02ID: Int?,
    @ColumnInfo("player_01_points") val player01Points: Int,
    @ColumnInfo("player_02_points") val player02Points: Int,
    @ColumnInfo("player_01_team_points") val player01TeamPoints: Int,
    @ColumnInfo("player_02_team_points") val player02TeamPoints: Int,
    @ColumnInfo("point_differential") val pointDifferential: Int
)