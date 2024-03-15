package com.example.a40kcc

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : ComponentActivity() {
    private val playerViewActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode != Activity.RESULT_OK) {
                Toast.makeText(
                    applicationContext,
                    "Error",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    private val factionViewActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode != Activity.RESULT_OK) {
                Toast.makeText(
                    applicationContext,
                    "Error",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.portrait)

        val viewPlayers = findViewById<Button>(R.id.view_players)
        viewPlayers.setOnClickListener {
            playerViewActivity.launch(
                Intent(this@MainActivity, PlayerViewActivity::class.java)
            )
        }

        val viewFactions = findViewById<Button>(R.id.view_factions)
        viewFactions.setOnClickListener {
            factionViewActivity.launch(
                Intent(this@MainActivity, FactionViewActivity::class.java)
            )
        }
    }
}