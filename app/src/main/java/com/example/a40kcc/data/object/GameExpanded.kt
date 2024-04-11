package com.example.a40kcc.data.`object`

import androidx.room.DatabaseView
import androidx.room.Embedded
import androidx.room.Relation

@DatabaseView
data class GameExpanded(
    @Embedded val game: Game,
    @Relation(
        parentColumn = "player_01",
        entityColumn = "playerID",
        entity = Player::class
    )
    val player01: PlayerWithTeams,
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
    val prediction: Prediction,
    @Relation(
        parentColumn = "round",
        entityColumn = "roundID",
        entity = Round::class
    )
    val round: RoundWithTournament,
    @Relation(
        parentColumn = "outcome",
        entityColumn = "outcomeID"
    )
    val outcome: Outcome? = null
)