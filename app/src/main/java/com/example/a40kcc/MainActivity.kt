package com.example.a40kcc

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a40kcc.data.adapter.PlayerListAdapter
import com.example.a40kcc.data.`object`.Player
import com.example.a40kcc.data.model.PlayerViewModel
import com.example.a40kcc.data.model.PlayerViewModelFactory
import com.example.a40kcc.ui.theme._40kCCTheme

class MainActivity : ComponentActivity() {
    private val playerViewModel: PlayerViewModel by viewModels {
        PlayerViewModelFactory((application as Application40kCC).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _40kCCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    setContentView(R.layout.normal)

                    val button = findViewById<Button>(R.id.button)
                    val recyclerView = findViewById<RecyclerView>(R.id.player1Text)
                    val adapter = PlayerListAdapter()
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(this)

                    playerViewModel.allPlayers.observe(this) { players ->
                        // Update the cached copy of the words in the adapter.
                        players.let { adapter.submitList(it) }
                    }

                    button.setOnClickListener { _ ->
                        playerViewModel.insert(Player(0, "dsa", "dsa"))
                    }
                    button.callOnClick()
                }
            }
        }
    }
}