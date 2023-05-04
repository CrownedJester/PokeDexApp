package com.crownedjester.soft.pokedexapp.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.crownedjester.soft.pokedexapp.R
import com.crownedjester.soft.pokedexapp.presentation.dashboard_screen.PokemonDashboardScreen
import com.crownedjester.soft.pokedexapp.presentation.pokemon_detail_screen.PokemonDetailScreen
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.PokeDexAppTheme
import com.crownedjester.soft.pokedexapp.presentation.util.Screen
import com.crownedjester.soft.pokedexapp.presentation.util.Screen.PokemonDetailScreen.POKEMON_DATA_KEY
import com.crownedjester.soft.pokedexapp.presentation.util.UiEvent
import com.crownedjester.soft.pokedexapp.util.converters.PokemonConverter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.launch
import kotlin.system.exitProcess

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
                ) { //surface

                    val pokemons = viewModel.pokemonPagingFlow.collectAsLazyPagingItems()
                    val navController = rememberNavController()

                    setupBackPressedCallback(navController)

                    LaunchedEffect(key1 = true) {
                        viewModel.uiEvent
                            .conflate()
                            .collectLatest { uiEvent ->
                                handleUiEvents(navController, uiEvent)
                            }
                    }


                    NavHost(
                        navController = navController,
                        startDestination = Screen.PokemonDashboardScreen.route,
                        builder = {
                            composable(route = Screen.PokemonDashboardScreen.route) {
                                PokemonDashboardScreen(
                                    pokemons = pokemons,
                                    modifier = Modifier.fillMaxSize()
                                )

                            }

                            composable(
                                route = Screen.PokemonDetailScreen.route + "?{$POKEMON_DATA_KEY}",
                                arguments = listOf(
                                    navArgument(POKEMON_DATA_KEY) {
                                        type = NavType.StringType
                                    }
                                )
                            ) { backStack ->
                                backStack.arguments?.getString(POKEMON_DATA_KEY)
                                    ?.let { pokemonJson ->
                                        val pokemon =
                                            PokemonConverter.decodeFromString(pokemonJson)!!
                                        PokemonDetailScreen(
                                            pokemon,
                                            modifier = Modifier,
                                        )

                                    }
                            }

                        })
                }
            }
        }

    }


    private fun setupBackPressedCallback(navController: NavController) {

        val callback = object : OnBackPressedCallback(true) {

            var backPressCallCount = 0

            override fun handleOnBackPressed() {

                if (navController.currentBackStackEntry!!.destination.route ==
                    Screen.PokemonDashboardScreen.route
                ) { //check current route
                    backPressCallCount++

                    lifecycleScope.launch {
                        delay(1500L)
                        backPressCallCount = 0
                    }

                    if (backPressCallCount < 2) {
                        viewModel.sendEvent(
                            UiEvent.ShowToast(
                                getString(R.string.exit_message)
                            )
                        )

                    } else {
                        finish()
                        exitProcess(0)
                    }
                } else {
                    viewModel.sendEvent(UiEvent.NavigateUp)
                }
            }
        }
        onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun handleUiEvents(navController: NavController, uiEvent: UiEvent) {

        val log = "UiEventHandler"

        when (uiEvent) {
            UiEvent.IDLE -> {
                Log.i(log, "IDLE event send")
            }

            is UiEvent.Navigate -> {
                Log.i(log, "Navigate event send")

                navController.navigate(
                    route = uiEvent.route
                )
            }

            UiEvent.NavigateUp -> {
                Log.i(log, "NavigateUp event send")
                navController.popBackStack()
                navController.navigateUp()
            }

            is UiEvent.ShowToast -> {
                Log.i("UiEventHandler", "ShowToast event send")
                Toast.makeText(
                    this@MainActivity,
                    uiEvent.message,
                    uiEvent.length
                ).show()
            }
        }
    }
}




