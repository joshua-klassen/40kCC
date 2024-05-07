package com.example.a40kcc.data.`object`

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class TournamentWithRounds(
    @Embedded val tournament: Tournament,
    @Relation(
        parentColumn = "tournamentID",
        entityColumn = "roundID",
        entity = Round::class,
        associateBy = Junction(TournamentRoundCrossRef::class)
    )
    val round: List<Round> = emptyList()
) : CoreObject {
    override fun getCoreColumns(): Map<String, String> {
        return mapOf(
            Pair("Name", tournament.getDisplayName()),
            Pair("Number of Rounds", tournament.roundCount.toString())
        )
    }

    override fun getDetailColumns(): Map<String, String> {
        return mapOf(
            Pair("Date", tournament.date.toString())
        )
    }

    override fun getDisplayName(): String {
        return "Tournament ${tournament.getDisplayName()} Rounds ${tournament.roundCount}"
    }
}