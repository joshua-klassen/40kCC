package com.example.a40kcc.data.`object`

import androidx.room.Embedded
import androidx.room.Relation

data class GameExpanded(
    @Embedded val game: Game,
    @Relation(
        parentColumn = "player_01",
        entityColumn = "playerID",
        entity = Player::class
    )
    val player01: PlayerWithTeams? = null,
    @Relation(
        parentColumn = "player_02",
        entityColumn = "playerID",
        entity = Player::class
    )
    val player02: PlayerWithTeams? = null,
    @Relation(
        parentColumn = "prediction",
        entityColumn = "predictionID"
    )
    val prediction: Prediction? = null,
    @Relation(
        parentColumn = "round",
        entityColumn = "roundID",
        entity = Round::class
    )
    val round: RoundWithTournament? = null,
    @Relation(
        parentColumn = "outcome",
        entityColumn = "outcomeID"
    )
    val outcome: Outcome? = null
) : CoreObject {
    override fun getCoreColumns(): Map<String, String> {
        return mapOf(
            Pair("Player 01", player01?.getDisplayName() ?: ""),
            Pair("Player 02", player02?.getDisplayName() ?: ""),
            Pair("Prediction", prediction?.getDisplayName() ?: ""),
            Pair("Round #", round?.round?.getDisplayName() ?: ""),
            Pair("Tournament", round?.tournament?.getDisplayName() ?: "")
        )
    }

    override fun getDetailColumns(): Map<String, String> {
        return mapOf(
            Pair("Player 01 Faction", player01?.player?.factionName ?: ""),
            Pair("Player 02 Faction", player02?.player?.factionName ?: ""),
            Pair("Deployment", round?.round?.deploymentName ?: ""),
            Pair("Primary Mission", round?.round?.primaryMissionName ?: ""),
            Pair("Secondary Mission", round?.round?.secondaryMissionName ?: "")
        )
    }

    override fun getDisplayName(): String {
        return "Game ${player01?.getDisplayName() ?: ""} at ${round?.getDisplayName() ?: ""}"
    }
}