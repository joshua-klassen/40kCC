package com.example.a40kcc.data.`object`

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "playerTeamCrossRef",
    indices = [
        Index(value = ["playerID"]),
        Index(value = ["teamID"])
    ],
    primaryKeys = ["playerID", "teamID"],
    foreignKeys = [
        ForeignKey(
            entity = Team::class,
            childColumns = ["teamID"],
            parentColumns = ["teamID"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ),
        ForeignKey(
            entity = Player::class,
            childColumns = ["playerID"],
            parentColumns = ["playerID"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        )
    ]
)
data class PlayerTeamCrossRef(
    val playerID: Int,
    val teamID: Int
)
