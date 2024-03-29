package com.example.a40kcc.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.a40kcc.data.dao.GameDao
import com.example.a40kcc.data.dao.OutcomeDao
import com.example.a40kcc.data.dao.PlayerDao
import com.example.a40kcc.data.dao.PredictionDao
import com.example.a40kcc.data.dao.RoundDao
import com.example.a40kcc.data.dao.TeamDao
import com.example.a40kcc.data.dao.TournamentDao
import com.example.a40kcc.data.`object`.Game
import com.example.a40kcc.data.`object`.Outcome
import com.example.a40kcc.data.`object`.Player
import com.example.a40kcc.data.`object`.PlayerTeamCrossRef
import com.example.a40kcc.data.`object`.Prediction
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.data.`object`.Team
import com.example.a40kcc.data.`object`.Tournament
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [Game::class, Outcome::class, Player::class, PlayerTeamCrossRef::class, Prediction::class, Round::class, Team::class, Tournament::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class Application40kCCDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun outcomeDao(): OutcomeDao
    abstract fun playerDao(): PlayerDao
    abstract fun predictionDao(): PredictionDao
    abstract fun roundDao(): RoundDao
    abstract fun teamDao(): TeamDao
    abstract fun tournamentDao(): TournamentDao

    private class Application40kCCDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { _ ->
                scope.launch {}
            }
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
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}