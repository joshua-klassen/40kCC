package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    "round",
    indices = [
        Index(value = ["roundID"]),
        Index(value = ["tournament"])
    ],
    foreignKeys = [
        ForeignKey(
            entity = Tournament::class,
            childColumns = ["tournament"],
            parentColumns = ["tournamentID"],
            onDelete = ForeignKey.RESTRICT,
            onUpdate = ForeignKey.CASCADE,
            deferred = true
        )
    ]
)
data class Round(
    @PrimaryKey(true) val roundID: Int = 0,
    @ColumnInfo("number") val number: Int,
    @ColumnInfo("primary_mission") val primaryMissionName: String,
    @ColumnInfo("secondary_mission") val secondaryMissionName: String,
    @ColumnInfo("deployment") val deploymentName: String,
    @ColumnInfo("tournament") val tournamentID: Int,
) : CoreObject {
    override fun getCoreColumns(): Map<String, String> {
        return mapOf(
            Pair("Round ID", roundID.toString()),
            Pair("Round Number", number.toString()),
            Pair("Tournament ID", tournamentID.toString())
        )
    }

    override fun getDetailColumns(): Map<String, String> {
        return mapOf(
            Pair("Primary Mission", primaryMissionName),
            Pair("Secondary Mission", secondaryMissionName),
            Pair("Deployment", deploymentName)
        )
    }
}