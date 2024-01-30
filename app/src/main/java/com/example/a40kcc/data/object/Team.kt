package com.example.a40kcc.data.`object`

import android.media.Image
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("team")
data class Team(
    @PrimaryKey(true) val teamID: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("logo") val logo: Image?
)