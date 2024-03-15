package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    "player",
    foreignKeys = [ForeignKey(
        entity = Faction::class,
        childColumns = ["preferred_faction"],
        parentColumns = ["factionID"],
        onDelete = ForeignKey.SET_NULL,
        onUpdate = ForeignKey.CASCADE,
        deferred = true
    )]
)
data class Player(
    @PrimaryKey(true) val playerID: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("nickname", defaultValue = "NULL") val nickname: String?,
    @ColumnInfo("preferred_faction", defaultValue = "NULL") val factionID: Int?
)