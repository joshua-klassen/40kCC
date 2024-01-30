package com.example.a40kcc.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.a40kcc.data.dao.PlayerDao
import com.example.a40kcc.data.`object`.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Player::class], version = 1, exportSchema = false)
abstract class Application40kCCDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao

    private class Application40kCCDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.playerDao())
                }
            }
        }

        suspend fun populateDatabase(playerDao: PlayerDao) {
            playerDao.deleteAll()

            var player = Player(0, "Eric", "Elf")
            playerDao.insert(player)
            player = Player(0, "Diogo", "WE")
            playerDao.insert(player)
            player = Player(0, "Ridvan", "Dark")
            playerDao.insert(player)
            player = Player(0, "Dustin", "GSC")
            playerDao.insert(player)
            player = Player(0, "Nick", "Necron")
            playerDao.insert(player)
            player = Player(0, "Jeff", "Short")
            playerDao.insert(player)
            player = Player(0, "Cody", "Sister")
            playerDao.insert(player)
            player = Player(0, "Dickie", "Swarm")
            playerDao.insert(player)
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