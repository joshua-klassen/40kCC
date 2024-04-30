package com.example.a40kcc

import com.example.a40kcc.data.model.GameExpandedViewModel
import com.example.a40kcc.data.model.GameViewModel
import com.example.a40kcc.data.model.LiveRoundExpandedViewModel
import com.example.a40kcc.data.model.LiveRoundViewModel
import com.example.a40kcc.data.model.OutcomeViewModel
import com.example.a40kcc.data.model.OutcomeWithPlayersViewModel
import com.example.a40kcc.data.model.PlayerViewModel
import com.example.a40kcc.data.model.PlayerWithTeamsViewModel
import com.example.a40kcc.data.model.PredictionViewModel
import com.example.a40kcc.data.model.RoundViewModel
import com.example.a40kcc.data.model.RoundWithTournamentViewModel
import com.example.a40kcc.data.model.TeamViewModel
import com.example.a40kcc.data.model.TeamWithPlayersViewModel
import com.example.a40kcc.data.model.TournamentViewModel
import com.example.a40kcc.data.`object`.DataObject

lateinit var DEPLOYMENT_DATA: DataObject
lateinit var FACTION_DATA: DataObject
lateinit var PRIMARY_MISSION_DATA: DataObject
lateinit var SECONDARY_MISSION_DATA: DataObject

lateinit var GAME_VIEW_MODEL: GameViewModel
lateinit var GAME_EXPANDED_VIEW_MODEL: GameExpandedViewModel
lateinit var LIVE_ROUND_VIEW_MODEL: LiveRoundViewModel
lateinit var LIVE_ROUND_EXPANDED_VIEW_MODEL: LiveRoundExpandedViewModel
lateinit var OUTCOME_VIEW_MODEL: OutcomeViewModel
lateinit var OUTCOME_WITH_PLAYERS_VIEW_MODEL: OutcomeWithPlayersViewModel
lateinit var PLAYER_VIEW_MODEL: PlayerViewModel
lateinit var PLAYER_WITH_TEAMS_VIEW_MODEL: PlayerWithTeamsViewModel
lateinit var PREDICTION_VIEW_MODEL: PredictionViewModel
lateinit var ROUND_VIEW_MODEL: RoundViewModel
lateinit var ROUND_WITH_TOURNAMENT_VIEW_MODEL: RoundWithTournamentViewModel
lateinit var TEAM_VIEW_MODEL: TeamViewModel
lateinit var TEAM_WITH_PLAYERS_VIEW_MODEL: TeamWithPlayersViewModel
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
    "liveRound" to mapOf(
        "Text" to R.string.live_round_text.toString(),
        "Image" to R.drawable.icon_live_round.toString()
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