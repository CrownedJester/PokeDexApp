package com.crownedjester.soft.pokedexapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.crownedjester.soft.pokedexapp.presentation.dashboard_screen.PokemonDashboardScreen
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.PokeDexAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<PokemonViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeDexAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val pokemons = viewModel.pokemonPagingFlow.collectAsLazyPagingItems()

                    PokemonDashboardScreen(pokemons = pokemons, modifier = Modifier.fillMaxSize()) {
                        //on click
                    }

                }
            }
        }
    }
}