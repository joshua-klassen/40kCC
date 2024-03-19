package com.example.a40kcc.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.a40kcc.data.model.GameViewModel
import com.example.a40kcc.data.`object`.Game

@Composable
fun GameScreen(gameViewModel: GameViewModel, onBackClick: () -> Unit) {
    Column {
        Button(onClick = onBackClick) {
            Column {
                Text("Back")
            }
        }
        val games: List<Game>? = gameViewModel.allGames.observeAsState().value

        if (games != null) {
            GameScreen(games)
        }
    }
}

@Composable
fun GameScreen(games: List<Game>) {
    println(games.size.toString())
}