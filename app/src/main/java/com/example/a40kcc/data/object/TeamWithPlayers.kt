package com.example.a40kcc.data.`object`

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class TeamWithPlayers(
    @Embedded val team: Team,
    @Relation(
        parentColumn = "teamID",
        entityColumn = "playerID",
        entity = Player::class,
        associateBy = Junction(PlayerTeamCrossRef::class)
    )
    val player: List<Player> = emptyList(),
) : CoreObject {
    override fun getCoreColumns(): Map<String, String> {
        return mapOf(
            Pair("Team Name", team.getDisplayName())
        )
    }

    override fun getDetailColumns(): Map<String, String> {
        val columnMap = mutableMapOf<String, String>()

        player.forEach {
            columnMap += Pair("Player ${it.playerID}", it.getDisplayName())
        }

        return columnMap
    }

    override fun getDisplayName(): String {
        return team.getDisplayName()
    }
}