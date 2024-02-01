package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("faction")
data class Faction(
    @PrimaryKey(true) val factionID: Int,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("super_faction") val superFaction: String,
    @ColumnInfo("detachments", defaultValue = "NULL") val detachments: String?
)