package com.crownedjester.soft.pokedexapp.presentation.dashboard_screen

import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.crownedjester.soft.pokedexapp.R
import com.crownedjester.soft.pokedexapp.domain.model.Pokemon
import com.crownedjester.soft.pokedexapp.presentation.PokemonViewModel
import com.crownedjester.soft.pokedexapp.presentation.common_components.LoadingScreen
import com.crownedjester.soft.pokedexapp.presentation.dashboard_screen.components.PokemonItem
import com.crownedjester.soft.pokedexapp.presentation.util.Screen
import com.crownedjester.soft.pokedexapp.presentation.util.UiEvent
import com.crownedjester.soft.pokedexapp.util.converters.PokemonConverter

@Composable
fun PokemonDashboardScreen(
    pokemons: LazyPagingItems<Pokemon>,
    modifier: Modifier = Modifier,
) {

    val context = LocalContext.current
    val viewModel = viewModel<PokemonViewModel>(context as ComponentActivity)
    var isDialogShown by remember { mutableStateOf(false) }

    ClearCacheDialog(
        isShown = isDialogShown,
        onDismiss = { isDialogShown = false },
        onConfirm = {
            viewModel.clearAll()
            isDialogShown = false
        }
    )


    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(itemsArrangement)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = headerPadding, end = headerPadding, top = headerPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Pokes",
                fontSize = headerFontSize,
                fontWeight = FontWeight.Bold,
            )

            IconButton(
                onClick = { isDialogShown = true },
                content = {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.clear_cash),
                    )
                })
        }


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
                            val encodedPokemon = PokemonConverter.encodeToString(pokemon)
                            viewModel.sendEvent(
                                UiEvent.Navigate(
                                    Screen.PokemonDetailScreen.route + "?$encodedPokemon"
                                )
                            )
                        }
                    )
                }
            }
            item {
                LoadingScreen(message = "")
            }
            if (pokemons.itemCount == 0) {
                item {
                    Text(text = stringResource(R.string.empty_list_message))
                }
            }
        }
    }


}

@Composable
fun ClearCacheDialog(
    isShown: Boolean,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {

    if (isShown) {
        AlertDialog(
            modifier = modifier,
            title = { Text(text = stringResource(R.string.dialog_title)) },
            text = { Text(text = stringResource(R.string.dialog_message)) },
            onDismissRequest = onDismiss,
            confirmButton = {
                Button(
                    onClick = onConfirm,
                    content = { Text(text = stringResource(R.string.dialog_confirm)) })
            },
            dismissButton = {
                Button(
                    onClick = onDismiss,
                    content = { Text(text = stringResource(R.string.dialog_cancel)) })

            }
        )
    }

}

private val listItemsArrangement = 12.dp
private val lazyColumnPadding = 8.dp
private val itemsArrangement = 14.dp
private val headerPadding = 28.dp
private val headerFontSize = 28.sp