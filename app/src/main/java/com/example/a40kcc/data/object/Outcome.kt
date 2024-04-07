package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.sql.Types.NULL


@Entity(
    "outcome",
    indices = [Index(value = ["outcomeID"]),
        Index(value = ["player_01"]),
        Index(value = ["player_02"])],
    foreignKeys = [ForeignKey(
        entity = Player::class,
        childColumns = ["player_01"],
        parentColumns = ["playerID"],
        onDelete = 3,
        onUpdate = 5,
        deferred = true
    ),
        ForeignKey(
            entity = Player::class,
            childColumns = ["player_02"],
            parentColumns = ["playerID"],
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        )]
)
data class Outcome(
    @PrimaryKey(true) val outcomeID: Int = 0,
    @ColumnInfo("player_01") val player01ID: Int,
    @ColumnInfo("player_02", defaultValue = "NULL") val player02ID: Int? = NULL,
    @ColumnInfo("player_01_points") val player01Points: Int,
    @ColumnInfo("player_02_points") val player02Points: Int,
    @ColumnInfo("player_01_team_points") val player01TeamPoints: Int,
    @ColumnInfo("player_02_team_points") val player02TeamPoints: Int,
    @ColumnInfo("point_differential") val pointDifferential: Int
)