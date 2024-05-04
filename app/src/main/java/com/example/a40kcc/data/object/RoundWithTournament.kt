package com.example.a40kcc.data.`object`

import androidx.room.Embedded
import androidx.room.Relation

data class RoundWithTournament(
    @Embedded val round: Round,
    @Relation(
        parentColumn = "tournament",
        entityColumn = "tournamentID"
    )
    val tournament: Tournament
) : CoreObject {
    override fun getCoreColumns(): Map<String, String> {
        return mapOf(
            Pair("Tournament", tournament.name),
            Pair("Round #", round.number.toString())
        )
    }

    override fun getDetailColumns(): Map<String, String> {
        return mapOf(
            Pair("Round Count", tournament.roundCount.toString()),
            Pair("Date", tournament.date.toString()),
            Pair("Deployment", round.deploymentName),
            Pair("Primary Mission", round.primaryMissionName),
            Pair("Secondary Mission", round.secondaryMissionName)
        )
    }

    override fun getDisplayName(): String {
        return tournament.name + " Round " + round.number.toString()
    }
}