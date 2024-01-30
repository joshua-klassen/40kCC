package com.example.a40kcc

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a40kcc.data.Player
import com.example.a40kcc.data.PlayerViewModel
import com.example.a40kcc.data.PlayerViewModelFactory
import com.example.a40kcc.ui.theme._40kCCTheme

class MainActivity : ComponentActivity() {

    private lateinit var minimum: EditText
    private lateinit var maximum: EditText
    private lateinit var average: EditText

    private lateinit var player1Min: EditText
    private lateinit var player1Max: EditText
    private lateinit var player2Min: EditText
    private lateinit var player2Max: EditText
    private lateinit var player3Min: EditText
    private lateinit var player3Max: EditText
    private lateinit var player4Min: EditText
    private lateinit var player4Max: EditText
    private lateinit var player5Min: EditText
    private lateinit var player5Max: EditText
    private lateinit var player6Min: EditText
    private lateinit var player6Max: EditText
    private lateinit var player7Min: EditText
    private lateinit var player7Max: EditText
    private lateinit var player8Min: EditText
    private lateinit var player8Max: EditText

    private val updateOnFocusChange = View.OnFocusChangeListener { _, focus ->
        if (!focus) {
            updateStats()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _40kCCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    setContentView(R.layout.normal)
                    minimum = findViewById(R.id.totalMin)
                    minimum.isEnabled = false
                    minimum.setTextColor(Color.Black.hashCode())
                    minimum.doOnTextChanged { text, _, _, _ ->
                        if ((text.toString().toIntOrNull() ?: 0) < 75) {
                            minimum.setBackgroundColor(Color.Red.hashCode())
                        } else if ((text.toString().toIntOrNull() ?: 0) > 86) {
                            minimum.setBackgroundColor(Color.Green.hashCode())
                        } else {
                            minimum.setBackgroundColor(Color.Yellow.hashCode())
                        }
                    }
                    maximum = findViewById(R.id.totalMax)
                    maximum.isEnabled = false
                    maximum.setTextColor(Color.Black.hashCode())
                    maximum.doOnTextChanged { text, _, _, _ ->
                        if ((text.toString().toIntOrNull() ?: 0) < 75) {
                            maximum.setBackgroundColor(Color.Red.hashCode())
                        } else if ((text.toString().toIntOrNull() ?: 0) > 86) {
                            maximum.setBackgroundColor(Color.Green.hashCode())
                        } else {
                            maximum.setBackgroundColor(Color.Yellow.hashCode())
                        }
                    }
                    average = findViewById(R.id.totalAvg)
                    average.isEnabled = false
                    average.setTextColor(Color.Black.hashCode())
                    average.doOnTextChanged { text, _, _, _ ->
                        if ((text.toString().toIntOrNull() ?: 0) < 75) {
                            average.setBackgroundColor(Color.Red.hashCode())
                        } else if ((text.toString().toIntOrNull() ?: 0) > 86) {
                            average.setBackgroundColor(Color.Green.hashCode())
                        } else {
                            average.setBackgroundColor(Color.Yellow.hashCode())
                        }
                    }

                    player1Min = findViewById(R.id.player1Min)
                    player1Max = findViewById(R.id.player1Max)
                    player2Min = findViewById(R.id.player2Min)
                    player2Max = findViewById(R.id.player2Max)
                    player3Min = findViewById(R.id.player3Min)
                    player3Max = findViewById(R.id.player3Max)
                    player4Min = findViewById(R.id.player4Min)
                    player4Max = findViewById(R.id.player4Max)
                    player5Min = findViewById(R.id.player5Min)
                    player5Max = findViewById(R.id.player5Max)
                    player6Min = findViewById(R.id.player6Min)
                    player6Max = findViewById(R.id.player6Max)
                    player7Min = findViewById(R.id.player7Min)
                    player7Max = findViewById(R.id.player7Max)
                    player8Min = findViewById(R.id.player8Min)
                    player8Max = findViewById(R.id.player8Max)

                    player1Min.onFocusChangeListener = updateOnFocusChange
                    player1Max.onFocusChangeListener = updateOnFocusChange
                    player2Min.onFocusChangeListener = updateOnFocusChange
                    player2Max.onFocusChangeListener = updateOnFocusChange
                    player3Min.onFocusChangeListener = updateOnFocusChange
                    player3Max.onFocusChangeListener = updateOnFocusChange
                    player4Min.onFocusChangeListener = updateOnFocusChange
                    player4Max.onFocusChangeListener = updateOnFocusChange
                    player5Min.onFocusChangeListener = updateOnFocusChange
                    player5Max.onFocusChangeListener = updateOnFocusChange
                    player6Min.onFocusChangeListener = updateOnFocusChange
                    player6Max.onFocusChangeListener = updateOnFocusChange
                    player7Min.onFocusChangeListener = updateOnFocusChange
                    player7Max.onFocusChangeListener = updateOnFocusChange
                    player8Min.onFocusChangeListener = updateOnFocusChange
                    player8Max.onFocusChangeListener = updateOnFocusChange
                }
            }
        }
    }

    private fun updateStats() {
        val totalMin = (player1Min.text.toString().toIntOrNull() ?: 0) +
                (player2Min.text.toString().toIntOrNull() ?: 0) +
                (player3Min.text.toString().toIntOrNull() ?: 0) +
                (player4Min.text.toString().toIntOrNull() ?: 0) +
                (player5Min.text.toString().toIntOrNull() ?: 0) +
                (player6Min.text.toString().toIntOrNull() ?: 0) +
                (player7Min.text.toString().toIntOrNull() ?: 0) +
                (player8Min.text.toString().toIntOrNull() ?: 0)
        val totalMax = (player1Max.text.toString().toIntOrNull() ?: 0) +
                (player2Max.text.toString().toIntOrNull() ?: 0) +
                (player3Max.text.toString().toIntOrNull() ?: 0) +
                (player4Max.text.toString().toIntOrNull() ?: 0) +
                (player5Max.text.toString().toIntOrNull() ?: 0) +
                (player6Max.text.toString().toIntOrNull() ?: 0) +
                (player7Max.text.toString().toIntOrNull() ?: 0) +
                (player8Max.text.toString().toIntOrNull() ?: 0)
        val totalAvg = (totalMax + totalMin) / 2

        minimum.setText(totalMin.toString())
        maximum.setText(totalMax.toString())
        average.setText(totalAvg.toString())
    }
}