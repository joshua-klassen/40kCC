package com.example.a40kcc.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.a40kcc.R
import com.example.a40kcc.ui.utilities.MAIN_ROUTES

@Composable
fun HomeScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val cardSideMargin = dimensionResource(id = R.dimen.card_side_margin)
    val cardBottomMargin = dimensionResource(id = R.dimen.card_bottom_margin)

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = modifier
    ) {
        items(MAIN_ROUTES.keys.toList()) { key ->
            HomeScreenCard(
                stringResource(id = MAIN_ROUTES[key]!!["Text"]!!.toInt()),
                painterResource(MAIN_ROUTES[key]!!["Image"]!!.toInt()),
                { navController.navigate(key) },
                modifier.padding(
                    start = cardSideMargin,
                    end = cardSideMargin,
                    bottom = cardBottomMargin,
                    top = cardBottomMargin
                )
            )
        }
    }
}

@Composable
fun HomeScreenCard(
    cardText: String,
    cardImage: Painter,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier.wrapContentWidth()
    ) {
        Column {
            Image(
                painter = cardImage,
                contentDescription = "Open $cardText",
                contentScale = ContentScale.Inside
            )
            Text(
                cardText,
                modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}