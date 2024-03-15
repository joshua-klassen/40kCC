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
        onDelete = ForeignKey.SET_NULL,
        onUpdate = ForeignKey.CASCADE,
        deferred = true
    ), ForeignKey(
        entity = Player::class,
        childColumns = ["playerID"],
        parentColumns = ["playerID"],
        onDelete = ForeignKey.SET_NULL,
        onUpdate = ForeignKey.CASCADE,
        deferred = true
    )]
)
data class PlayerTeamCrossRef(
    val playerID: Int,
    val teamID: Int
)
