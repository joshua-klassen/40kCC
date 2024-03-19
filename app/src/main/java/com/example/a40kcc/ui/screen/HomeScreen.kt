package com.example.a40kcc.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.a40kcc.R

@Composable
fun HomeScreen(navController: NavHostController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        item {
            Card(
                onClick = { navController.navigate("deployments") }) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.factions),
                        contentDescription = "Open Deployments Page",
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        "Deployment",
                        Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
        item {
            Card(
                onClick = { navController.navigate("factions") }) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.factions),
                        contentDescription = "Open Factions Page",
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        "Factions",
                        Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
        item {
            Card(
                onClick = { navController.navigate("games") }) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.factions),
                        contentDescription = "Open Games Page",
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        "Games",
                        Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
        item {
            Card(
                onClick = { navController.navigate("missions") }) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.factions),
                        contentDescription = "Open Missions Page",
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        "Missions",
                        Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
        item {
            Card(
                onClick = { navController.navigate("objectives") }) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.factions),
                        contentDescription = "Open Objectives Page",
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        "Objectives",
                        Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
        item {
            Card(
                onClick = { navController.navigate("outcomes") }) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.factions),
                        contentDescription = "Open Outcomes Page",
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        "Outcomes",
                        Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
        item {
            Card(
                onClick = { navController.navigate("players") }) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.factions),
                        contentDescription = "Open Players Page",
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        "Players",
                        Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
        item {
            Card(
                onClick = { navController.navigate("predictions") }) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.factions),
                        contentDescription = "Open Predictions Page",
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        "Predictions",
                        Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
        item {
            Card(
                onClick = { navController.navigate("rounds") }) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.factions),
                        contentDescription = "Open Rounds Page",
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        "Rounds",
                        Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
        item {
            Card(
                onClick = { navController.navigate("teams") }) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.factions),
                        contentDescription = "Open Teams Page",
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        "Teams",
                        Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
        item {
            Card(
                onClick = { navController.navigate("tournaments") }) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.factions),
                        contentDescription = "Open Tournaments Page",
                        contentScale = ContentScale.Crop,
                    )
                    Text(
                        "Tournaments",
                        Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}