package com.example.a40kcc.data.`object`

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class TeamWithPlayers(
    @Embedded val team: Team,
    @Relation(
        parentColumn = "teamID",
        entityColumn = "playerID",
        entity = Player::class,
        associateBy = Junction(PlayerTeamCrossRef::class)
    )
    val player: List<Player> = emptyList(),
)