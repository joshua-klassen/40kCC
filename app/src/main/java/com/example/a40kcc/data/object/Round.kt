package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    "round",
    foreignKeys = [ForeignKey(
        entity = Mission::class,
        childColumns = ["mission"],
        parentColumns = ["missionID"],
        onDelete = ForeignKey.SET_NULL,
        onUpdate = ForeignKey.CASCADE,
        deferred = true
    ),
        ForeignKey(
            entity = Objective::class,
            childColumns = ["objective"],
            parentColumns = ["objectiveID"],
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ),
        ForeignKey(
            entity = Deployment::class,
            childColumns = ["deployment"],
            parentColumns = ["deploymentID"],
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        ),
        ForeignKey(
            entity = Tournament::class,
            childColumns = ["tournament"],
            parentColumns = ["tournamentID"],
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        )]
)
data class Round(
    @PrimaryKey(true) val roundID: Int,
    @ColumnInfo("number") val number: Int,
    @ColumnInfo("mission") val missionID: Int,
    @ColumnInfo("objective") val objectiveID: Int,
    @ColumnInfo("deployment") val deploymentID: Int,
    @ColumnInfo("tournament") val tournamentID: Int,
)