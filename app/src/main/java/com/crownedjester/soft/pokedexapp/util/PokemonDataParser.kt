package com.crownedjester.soft.pokedexapp.util

fun String.parseId(): Int =
    removePrefix("https://pokeapi.co/api/v2/pokemon/").removeSuffix("/").toInt()
