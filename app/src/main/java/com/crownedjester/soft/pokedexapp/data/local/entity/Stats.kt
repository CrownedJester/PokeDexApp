package com.crownedjester.soft.pokedexapp.data.local.entity

import com.crownedjester.soft.pokedexapp.presentation.ui.theme.AtkColor
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.DefColor
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.HPColor
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.SpAtkColor
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.SpDefColor
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.SpdColor

data class Stats(
    val stats: List<Stat>
)

data class Stat(
    val baseStat: Int,
    val effort: Int,
    val name: String
) {
    companion object {

        val statsWithColors = mapOf(
            "hp" to HPColor,
            "attack" to AtkColor,
            "defense" to DefColor,
            "special-attack" to SpAtkColor,
            "special-defense" to SpDefColor,
            "speed" to SpdColor
        )

    }
}


