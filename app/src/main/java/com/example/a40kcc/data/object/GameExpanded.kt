package com.example.a40kcc.data.`object`

import androidx.room.Embedded
import androidx.room.Relation

data class GameExpanded(
    @Embedded val game: Game,
    @Relation(
        parentColumn = "player_01",
        entityColumn = "playerID"
    )
    val player01: Player,
    @Relation(
        parentColumn = "player_02",
        entityColumn = "playerID"
    )
    val player02: Player?,
    @Relation(
        parentColumn = "player_01_faction",
        entityColumn = "factionID"
    )
    val player01Faction: Faction,
    @Relation(
        parentColumn = "player_02_faction",
        entityColumn = "factionID"
    )
    val player02Faction: Faction,
    @Relation(
        parentColumn = "player_01_team",
        entityColumn = "teamID"
    )
    val player01Team: Team?,
    @Relation(
        parentColumn = "player_02_team",
        entityColumn = "teamID"
    )
    val player02Team: Team?,
    @Relation(
        parentColumn = "prediction",
        entityColumn = "predictionID"
    )
    val prediction: Prediction,
    @Relation(
        parentColumn = "round",
        entityColumn = "roundID"
    )
    val round: Round,
    @Relation(
        parentColumn = "outcome",
        entityColumn = "outcomeID"
    )
    val outcome: Outcome?
)