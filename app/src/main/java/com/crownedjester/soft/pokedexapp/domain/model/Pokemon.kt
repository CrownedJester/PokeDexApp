package com.crownedjester.soft.pokedexapp.domain.model

import com.crownedjester.soft.pokedexapp.data.local.entity.Abilities
import com.crownedjester.soft.pokedexapp.data.local.entity.Stats
import com.crownedjester.soft.pokedexapp.data.local.entity.Types

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val type: Types,
    val stats: Stats,
    val abilities: Abilities,
    val art: String
)
