package com.crownedjester.soft.pokedexapp.presentation.util

sealed class Screen(val title: String, val route: String) {

    object PokemonDashboardScreen : Screen(title = "Pokemon Dashboard", route = "pokemon_dashboard")

    object PokemonDetailScreen : Screen(title = "Pokemon Detail", route = "pokemon_detail") {

        const val POKEMON_DATA_KEY = "pokemon_data"
    }

}
