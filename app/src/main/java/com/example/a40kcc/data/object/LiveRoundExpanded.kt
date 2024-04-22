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
) : CoreObject {
    override fun getCoreColumns(): Map<String, String> {
        return mapOf(
            Pair("Player 01", game.player01.player.name),
            Pair("Player 02", game.player02?.player?.name ?: ""),
            Pair("Prediction", game.prediction.name),
            Pair("Expected Result", expectedResult.name)
        )
    }

    override fun getDetailColumns(): Map<String, String> {
        return mapOf(
            Pair("Player 01 Faction", game.player01.player.factionName ?: ""),
            Pair("Player 02 Faction", game.player02?.player?.factionName ?: ""),
            Pair("Tournament", game.round.tournament.name),
            Pair("Round #", game.round.round.number.toString()),
            Pair("Deployment", game.round.round.deploymentName),
            Pair("Primary Mission", game.round.round.primaryMissionName),
            Pair("Secondary Mission", game.round.round.secondaryMissionName)
        )
    }
}