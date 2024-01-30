package com.example.a40kcc.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity("player")
@ForeignKey(Faction, "factionID", "factionID", 3, 5, true)
data class Player(
    @PrimaryKey(true) val playerID: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("nickname", defaultValue = "NULL") val nickname: String?,
    @ColumnInfo("preferred_faction", defaultValue = "NULL") val factionID: Int?,
    @ColumnInfo("team", defaultValue = "NULL") val teamID: List<Int>?
)