package com.example.a40kcc.data.`object`

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    "playerTeamCrossRef",
    primaryKeys = ["playerID", "teamID"],
    foreignKeys = [ForeignKey(
        entity = Team::class,
        childColumns = ["teamID"],
        parentColumns = ["teamID"],
        onDelete = 3,
        onUpdate = 5,
        deferred = true
    ), ForeignKey(
        entity = Player::class,
        childColumns = ["playerID"],
        parentColumns = ["playerID"],
        onDelete = 3,
        onUpdate = 5,
        deferred = true
    )]
)
data class PlayerTeamCrossRef(
    val playerID: Int,
    val teamID: Int
)
