package com.example.a40kcc.data.`object`

import androidx.room.Embedded
import androidx.room.Relation

data class RoundExpanded(
    @Embedded val round: Round,
    @Relation(
        parentColumn = "mission",
        entityColumn = "missionID"
    )
    val mission: Mission,
    @Relation(
        parentColumn = "objective",
        entityColumn = "objectiveID"
    )
    val objective: Objective,
    @Relation(
        parentColumn = "deployment",
        entityColumn = "deploymentID"
    )
    val deployment: Deployment,
    @Relation(
        parentColumn = "tournament",
        entityColumn = "tournamentID"
    )
    val tournament: Tournament
)
