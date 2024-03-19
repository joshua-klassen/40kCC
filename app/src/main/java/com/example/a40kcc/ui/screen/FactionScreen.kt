package com.example.a40kcc.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.a40kcc.data.model.FactionViewModel
import com.example.a40kcc.data.`object`.Faction

@Composable
fun FactionScreen(factionViewModel: FactionViewModel, onBackClick: () -> Unit) {
    Column {
        Button(onClick = onBackClick) {
            Column {
                Text("Back")
            }
        }
        val factions: List<Faction>? = factionViewModel.allFactions.observeAsState().value

        if (factions != null) {
            FactionScreen(factions)
        }
    }
}

@Composable
fun FactionScreen(factions: List<Faction>) {
    Row(modifier = Modifier.padding(all = 2.dp)) {
        Column {
            Text(
                "ID",
                color = MaterialTheme.colorScheme.primary
            )
        }
        Column {
            Text(
                "Name",
                color = MaterialTheme.colorScheme.secondary
            )
        }
        Column {
            Text(
                "Super Faction",
                color = MaterialTheme.colorScheme.secondary
            )
        }
        Column {
            Text(
                "Detachments",
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }

    LazyColumn {
        items(factions) { faction: Faction ->
            Row(modifier = Modifier.padding(all = 1.dp)) {
                AddFaction(faction)
            }
        }
    }
}

@Composable
fun AddFaction(
    faction: Faction
) {
    Column {
        Text(
            faction.factionID.toString(),
            style = MaterialTheme.typography.titleSmall
        )
    }
    Column {
        Text(
            faction.name,
            style = MaterialTheme.typography.titleSmall
        )
    }
    Column {
        Text(
            faction.superFaction,
            style = MaterialTheme.typography.titleSmall
        )
    }
    Column {
        Text(
            faction.detachments.toString(),
            style = MaterialTheme.typography.titleSmall
        )
    }
}