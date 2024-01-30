package com.example.a40kcc.data.`object`

import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity("tournament")
data class Tournament(
    @PrimaryKey(true) val tournamentID: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("round_count") val roundCount: Int,
    @ColumnInfo("date") val date: Date,
    @ColumnInfo("logo") val logo: Image?
)