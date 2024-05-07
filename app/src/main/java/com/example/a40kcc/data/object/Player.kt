package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "player",
    indices = [Index(value = ["playerID"])]
)
data class Player(
    @PrimaryKey(autoGenerate = true) val playerID: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "nickname", defaultValue = "NULL") val nickname: String?,
    @ColumnInfo(name = "preferred_faction", defaultValue = "NULL") val factionName: String?
) : CoreObject {
    override fun getCoreColumns(): Map<String, String> {
        return mapOf(
            Pair("Player ID", playerID.toString()),
            Pair("Name", name)
        )
    }

    override fun getDetailColumns(): Map<String, String> {
        return mapOf(
            Pair("Nickname", nickname.toString()),
            Pair("Preferred Faction", factionName.toString())
        )
    }

    override fun getDisplayName(): String {
        return name
    }
}