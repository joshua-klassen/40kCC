package com.example.a40kcc.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.a40kcc.COLORS
import com.example.a40kcc.data.dao.GameDao
import com.example.a40kcc.data.dao.GameExpandedDao
import com.example.a40kcc.data.dao.HistoricalRoundDataDao
import com.example.a40kcc.data.dao.LiveRoundDao
import com.example.a40kcc.data.dao.LiveRoundExpandedDao
import com.example.a40kcc.data.dao.OutcomeDao
import com.example.a40kcc.data.dao.OutcomeWithPlayersDao
import com.example.a40kcc.data.dao.PlayerDao
import com.example.a40kcc.data.dao.PlayerWithTeamsDao
import com.example.a40kcc.data.dao.PredictionDao
import com.example.a40kcc.data.dao.RoundDao
import com.example.a40kcc.data.dao.RoundWithTournamentDao
import com.example.a40kcc.data.dao.TeamDao
import com.example.a40kcc.data.dao.TeamWithPlayersDao
import com.example.a40kcc.data.dao.TournamentDao
import com.example.a40kcc.data.dao.TournamentWithRoundsDao
import com.example.a40kcc.data.`object`.Game
import com.example.a40kcc.data.`object`.HistoricalRoundData
import com.example.a40kcc.data.`object`.LiveRound
import com.example.a40kcc.data.`object`.Outcome
import com.example.a40kcc.data.`object`.Player
import com.example.a40kcc.data.`object`.PlayerTeamCrossRef
import com.example.a40kcc.data.`object`.Prediction
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.data.`object`.Team
import com.example.a40kcc.data.`object`.Tournament
import com.example.a40kcc.data.`object`.TournamentRoundCrossRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [Game::class, HistoricalRoundData::class, LiveRound::class, Outcome::class, Player::class, PlayerTeamCrossRef::class, Prediction::class, Round::class, Team::class, Tournament::class, TournamentRoundCrossRef::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class Application40kCCDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun gameExpandedDao(): GameExpandedDao
    abstract fun historicalRoundDataDao(): HistoricalRoundDataDao
    abstract fun liveRoundDao(): LiveRoundDao
    abstract fun liveRoundExpandedDao(): LiveRoundExpandedDao
    abstract fun outcomeDao(): OutcomeDao
    abstract fun outcomeWithPlayersDao(): OutcomeWithPlayersDao
    abstract fun playerDao(): PlayerDao
    abstract fun playerWithTeamsDao(): PlayerWithTeamsDao
    abstract fun predictionDao(): PredictionDao
    abstract fun roundDao(): RoundDao
    abstract fun roundWithTournamentDao(): RoundWithTournamentDao
    abstract fun teamDao(): TeamDao
    abstract fun teamWithPlayersDao(): TeamWithPlayersDao
    abstract fun tournamentDao(): TournamentDao
    abstract fun tournamentWithRoundsDao(): TournamentWithRoundsDao

    private class Application40kCCDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populatePredictions(database.predictionDao())
                }
            }
        }

        suspend fun populatePredictions(predictionDao: PredictionDao) {
            var prediction = Prediction(
                name = "Guaranteed",
                color = COLORS.getValue(key = "Blue"),
                minPoints = 16,
                maxPoints = 20,
                defaultOption = true
            )
            predictionDao.insert(prediction)
            prediction = Prediction(
                name = "Advantage",
                color = COLORS.getValue(key = "Green"),
                minPoints = 12,
                maxPoints = 15,
                defaultOption = true
            )
            predictionDao.insert(prediction)
            prediction = Prediction(
                name = "Close Game",
                color = COLORS.getValue(key = "Yellow"),
                minPoints = 9,
                maxPoints = 11,
                defaultOption = true
            )
            predictionDao.insert(prediction)
            prediction = Prediction(
                name = "Disadvantage",
                color = COLORS.getValue(key = "Orange"),
                minPoints = 5,
                maxPoints = 9,
                defaultOption = true
            )
            predictionDao.insert(prediction)
            prediction = Prediction(
                name = "Volatile/Unsure",
                color = COLORS.getValue(key = "Red"),
                minPoints = 0,
                maxPoints = 4,
                defaultOption = true
            )
            predictionDao.insert(prediction)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: Application40kCCDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): Application40kCCDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Application40kCCDatabase::class.java,
                    "40kCC-database"
                )
                    .addCallback(Application40kCCDatabaseCallback(scope))
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}