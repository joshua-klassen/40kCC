package com.example.a40kcc.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.a40kcc.data.dao.DeploymentDao
import com.example.a40kcc.data.dao.FactionDao
import com.example.a40kcc.data.dao.GameDao
import com.example.a40kcc.data.dao.MissionDao
import com.example.a40kcc.data.dao.ObjectiveDao
import com.example.a40kcc.data.dao.OutcomeDao
import com.example.a40kcc.data.dao.PlayerDao
import com.example.a40kcc.data.dao.PredictionDao
import com.example.a40kcc.data.dao.RoundDao
import com.example.a40kcc.data.dao.TeamDao
import com.example.a40kcc.data.dao.TournamentDao
import com.example.a40kcc.data.`object`.Deployment
import com.example.a40kcc.data.`object`.Faction
import com.example.a40kcc.data.`object`.Game
import com.example.a40kcc.data.`object`.Mission
import com.example.a40kcc.data.`object`.Objective
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
    entities = [Deployment::class, Faction::class, Game::class, Mission::class, Objective::class, Outcome::class, Player::class, PlayerTeamCrossRef::class, Prediction::class, Round::class, Team::class, Tournament::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class Application40kCCDatabase : RoomDatabase() {
    abstract fun deploymentDao(): DeploymentDao
    abstract fun factionDao(): FactionDao
    abstract fun gameDao(): GameDao
    abstract fun missionDao(): MissionDao
    abstract fun objectiveDao(): ObjectiveDao
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
            INSTANCE?.let { database ->
                scope.launch {
                    populateFactions(database.factionDao())
                    populateMissions(database.missionDao())
                }
            }
        }

        suspend fun populateFactions(factionDao: FactionDao) {
            var faction = Faction(0, "Ultramarines", "Adeptus Astartes", "Gladius Task Force")
            factionDao.insert(faction)
            faction = Faction(0, "Imperial Fists", "Adeptus Astartes", "Anvil Siege Force")
            factionDao.insert(faction)
            faction = Faction(0, "Dark Angels", "Adeptus Astartes", "Unforgiven Task Force")
            factionDao.insert(faction)
            faction = Faction(0, "Blood Angels", "Adeptus Astartes", "Sons of Sanguinius")
            factionDao.insert(faction)
            faction = Faction(0, "Salamanders", "Adeptus Astartes", "Firestorm Assault Force")
            factionDao.insert(faction)
            faction = Faction(0, "Iron Hands", "Adeptus Astartes", "Ironstorm Spearhead")
            factionDao.insert(faction)
            faction = Faction(0, "Space Wolves", "Adeptus Astartes", "Champions of Russ")
            factionDao.insert(faction)
            faction = Faction(0, "White Scars", "Adeptus Astartes", "Stormlance Task Force")
            factionDao.insert(faction)
            faction = Faction(0, "Raven Guard", "Adeptus Astartes", "Vanguard Spearhead")
            factionDao.insert(faction)
            faction = Faction(0, "Deathwatch", "Adeptus Astartes", "Black Spear Task Force")
            factionDao.insert(faction)
            faction = Faction(0, "Space Marines", "Adeptus Astartes", "1st Company Task Force")
            factionDao.insert(faction)
            faction = Faction(0, "Astra Militarum", "Imperium", "Combined Regiment")
            factionDao.insert(faction)
            faction = Faction(0, "Adepta Sororitas", "Imperium", "Hallowed Martyrs")
            factionDao.insert(faction)
            faction = Faction(0, "Adeptus Custodes", "Imperium", "Shield Host")
            factionDao.insert(faction)
            faction = Faction(
                0,
                "Adeptus Mechanicus",
                "Imperium",
                "Cohort Cybernetica, Data-psalm Conclave, Explorator Maniple, Rad Zone Corps, Skitarii Hunter Cohort"
            )
            factionDao.insert(faction)
            faction = Faction(0, "Grey Knights", "Imperium", "Teleport Strike Force")
            factionDao.insert(faction)
            faction = Faction(0, "Imperial Knights", "Imperium", "Noble Lance")
            factionDao.insert(faction)
            faction = Faction(0, "Chaos Space Marines", "Chaos", "Slaves to Darkness")
            factionDao.insert(faction)
            faction = Faction(0, "Death Guard", "Chaos", "Plague Company")
            factionDao.insert(faction)
            faction = Faction(0, "Thousand Sons", "Chaos", "Cult of Magic")
            factionDao.insert(faction)
            faction = Faction(0, "World Eaters", "Chaos", "Berzerker Warband")
            factionDao.insert(faction)
            faction = Faction(0, "Chaos Daemons", "Chaos", "Daemonic Incursion")
            factionDao.insert(faction)
            faction = Faction(0, "Chaos Knights", "Chaos", "Traitoris Lance")
            factionDao.insert(faction)
            faction = Faction(
                0,
                "Necrons",
                "Xenos",
                "Annihilation Legion, Awakened Dynasty, Canoptek Court, Hypercrypt Legion, Obeisance Phalanx"
            )
            factionDao.insert(faction)
            faction = Faction(0, "Orks", "Xenos", "Waaagh! Tribe")
            factionDao.insert(faction)
            faction = Faction(0, "T’au Empire", "Xenos", "Kauyon")
            factionDao.insert(faction)
            faction = Faction(0, "Leagues of Votann", "Xenos", "Oathband")
            factionDao.insert(faction)
            faction = Faction(
                0,
                "Tyranids",
                "Xenos",
                "Assimilation Swarm, Crusher Stampede, Invasion Fleet, Synaptic Nexus, Unending Swarm, Vanguard Onslaught"
            )
            factionDao.insert(faction)
            faction = Faction(0, "Genestealer Cults", "Xenos", "Ascension Day")
            factionDao.insert(faction)
            faction = Faction(0, "Craftworlds", "Aeldari", "Battle Host")
            factionDao.insert(faction)
            faction = Faction(0, "Drukhari", "Aeldari", "Realspace Raiders, Skysplinter Assault")
            factionDao.insert(faction)
            faction = Faction(0, "Ynnari", "Aeldari", "Battle Host")
            factionDao.insert(faction)
        }

        suspend fun populateMissions(missionDao: MissionDao) {
            var mission = Mission(0, "Take and Hold", "Hold 3")
            missionDao.insert(mission)
            mission = Mission(0, "Scorched Earth", "Burn Objectives")
            missionDao.insert(mission)
            mission = Mission(0, "Purge the Foe", "Kill More")
            missionDao.insert(mission)
            mission = Mission(0, "Sites of Power", "Empower Objectives")
            missionDao.insert(mission)
            mission = Mission(0, "The Ritual", "Create Objectives")
            missionDao.insert(mission)
            mission = Mission(0, "Priority Targets", "Hold 2")
            missionDao.insert(mission)
            mission = Mission(0, "Supply Drop", "Disappearing No Mans Land")
            missionDao.insert(mission)
            mission = Mission(0, "Deploy Servo-Skulls", "Moving Objectives")
            missionDao.insert(mission)
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