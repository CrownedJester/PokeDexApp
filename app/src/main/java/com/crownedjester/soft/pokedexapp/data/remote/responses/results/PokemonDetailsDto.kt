package com.crownedjester.soft.pokedexapp.data.remote.responses.results

import com.crownedjester.soft.pokedexapp.data.remote.responses.abilities.AbilitiesDto
import com.crownedjester.soft.pokedexapp.data.remote.responses.sprites.SpritesDto
import com.crownedjester.soft.pokedexapp.data.remote.responses.stats.StatsDto
import com.crownedjester.soft.pokedexapp.data.remote.responses.type.TypeDto
import com.squareup.moshi.Json

data class PokemonDetailsDto(
    val id: Int,
    val height: Int,
    val weight: Int,
    @Json(name = "types")
    val typesDto: List<TypeDto>,
    @Json(name = "stats")
    val statsDto: List<StatsDto>,
    @Json(name = "abilities")
    val abilitiesDto: List<AbilitiesDto>,
    val sprites: SpritesDto
)