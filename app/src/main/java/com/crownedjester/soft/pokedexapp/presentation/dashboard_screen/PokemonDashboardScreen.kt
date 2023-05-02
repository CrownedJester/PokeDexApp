package com.crownedjester.soft.pokedexapp.presentation.dashboard_screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.crownedjester.soft.pokedexapp.domain.model.Pokemon
import com.crownedjester.soft.pokedexapp.presentation.dashboard_screen.components.PokemonItem
import com.crownedjester.soft.pokedexapp.presentation.util.LoadingScreen

@Composable
fun PokemonDashboardScreen(
    pokemons: LazyPagingItems<Pokemon>,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = pokemons.loadState) {
        when (pokemons.loadState.refresh) {
            is LoadState.Error -> {
                Toast.makeText(
                    context,
                    (pokemons.loadState.refresh as LoadState.Error).error.message,
                    Toast.LENGTH_LONG
                ).show()
            }

            is LoadState.Loading -> {

            }

            else -> {}
        }
    }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(itemsArrangement)) {

        Text(
            modifier = Modifier.padding(start = headerPadding, top = headerPadding),
            text = "Pokes",
            fontSize = headerFontSize,
            fontWeight = FontWeight.Bold
        )


        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(lazyColumnPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(listItemsArrangement)
        ) {
            items(pokemons) { pokemon ->
                if (pokemon != null) {
                    PokemonItem(
                        pokemon = pokemon,
                        modifier = Modifier.clickable {
                            onClick()
                        }
                    )
                }
            }
            item {
                LoadingScreen(message = "")
            }
        }
    }


}

private val listItemsArrangement = 12.dp
private val lazyColumnPadding = 8.dp
private val itemsArrangement = 14.dp
private val headerPadding = 28.dp
private val headerFontSize = 28.sp