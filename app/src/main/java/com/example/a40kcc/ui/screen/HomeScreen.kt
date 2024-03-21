package com.example.a40kcc.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.a40kcc.R

@Composable
fun HomeScreen(navController: NavHostController) {
    val cardSideMargin = dimensionResource(id = R.dimen.card_side_margin)
    val cardBottomMargin = dimensionResource(id = R.dimen.card_bottom_margin)
    val cardImageHeight = dimensionResource(id = R.dimen.card_image_height)
    val marginNormal = dimensionResource(id = R.dimen.margin_normal)

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = marginNormal, vertical = marginNormal)
    ) {
        item {
            ElevatedCard(
                onClick = { navController.navigate("deployments") },
                modifier = Modifier.padding(
                    start = cardSideMargin,
                    end = cardSideMargin,
                    bottom = cardBottomMargin,
                    top = cardBottomMargin
                )
            ) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.icon_factions),
                        contentDescription = "Open Deployments Page",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth().height(cardImageHeight)
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
                onClick = { navController.navigate("factions") },
                modifier = Modifier.padding(
                    start = cardSideMargin,
                    end = cardSideMargin,
                    bottom = cardBottomMargin,
                    top = cardBottomMargin
                )
            ) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.icon_factions),
                        contentDescription = "Open Factions Page",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth().height(cardImageHeight)
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
                onClick = { navController.navigate("games") },
                modifier = Modifier.padding(
                    start = cardSideMargin,
                    end = cardSideMargin,
                    bottom = cardBottomMargin,
                    top = cardBottomMargin
                )
            ) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.icon_factions),
                        contentDescription = "Open Games Page",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth().height(cardImageHeight)
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
                onClick = { navController.navigate("missions") },
                modifier = Modifier.padding(
                    start = cardSideMargin,
                    end = cardSideMargin,
                    bottom = cardBottomMargin,
                    top = cardBottomMargin
                )
            ) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.icon_factions),
                        contentDescription = "Open Missions Page",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth().height(cardImageHeight)
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
                onClick = { navController.navigate("objectives") },
                modifier = Modifier.padding(
                    start = cardSideMargin,
                    end = cardSideMargin,
                    bottom = cardBottomMargin,
                    top = cardBottomMargin
                )
            ) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.icon_factions),
                        contentDescription = "Open Objectives Page",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth().height(cardImageHeight)
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
                onClick = { navController.navigate("outcomes") },
                modifier = Modifier.padding(
                    start = cardSideMargin,
                    end = cardSideMargin,
                    bottom = cardBottomMargin,
                    top = cardBottomMargin
                )
            ) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.icon_factions),
                        contentDescription = "Open Outcomes Page",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth().height(cardImageHeight)
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
                onClick = { navController.navigate("players") },
                modifier = Modifier.padding(
                    start = cardSideMargin,
                    end = cardSideMargin,
                    bottom = cardBottomMargin,
                    top = cardBottomMargin
                )
            ) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.icon_factions),
                        contentDescription = "Open Players Page",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth().height(cardImageHeight)
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
                onClick = { navController.navigate("predictions") },
                modifier = Modifier.padding(
                    start = cardSideMargin,
                    end = cardSideMargin,
                    bottom = cardBottomMargin,
                    top = cardBottomMargin
                )
            ) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.icon_factions),
                        contentDescription = "Open Predictions Page",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth().height(cardImageHeight)
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
                onClick = { navController.navigate("rounds") },
                modifier = Modifier.padding(
                    start = cardSideMargin,
                    end = cardSideMargin,
                    bottom = cardBottomMargin,
                    top = cardBottomMargin
                )
            ) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.icon_factions),
                        contentDescription = "Open Rounds Page",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth().height(cardImageHeight)
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
                onClick = { navController.navigate("teams") },
                modifier = Modifier.padding(
                    start = cardSideMargin,
                    end = cardSideMargin,
                    bottom = cardBottomMargin,
                    top = cardBottomMargin
                )
            ) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.icon_factions),
                        contentDescription = "Open Teams Page",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth().height(cardImageHeight)
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
                onClick = { navController.navigate("tournaments") },
                modifier = Modifier.padding(
                    start = cardSideMargin,
                    end = cardSideMargin,
                    bottom = cardBottomMargin,
                    top = cardBottomMargin
                )
            ) {
                Column {
                    Image(
                        painter = painterResource(R.drawable.icon_factions),
                        contentDescription = "Open Tournaments Page",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth().height(cardImageHeight)
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