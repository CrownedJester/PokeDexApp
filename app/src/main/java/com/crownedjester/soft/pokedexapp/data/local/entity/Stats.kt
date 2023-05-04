package com.crownedjester.soft.pokedexapp.data.local.entity

import com.crownedjester.soft.pokedexapp.presentation.ui.theme.AtkColor
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.DefColor
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.HPColor
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.SpAtkColor
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.SpDefColor
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.SpdColor

data class Stats(
    val data: List<Stat>
)

data class Stat(
    val baseStat: Int,
    val effort: Int,
    val name: String
) {

    companion object {

        const val HP = "hp"
        const val ATK = "attack"
        const val DEF = "defense"
        const val SP_ATK = "special-attack"
        const val SP_DEF = "special-defense"
        const val SPEED = "speed"

        val statsWithColors = mapOf(
            HP to HPColor,
            ATK to AtkColor,
            DEF to DefColor,
            SP_ATK to SpAtkColor,
            SP_DEF to SpDefColor,
            SPEED to SpdColor
        )

        const val MAX_STAT_VALUE = 150f

    }
}