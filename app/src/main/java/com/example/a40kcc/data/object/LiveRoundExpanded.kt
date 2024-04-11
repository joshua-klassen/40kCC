package com.example.a40kcc.data.`object`

import androidx.room.Embedded
import androidx.room.Relation

data class LiveRoundExpanded(
    @Embedded val liveRound: LiveRound,
    @Relation(
        parentColumn = "game",
        entityColumn = "gameID",
        entity = Game::class
    )
    val game: GameExpanded,
    @Relation(
        parentColumn = "expected_result",
        entityColumn = "predictionID"
    )
    val expectedResult: Prediction
)