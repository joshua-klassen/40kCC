package com.example.a40kcc

import android.app.Application
import com.example.a40kcc.data.Application40kCCDatabase
import com.example.a40kcc.data.repository.PlayerRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class Application40kCC : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { Application40kCCDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { PlayerRepository(database.playerDao()) }
}