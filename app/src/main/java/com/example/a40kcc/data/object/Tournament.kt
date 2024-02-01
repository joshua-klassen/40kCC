package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("tournament")
data class Tournament(
    @PrimaryKey(true) val tournamentID: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("round_count") val roundCount: Int,
    @ColumnInfo("date") val date: Date
)