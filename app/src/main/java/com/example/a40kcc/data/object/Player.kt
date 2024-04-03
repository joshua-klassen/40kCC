package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    "player"
)
data class Player(
    @PrimaryKey(true) val playerID: Int = 0,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("nickname", defaultValue = "NULL") val nickname: String?,
    @ColumnInfo("preferred_faction", defaultValue = "NULL") val factionName: String?
)