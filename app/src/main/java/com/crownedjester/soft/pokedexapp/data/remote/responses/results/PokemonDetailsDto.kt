package com.crownedjester.soft.pokedexapp.data.remote.responses.results

import com.crownedjester.soft.pokedexapp.data.remote.responses.abilities.AbilitiesDto
import com.crownedjester.soft.pokedexapp.data.remote.responses.sprites.SpritesDto
import com.crownedjester.soft.pokedexapp.data.remote.responses.stats.StatsDto
import com.crownedjester.soft.pokedexapp.data.remote.responses.type.TypeDto

data class PokemonDetailsDto(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<TypeDto>,
    val stats: List<StatsDto>,
    val abilities: List<AbilitiesDto>,
    val sprites: SpritesDto
)