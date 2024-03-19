package com.example.a40kcc.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.a40kcc.data.model.TeamViewModel
import com.example.a40kcc.data.`object`.Team

@Composable
fun TeamScreen(teamViewModel: TeamViewModel, onBackClick: () -> Unit) {
    Column {
        Button(onClick = onBackClick) {
            Column {
                Text("Back")
            }
        }
        val teams: List<Team>? = teamViewModel.allTeams.observeAsState().value

        if (teams != null) {
            TeamScreen(teams)
        }
    }
}

@Composable
fun TeamScreen(teams: List<Team>) {
    println(teams.size.toString())
}