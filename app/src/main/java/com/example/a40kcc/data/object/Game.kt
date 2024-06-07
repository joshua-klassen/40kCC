package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "game",
    indices = [
        Index(value = ["gameID"]),
        Index(value = ["player_01"]),
        Index(value = ["player_02"]),
        Index(value = ["prediction"]),
        Index(value = ["outcome"]),
        Index(value = ["round"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = Player::class,
            childColumns = ["player_01"],
            parentColumns = ["playerID"],
            onDelete = ForeignKey.CASCADE,
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
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        )
    ]
)
data class Game(
    @PrimaryKey(autoGenerate = true) val gameID: Int = 0,
    @ColumnInfo(name = "player_01") val player01ID: Int,
    @ColumnInfo(name = "player_02", defaultValue = "NULL") val player02ID: Int? = null,
    @ColumnInfo(name = "player_01_faction") val player01FactionName: String,
    @ColumnInfo(name = "player_02_faction") val player02FactionName: String,
    @ColumnInfo(
        name = "player_01_faction_detachment",
        defaultValue = "NULL"
    ) val player01FactionDetachment: String? = "NULL",
    @ColumnInfo(
        name = "player_02_faction_detachment",
        defaultValue = "NULL"
    ) val player02FactionDetachment: String? = "NULL",
    @ColumnInfo(name = "prediction", defaultValue = "NULL") val predictionID: Int? = null,
    @ColumnInfo(name = "round") val roundID: Int,
    @ColumnInfo(name = "outcome", defaultValue = "NULL") val outcomeID: Int? = null
) : CoreObject {
    override fun getCoreColumns(): Map<String, String> {
        return mapOf(
            Pair("Game ID", gameID.toString()),
            Pair("Player 01 ID", player01ID.toString()),
            Pair("Player 02 ID", player02ID?.toString() ?: ""),
            Pair("Round ID", roundID.toString())
        )
    }

    override fun getDetailColumns(): Map<String, String> {
        return mapOf(
            Pair("Player 01 Faction", player01FactionName),
            Pair("Player 01 Detachment", player01FactionDetachment.toString()),
            Pair("Player 02 Faction", player02FactionName),
            Pair("Player 02 Detachment", player02FactionDetachment.toString()),
            Pair("Prediction ID", predictionID.toString()),
            Pair("Outcome ID", outcomeID.toString())
        )
    }

    override fun getDisplayName(): String {
        return "gameID: $gameID"
    }
}