package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    "game",
    indices = [Index(value = ["gameID"]),
        Index(value = ["player_01"]),
        Index(value = ["player_02"]),
        Index(value = ["prediction"]),
        Index(value = ["outcome"]),
        Index(value = ["round"])],
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
    @PrimaryKey(true) val gameID: Int = 0,
    @ColumnInfo("player_01") val player01ID: Int,
    @ColumnInfo("player_02", defaultValue = "NULL") val player02ID: Int? = null,
    @ColumnInfo("player_01_faction") val player01FactionName: String,
    @ColumnInfo("player_02_faction") val player02FactionName: String,
    @ColumnInfo(
        "player_01_faction_detachment",
        defaultValue = "NULL"
    ) val player01FactionDetachment: String? = "NULL",
    @ColumnInfo(
        "player_02_faction_detachment",
        defaultValue = "NULL"
    ) val player02FactionDetachment: String? = "NULL",
    @ColumnInfo("prediction", defaultValue = "NULL") val predictionID: Int? = null,
    @ColumnInfo("round") val roundID: Int,
    @ColumnInfo("outcome", defaultValue = "NULL") val outcomeID: Int? = null
)