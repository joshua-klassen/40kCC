package com.example.a40kcc.data.`object`

import androidx.room.Embedded
import androidx.room.Relation

data class OutcomeWithPlayers(
    @Embedded val outcome: Outcome,
    @Relation(
        parentColumn = "player_01",
        entityColumn = "playerID"
    )
    val player01: Player? = null,
    @Relation(
        parentColumn = "player_02",
        entityColumn = "playerID"
    )
    val player02: Player? = null
) : CoreObject {
    override fun getCoreColumns(): Map<String, String> {
        return mapOf(
            Pair("Player 01", player01?.getDisplayName() ?: ""),
            Pair("Player 01 Points", outcome.player01Points.toString()),
            Pair("Player 02", player02?.getDisplayName() ?: ""),
            Pair("Player 01 Points", outcome.player02Points.toString())
        )
    }

    override fun getDetailColumns(): Map<String, String> {
        return mapOf(
            Pair("Player 01 Points", outcome.player01TeamPoints.toString()),
            Pair("Player 02 Points", outcome.player02TeamPoints.toString()),
            Pair("Point Differential", outcome.pointDifferential.toString())
        )
    }

    override fun getDisplayName(): String {
        return "${player01?.getDisplayName() ?: ""} vs ${player02?.getDisplayName() ?: ""}\n${outcome.getDisplayName()}"
    }
}