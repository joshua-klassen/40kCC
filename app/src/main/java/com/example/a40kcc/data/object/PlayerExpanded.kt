package com.example.a40kcc.data.`object`

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PlayerExpanded(
    @Embedded val player: Player,
    @Relation(
        parentColumn = "playerID",
        entityColumn = "teamID",
        associateBy = Junction(PlayerTeamCrossRef::class)
    )
    val team: List<Team>?
)