package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "live_round",
    indices = [
        Index(value = ["liveRoundID"]),
        Index(value = ["game"]),
        Index(value = ["expected_result"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = Game::class,
            childColumns = ["game"],
            parentColumns = ["gameID"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ),
        ForeignKey(
            entity = Prediction::class,
            childColumns = ["expected_result"],
            parentColumns = ["predictionID"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        )
    ]
)
data class LiveRound(
    @PrimaryKey(autoGenerate = true) val liveRoundID: Int = 0,
    @ColumnInfo(name = "game") val gameID: Int,
    @ColumnInfo(name = "expected_result") val expectedResult: Int
) : CoreObject {
    override fun getCoreColumns(): Map<String, String> {
        return mapOf(
            Pair("Live Round ID", liveRoundID.toString()),
            Pair("Game ID", gameID.toString()),
            Pair("Expected Result ID", expectedResult.toString())
        )
    }

    override fun getDetailColumns(): Map<String, String> {
        return emptyMap()
    }

    override fun getDisplayName(): String {
        return "liveRoundID: $liveRoundID"
    }
}