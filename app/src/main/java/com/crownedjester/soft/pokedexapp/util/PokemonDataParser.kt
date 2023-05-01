package com.crownedjester.soft.pokedexapp.util

const val ID_PARSER_ERROR_RESPONSE = -1
fun String.parseId(): Int =
    if (isEmpty() || isBlank())
        ID_PARSER_ERROR_RESPONSE
    else
        removePrefix("https://pokeapi.co/api/v2/pokemon/").removeSuffix("/").toInt()
