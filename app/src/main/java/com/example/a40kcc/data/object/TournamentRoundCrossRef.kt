package com.example.a40kcc.data.`object`

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "tournamentRoundCrossRef",
    indices = [
        Index(value = ["tournamentID"]),
        Index(value = ["roundID"])
    ],
    primaryKeys = ["tournamentID", "roundID"],
    foreignKeys = [
        ForeignKey(
            entity = Round::class,
            childColumns = ["roundID"],
            parentColumns = ["roundID"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ),
        ForeignKey(
            entity = Tournament::class,
            childColumns = ["tournamentID"],
            parentColumns = ["tournamentID"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        )
    ]
)
data class TournamentRoundCrossRef(
    val tournamentID: Int,
    val roundID: Int
)