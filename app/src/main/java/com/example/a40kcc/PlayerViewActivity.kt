package com.example.a40kcc

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a40kcc.data.adapter.PlayerListAdapter
import com.example.a40kcc.data.model.PlayerViewModel
import com.example.a40kcc.data.model.PlayerViewModelFactory
import com.example.a40kcc.data.`object`.Player
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PlayerViewActivity : AppCompatActivity() {
    private val playerViewModel: PlayerViewModel by viewModels {
        PlayerViewModelFactory((application as Application40kCC).player)
    }

    private val playerAddActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.getStringExtra(PlayerAddActivity.EXTRA_REPLY)?.let {
                    val player = Player(0, it, "null", 1)
                    playerViewModel.insert(player)
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "Error",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_data_object)

        val playerView = findViewById<RecyclerView>(R.id.view_data_object)
        val playerAdapter = PlayerListAdapter()
        playerView.adapter = playerAdapter
        playerView.layoutManager = LinearLayoutManager(this)

        playerViewModel.allPlayers.observe(this, Observer { players ->
            players?.let { playerAdapter.submitList(it) }
        })

        val addPlayer = findViewById<FloatingActionButton>(R.id.add_button)
        addPlayer.setOnClickListener {
            playerAddActivity.launch(
                Intent(this@PlayerViewActivity, PlayerAddActivity::class.java)
            )
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.PlayerView.REPLY"
    }
}