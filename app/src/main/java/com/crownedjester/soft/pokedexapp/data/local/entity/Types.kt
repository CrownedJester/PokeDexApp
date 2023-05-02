package com.crownedjester.soft.pokedexapp.data.local.entity

import com.crownedjester.soft.pokedexapp.presentation.ui.theme.TypeBug
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.TypeDark
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.TypeDragon
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.TypeElectric
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.TypeFairy
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.TypeFighting
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.TypeFire
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.TypeFlying
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.TypeGhost
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.TypeGrass
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.TypeGround
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.TypeIce
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.TypeNormal
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.TypePoison
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.TypePsychic
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.TypeRock
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.TypeSteel
import com.crownedjester.soft.pokedexapp.presentation.ui.theme.TypeWater

data class Types(
    val types: List<String>?
) {
    companion object {

        val typesWithColors = mapOf(
            "normal" to TypeNormal,
            "fire" to TypeFire,
            "water" to TypeWater,
            "electric" to TypeElectric,
            "grass" to TypeGrass,
            "ice" to TypeIce,
            "fighting" to TypeFighting,
            "poison" to TypePoison,
            "ground" to TypeGround,
            "flying" to TypeFlying,
            "psychic" to TypePsychic,
            "bug" to TypeBug,
            "rock" to TypeRock,
            "ghost" to TypeGhost,
            "dragon" to TypeDragon,
            "dark" to TypeDark,
            "steel" to TypeSteel,
            "fairy" to TypeFairy
        )

    }
}