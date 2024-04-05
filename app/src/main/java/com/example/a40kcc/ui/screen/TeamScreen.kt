package com.example.a40kcc.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.a40kcc.data.`object`.Team
import com.example.a40kcc.ui.utilities.TEAM_VIEW_MODEL

@Composable
fun TeamScreen(onBackClick: () -> Unit) {
    Column {
        Button(onClick = onBackClick) {
            Column {
                Text("Back")
            }
        }
        val teams: List<Team>? = TEAM_VIEW_MODEL.allTeams.observeAsState().value

        if (teams != null) {
            TeamScreen(teams)
        }
    }
}

@Composable
fun TeamScreen(teams: List<Team>) {
    println(teams.size.toString())
}