package com.example.a40kcc.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.a40kcc.data.`object`.Outcome
import com.example.a40kcc.ui.utilities.OUTCOME_VIEW_MODEL

@Composable
fun OutcomeScreen(onBackClick: () -> Unit) {
    Column {
        Button(onClick = onBackClick) {
            Column {
                Text("Back")
            }
        }
        val outcomes: List<Outcome>? = OUTCOME_VIEW_MODEL.allOutcomes.observeAsState().value

        if (outcomes != null) {
            OutcomeScreen(outcomes)
        }
    }
}

@Composable
fun OutcomeScreen(outcomes: List<Outcome>) {
    println(outcomes.size.toString())
}