package com.crownedjester.soft.pokedexapp.extensions

import com.crownedjester.soft.pokedexapp.data.local.entity.Ability

fun List<Ability>.convertToString(sep: String = " "): String =
    with(StringBuilder()) {
        this@convertToString.forEachIndexed { i, item ->
            append(item.name + if (item.isHidden) "(hidden)" else "")

            if (i != this@convertToString.size - 1) {
                append("$sep ")
            }

        }

        this.toString()
    }
