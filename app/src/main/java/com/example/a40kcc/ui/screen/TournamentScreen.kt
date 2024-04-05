package com.example.a40kcc.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.a40kcc.data.`object`.Tournament
import com.example.a40kcc.ui.utilities.TOURNAMENT_VIEW_MODEL

@Composable
fun TournamentScreen(onBackClick: () -> Unit) {
    Column {
        Button(onClick = onBackClick) {
            Column {
                Text("Back")
            }
        }
        val tournaments: List<Tournament>? =
            TOURNAMENT_VIEW_MODEL.allTournaments.observeAsState().value

        if (tournaments != null) {
            TournamentScreen(tournaments)
        }
    }
}

@Composable
fun TournamentScreen(tournaments: List<Tournament>) {
    println(tournaments.size.toString())
}