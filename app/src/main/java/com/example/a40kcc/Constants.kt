package com.example.a40kcc

import com.example.a40kcc.data.model.GameExpandedViewModel
import com.example.a40kcc.data.model.GameViewModel
import com.example.a40kcc.data.model.HistoricalRoundDataViewModel
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

lateinit var DEPLOYMENT_DATA: DataObject
lateinit var FACTION_DATA: DataObject
lateinit var PRIMARY_MISSION_DATA: DataObject
lateinit var SECONDARY_MISSION_DATA: DataObject

lateinit var GAME_VIEW_MODEL: GameViewModel
lateinit var GAME_EXPANDED_VIEW_MODEL: GameExpandedViewModel
lateinit var HISTORICAL_ROUND_DATA_VIEW_MODEL: HistoricalRoundDataViewModel
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

data class RouteInfo(val textResId: Int, val imageResId: Int)

val MAIN_ROUTES: Map<String, RouteInfo> = mapOf(
    "deployments" to RouteInfo(textResId = R.string.deployment_text, imageResId = R.drawable.icon_deployments),
    "factions" to RouteInfo(textResId = R.string.faction_text, imageResId = R.drawable.icon_factions),
    "games" to RouteInfo(textResId = R.string.game_text, imageResId = R.drawable.icon_games),
    "liveRound" to RouteInfo(textResId = R.string.live_round_text, imageResId = R.drawable.icon_live_round),
    "primaryMissions" to RouteInfo(textResId = R.string.primary_mission_text, imageResId = R.drawable.icon_primary_missions),
    "secondaryMissions" to RouteInfo(textResId = R.string.secondary_mission_text, imageResId = R.drawable.icon_secondary_missions),
    "players" to RouteInfo(textResId = R.string.player_text, imageResId = R.drawable.icon_players),
    "teams" to RouteInfo(textResId = R.string.team_text, imageResId = R.drawable.icon_teams),
    "tournaments" to RouteInfo(textResId = R.string.tournament_text, imageResId = R.drawable.icon_tournaments)
)

val COLORS: Map<String, Int> = mapOf(
    "Blue" to 0xFF0000FF.toInt(),
    "Green" to 0xFF00FF00.toInt(),
    "Yellow" to 0xFFFFFF00.toInt(),
    "Orange" to 0xFFFFAA00.toInt(),
    "Red" to 0xFFFF0000.toInt(),
    "Aqua" to 0xFF00FFFF.toInt(),
    "Purple" to 0xFFFF00FF.toInt(),
    "Light Blue" to 0xFF00AAFF.toInt(),
    "Sea Green" to 0xFF00FFAA.toInt(),
    "Sick Yellow" to 0xFFAAFF00.toInt(),
    "Deep Purple" to 0xFFAA00FF.toInt(),
    "Pink" to 0xFFFF00AA.toInt(),
    "White" to 0xFFFFFFFF.toInt(),
    "Black" to 0xFF000000.toInt()
)