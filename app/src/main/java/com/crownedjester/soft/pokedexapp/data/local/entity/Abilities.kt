package com.crownedjester.soft.pokedexapp.data.local.entity

data class Abilities(
    val abilities: List<Ability>
)

data class Ability(
    val name: String,
    val isHidden: Boolean
)

