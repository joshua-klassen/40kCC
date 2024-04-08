package com.example.a40kcc.data.`object`

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class TeamExpanded(
    val team: Team,
    @Relation(
        parentColumn = "playerID",
        entityColumn = "teamID",
        associateBy = Junction(PlayerTeamCrossRef::class)
    )
    @Embedded val player: List<Player>? = null,
)