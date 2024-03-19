package com.example.a40kcc.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.a40kcc.data.model.PlayerViewModel
import com.example.a40kcc.data.`object`.Player

@Composable
fun PlayerScreen(playerViewModel: PlayerViewModel, onBackClick: () -> Unit) {
    Column {
        Button(onClick = onBackClick) {
            Column {
                Text("Back")
            }
        }
        val players: List<Player>? = playerViewModel.allPlayers.observeAsState().value

        if (players != null) {
            PlayerScreen(players)
        }
    }
}

@Composable
fun PlayerScreen(players: List<Player>) {
    println(players.size.toString())
}