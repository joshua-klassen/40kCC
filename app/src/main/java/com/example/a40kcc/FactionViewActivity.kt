package com.example.a40kcc

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a40kcc.data.adapter.FactionListAdapter
import com.example.a40kcc.data.model.FactionViewModel
import com.example.a40kcc.data.model.FactionViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FactionViewActivity : AppCompatActivity() {
    private val factionAddActivityRequestCode = 1

    private val factionViewModel: FactionViewModel by viewModels {
        FactionViewModelFactory((application as Application40kCC).faction)
    }

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_data_object)

        val factionView = findViewById<RecyclerView>(R.id.view_data_object)
        val factionAdapter = FactionListAdapter()
        factionView.adapter = factionAdapter
        factionView.layoutManager = LinearLayoutManager(this)

        factionViewModel.allFactions.observe(this, Observer { factions ->
            factions?.let { factionAdapter.submitList(it) }
        })

        findViewById<FloatingActionButton>(R.id.add_button).hide()
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.FactionView.REPLY"
    }
}