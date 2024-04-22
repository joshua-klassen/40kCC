package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    "team",
    indices = [Index(value = ["teamID"])]
)
data class Team(
    @PrimaryKey(true) val teamID: Int = 0,
    @ColumnInfo("name") val name: String
) : CoreObject {
    override fun getCoreColumns(): Map<String, String> {
        return mapOf(
            Pair("Team ID", teamID.toString()),
            Pair("Name", name)
        )
    }

    override fun getDetailColumns(): Map<String, String> {
        return emptyMap()
    }
}