package com.example.a40kcc.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.a40kcc.data.model.ObjectiveViewModel
import com.example.a40kcc.data.`object`.Objective

@Composable
fun ObjectiveScreen(objectiveViewModel: ObjectiveViewModel, onBackClick: () -> Unit) {
    Column {
        Button(onClick = onBackClick) {
            Column {
                Text("Back")
            }
        }
        val objectives: List<Objective>? = objectiveViewModel.allObjectives.observeAsState().value

        if (objectives != null) {
            ObjectiveScreen(objectives)
        }
    }
}

@Composable
fun ObjectiveScreen(objectives: List<Objective>) {
    println(objectives.size.toString())
}