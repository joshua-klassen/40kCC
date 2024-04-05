package com.example.a40kcc.ui.utilities

import com.example.a40kcc.R
import com.example.a40kcc.data.model.GameViewModel
import com.example.a40kcc.data.model.OutcomeViewModel
import com.example.a40kcc.data.model.PlayerViewModel
import com.example.a40kcc.data.model.PredictionViewModel
import com.example.a40kcc.data.model.RoundViewModel
import com.example.a40kcc.data.model.TeamViewModel
import com.example.a40kcc.data.model.TournamentViewModel
import com.example.a40kcc.data.`object`.DataObject

lateinit var DEPLOYMENT_DATA: DataObject
lateinit var FACTION_DATA: DataObject
lateinit var PRIMARY_MISSION_DATA: DataObject
lateinit var SECONDARY_MISSION_DATA: DataObject

lateinit var GAME_VIEW_MODEL: GameViewModel
lateinit var OUTCOME_VIEW_MODEL: OutcomeViewModel
lateinit var PLAYER_VIEW_MODEL: PlayerViewModel
lateinit var PREDICTION_VIEW_MODEL: PredictionViewModel
lateinit var ROUND_VIEW_MODEL: RoundViewModel
lateinit var TEAM_VIEW_MODEL: TeamViewModel
lateinit var TOURNAMENT_VIEW_MODEL: TournamentViewModel

val MAIN_ROUTES: Map<String, Map<String, String>> = mapOf(
    "deployments" to mapOf(
        "Text" to R.string.deployment_text.toString(),
        "Image" to R.drawable.icon_deployments.toString()
    ),
    "factions" to mapOf(
        "Text" to R.string.faction_text.toString(),
        "Image" to R.drawable.icon_factions.toString()
    ),
    "games" to mapOf(
        "Text" to R.string.game_text.toString(),
        "Image" to R.drawable.icon_games.toString()
    ),
    "primaryMissions" to mapOf(
        "Text" to R.string.primary_mission_text.toString(),
        "Image" to R.drawable.icon_primary_missions.toString()
    ),
    "secondaryMissions" to mapOf(
        "Text" to R.string.secondary_mission_text.toString(),
        "Image" to R.drawable.icon_secondary_missions.toString()
    ),
    "outcomes" to mapOf(
        "Text" to R.string.outcome_text.toString(),
        "Image" to R.drawable.icon_outcomes.toString()
    ),
    "players" to mapOf(
        "Text" to R.string.player_text.toString(),
        "Image" to R.drawable.icon_players.toString()
    ),
    "predictions" to mapOf(
        "Text" to R.string.prediction_text.toString(),
        "Image" to R.drawable.icon_predictions.toString()
    ),
    "rounds" to mapOf(
        "Text" to R.string.round_text.toString(),
        "Image" to R.drawable.icon_rounds.toString()
    ),
    "teams" to mapOf(
        "Text" to R.string.team_text.toString(),
        "Image" to R.drawable.icon_teams.toString()
    ),
    "tournaments" to mapOf(
        "Text" to R.string.tournament_text.toString(),
        "Image" to R.drawable.icon_tournaments.toString()
    )
)