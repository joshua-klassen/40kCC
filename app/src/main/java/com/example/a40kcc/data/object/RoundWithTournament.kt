package com.example.a40kcc.data.`object`

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class RoundWithTournament(
    @Embedded val round: Round,
    @Relation(
        parentColumn = "roundID",
        entityColumn = "tournamentID",
        entity = Tournament::class,
        associateBy = Junction(TournamentRoundCrossRef::class)
    )
    val tournament: Tournament,
) : CoreObject {
    override fun getCoreColumns(): Map<String, String> {
        return mapOf(
            Pair("Round Number", round.getDisplayName()),
            Pair("Tournament Name", tournament.getDisplayName()),
        )
    }

    override fun getDetailColumns(): Map<String, String> {
        return mapOf(
            Pair("Primary Mission", round.primaryMissionName),
            Pair("Secondary Mission", round.secondaryMissionName),
            Pair("Deployment", round.deploymentName),
            Pair("Date", tournament.date.toString())
        )
    }

    override fun getDisplayName(): String {
        return "Tournament ${tournament.getDisplayName()} Round ${round.getDisplayName()}"
    }
}