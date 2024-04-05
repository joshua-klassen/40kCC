package com.example.a40kcc.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.a40kcc.data.`object`.Round
import com.example.a40kcc.ui.utilities.ROUND_VIEW_MODEL

@Composable
fun RoundScreen(onBackClick: () -> Unit) {
    Column {
        Button(onClick = onBackClick) {
            Column {
                Text("Back")
            }
        }
        val rounds: List<Round>? = ROUND_VIEW_MODEL.allRounds.observeAsState().value

        if (rounds != null) {
            RoundScreen(rounds)
        }
    }
}

@Composable
fun RoundScreen(rounds: List<Round>) {
    println(rounds.size.toString())
}