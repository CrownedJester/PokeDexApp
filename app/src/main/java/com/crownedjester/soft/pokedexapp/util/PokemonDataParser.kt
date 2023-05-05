package com.crownedjester.soft.pokedexapp.util

const val ID_PARSER_ERROR_RESPONSE = -1
const val prefix = "https://pokeapi.co/api/v2/pokemon/"
fun String.parseId(): Int =
    if (isEmpty() || isBlank() || !contains(prefix))
        ID_PARSER_ERROR_RESPONSE
    else
        removePrefix(prefix).removeSuffix("/").toInt()
