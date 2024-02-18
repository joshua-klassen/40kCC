package com.example.a40kcc.data.`object`

import androidx.room.Embedded
import androidx.room.Relation

data class OutcomeExpanded(
    @Embedded val outcome: Outcome,
    @Relation(
        parentColumn = "player_01",
        entityColumn = "playerID"
    )
    val player01: Player,
    @Relation(
        parentColumn = "player_02",
        entityColumn = "playerID"
    )
    val player02: Player?
)