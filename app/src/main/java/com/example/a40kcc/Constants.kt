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
import com.example.a40kcc.data.model.TournamentWithRoundsViewModel
import com.example.a40kcc.data.`object`.DataObject
import com.example.a40kcc.ui.utilities.ComposeData

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
lateinit var TOURNAMENT_WITH_ROUNDS_VIEW_MODEL: TournamentWithRoundsViewModel

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
    "players" to mapOf(
        "Text" to R.string.player_text.toString(),
        "Image" to R.drawable.icon_players.toString()
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

val COLORS: Map<String, Long> = mapOf(
    "Blue" to 0xff0000ff,
    "Green" to 0xff00ff00,
    "Yellow" to 0xffffff00,
    "Orange" to 0xffffaa00,
    "Red" to 0xffff0000,
    "Aqua" to 0xff00ffff,
    "Purple" to 0xffff00ff,
    "Light Blue" to 0xff00aaff,
    "Sea Green" to 0xff00ffaa,
    "Sick Yellow" to 0xffaaff00,
    "Deep Purple" to 0xffaa00ff,
    "Pink" to 0xffff00aa,
    "White" to 0xffffffff,
    "Black" to 0xff000000
)

lateinit var COMPOSE_DATA: ComposeData