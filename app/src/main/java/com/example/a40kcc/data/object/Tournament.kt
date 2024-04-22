package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    "tournament",
    indices = [Index(value = ["tournamentID"])]
)
data class Tournament(
    @PrimaryKey(true) val tournamentID: Int = 0,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("round_count") val roundCount: Int,
    @ColumnInfo("date") val date: Date
) : CoreObject {
    override fun getCoreColumns(): Map<String, String> {
        return mapOf(
            Pair("Tournament ID", tournamentID.toString()),
            Pair("Name", name),
            Pair("Number of Rounds", roundCount.toString())
        )
    }

    override fun getDetailColumns(): Map<String, String> {
        return mapOf(
            Pair("Date", date.toString())
        )
    }
}