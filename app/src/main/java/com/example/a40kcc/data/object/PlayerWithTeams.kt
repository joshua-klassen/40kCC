package com.example.a40kcc.data.`object`

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PlayerWithTeams(
    @Embedded val player: Player,
    @Relation(
        parentColumn = "playerID",
        entityColumn = "teamID",
        entity = Team::class,
        associateBy = Junction(PlayerTeamCrossRef::class)
    )
    val team: List<Team> = emptyList()
) : CoreObject {
    override fun getCoreColumns(): Map<String, String> {
        return mapOf(
            Pair("Player", player.name),
            Pair("Preferred Faction", player.factionName ?: "")
        )
    }

    override fun getDetailColumns(): Map<String, String> {
        val columnMap = mutableMapOf<String, String>()

        team.forEach {
            columnMap += Pair("Team ${it.teamID}", it.name)
        }

        return columnMap
    }
}