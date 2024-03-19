package com.example.a40kcc.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.a40kcc.data.model.MissionViewModel
import com.example.a40kcc.data.`object`.Mission

@Composable
fun MissionScreen(missionViewModel: MissionViewModel, onBackClick: () -> Unit) {
    Column {
        Button(onClick = onBackClick) {
            Column {
                Text("Back")
            }
        }
        val missions: List<Mission>? = missionViewModel.allMissions.observeAsState().value

        if (missions != null) {
            MissionScreen(missions)
        }
    }
}

@Composable
fun MissionScreen(missions: List<Mission>) {
    println(missions.size.toString())
}