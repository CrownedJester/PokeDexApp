package com.crownedjester.soft.pokedexapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("pokemon_table")
data class PokemonEntity(
    @PrimaryKey
    val id: Int,
    val height: Int,
    val weight: Int,
    val type: Types,
    val stats: Stats,
    val abilities: Abilities,
    val art: String
)
