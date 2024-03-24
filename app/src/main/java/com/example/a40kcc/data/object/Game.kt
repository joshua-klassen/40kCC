package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    "game",
    foreignKeys = [ForeignKey(
        entity = Player::class,
        childColumns = ["player_01"],
        parentColumns = ["playerID"],
        onDelete = ForeignKey.SET_NULL,
        onUpdate = ForeignKey.CASCADE,
        deferred = true
    ),
        ForeignKey(
            entity = Player::class,
            childColumns = ["player_02"],
            parentColumns = ["playerID"],
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ),
        ForeignKey(
            entity = Prediction::class,
            childColumns = ["prediction"],
            parentColumns = ["predictionID"],
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ),
        ForeignKey(
            entity = Team::class,
            childColumns = ["player_01_team"],
            parentColumns = ["teamID"],
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ),
        ForeignKey(
            entity = Team::class,
            childColumns = ["player_02_team"],
            parentColumns = ["teamID"],
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ),
        ForeignKey(
            entity = Outcome::class,
            childColumns = ["outcome"],
            parentColumns = ["outcomeID"],
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ),
        ForeignKey(
            entity = Round::class,
            childColumns = ["round"],
            parentColumns = ["roundID"],
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        )]
)
data class Game(
    @PrimaryKey(true) val gameID: Int,
    @ColumnInfo("player_01") val player01ID: Int,
    @ColumnInfo("player_02", defaultValue = "NULL") val player02ID: Int?,
    @ColumnInfo("player_01_faction") val player01FactionName: String,
    @ColumnInfo("player_02_faction") val player02FactionName: String,
    @ColumnInfo(
        "player_01_faction_detachment",
        defaultValue = "NULL"
    ) val player01FactionDetachment: String?,
    @ColumnInfo(
        "player_02_faction_detachment",
        defaultValue = "NULL"
    ) val player02FactionDetachment: String?,
    @ColumnInfo("player_01_team", defaultValue = "NULL") val player01TeamID: Int?,
    @ColumnInfo("player_02_team", defaultValue = "NULL") val player02TeamID: Int?,
    @ColumnInfo("prediction", defaultValue = "NULL") val predictionID: Int?,
    @ColumnInfo("round") val roundID: Int,
    @ColumnInfo("outcome", defaultValue = "NULL") val outcomeID: Int?
)