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
)
