package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    "live_round",
    indices = [Index(value = ["liveRoundID"]),
        Index(value = ["game"]),
        Index(value = ["expected_result"])],
    foreignKeys = [ForeignKey(
        entity = Game::class,
        childColumns = ["game"],
        parentColumns = ["gameID"],
        onDelete = ForeignKey.SET_NULL,
        onUpdate = ForeignKey.CASCADE,
        deferred = true
    ),
        ForeignKey(
            entity = Prediction::class,
            childColumns = ["expected_result"],
            parentColumns = ["predictionID"],
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        )]
)
data class LiveRound(
    @PrimaryKey(true) val liveRoundID: Int = 0,
    @ColumnInfo("game") val gameID: Int,
    @ColumnInfo("expected_result") val expectedResult: Int
)