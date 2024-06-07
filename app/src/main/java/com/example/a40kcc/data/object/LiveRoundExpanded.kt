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
    val game: GameExpanded? = null,
    @Relation(
        parentColumn = "expected_result",
        entityColumn = "predictionID"
    )
    val expectedResult: Prediction? = null
) : CoreObject {
    override fun getCoreColumns(): Map<String, String> {
        return mapOf(
            Pair("Player 01", game?.player01?.getDisplayName() ?: ""),
            Pair("Player 02", game?.player02?.getDisplayName() ?: ""),
            Pair("Prediction", game?.prediction?.getDisplayName() ?: ""),
            Pair("Expected Result", expectedResult?.getDisplayName() ?: "")
        )
    }

    override fun getDetailColumns(): Map<String, String> {
        return mapOf(
            Pair("Player 01 Faction", game?.player01?.player?.factionName ?: ""),
            Pair("Player 02 Faction", game?.player02?.player?.factionName ?: ""),
            Pair("Event", game?.round?.getDisplayName() ?: ""),
            Pair("Deployment", game?.round?.round?.deploymentName ?: ""),
            Pair("Primary Mission", game?.round?.round?.primaryMissionName ?: ""),
            Pair("Secondary Mission", game?.round?.round?.secondaryMissionName ?: "")
        )
    }

    override fun getDisplayName(): String {
        return "${game?.round?.getDisplayName() ?: ""} for ${game?.player01?.getDisplayName() ?: ""}"
    }
}