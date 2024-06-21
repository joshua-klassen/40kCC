package com.example.a40kcc.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.a40kcc.data.`object`.Game
import com.example.a40kcc.data.`object`.LiveRound
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao : BaseDao<Game> {
    @Query("SELECT * FROM game")
    fun getAll(): List<Game>

    @Query("SELECT * FROM game")
    fun getAllFlow(): Flow<List<Game>>

    @Query("SELECT * FROM game WHERE gameID = :gameId")
    fun getById(gameId: Int): Game

    @Query("SELECT * FROM game WHERE (player_01 = :playerId OR player_02 = :playerId)")
    fun getByPlayerId(playerId: Int): List<Game>

    @Query("SELECT * FROM game " +
            "INNER JOIN tournamentRoundCrossRef ON tournamentRoundCrossRef.tournamentID = :tournamentId " +
            "WHERE game.round = tournamentRoundCrossRef.roundID")
    fun getGameByTournamentId(tournamentId: Int): List<Game>
}