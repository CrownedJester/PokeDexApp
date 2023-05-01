package com.crownedjester.soft.pokedexapp.util

object PokemonDataParser {

    fun parseId(url: String): Int =
        url.removePrefix("https://pokeapi.co/api/v2/pokemon/").removeSuffix("/").toInt()


}