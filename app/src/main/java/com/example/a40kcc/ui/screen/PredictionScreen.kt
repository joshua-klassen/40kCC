package com.example.a40kcc.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.a40kcc.data.model.PredictionViewModel
import com.example.a40kcc.data.`object`.Prediction

@Composable
fun PredictionScreen(predictionViewModel: PredictionViewModel, onBackClick: () -> Unit) {
    Column {
        Button(onClick = onBackClick) {
            Column {
                Text("Back")
            }
        }
        val predictions: List<Prediction>? =
            predictionViewModel.allPredictions.observeAsState().value

        if (predictions != null) {
            PredictionScreen(predictions)
        }
    }
}

@Composable
fun PredictionScreen(predictions: List<Prediction>) {
    println(predictions.size.toString())
}