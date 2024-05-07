package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "round",
    indices = [
        Index(value = ["roundID"])
    ]
)
data class Round(
    @PrimaryKey(autoGenerate = true) val roundID: Int = 0,
    @ColumnInfo(name = "number") val number: Int,
    @ColumnInfo(name = "primary_mission") val primaryMissionName: String,
    @ColumnInfo(name = "secondary_mission") val secondaryMissionName: String,
    @ColumnInfo(name = "deployment") val deploymentName: String
) : CoreObject {
    override fun getCoreColumns(): Map<String, String> {
        return mapOf(
            Pair("Round ID", roundID.toString()),
            Pair("Round Number", number.toString())
        )
    }

    override fun getDetailColumns(): Map<String, String> {
        return mapOf(
            Pair("Primary Mission", primaryMissionName),
            Pair("Secondary Mission", secondaryMissionName),
            Pair("Deployment", deploymentName)
        )
    }

    override fun getDisplayName(): String {
        return number.toString()
    }
}