package com.crownedjester.soft.pokedexapp.extensions

fun String.makeFirstUppercase(): String =
    if (isEmpty() || isBlank())
        ""
    else
        first().uppercase() + subSequence(1, length)