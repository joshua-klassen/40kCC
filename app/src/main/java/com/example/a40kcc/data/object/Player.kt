package com.example.a40kcc.data.`object`

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    "player",
    indices = [androidx.room.Index(value = ["playerID"])]
)
data class Player(
    @PrimaryKey(true) val playerID: Int = 0,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("nickname", defaultValue = "NULL") val nickname: String?,
    @ColumnInfo("preferred_faction", defaultValue = "NULL") val factionName: String?
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
}