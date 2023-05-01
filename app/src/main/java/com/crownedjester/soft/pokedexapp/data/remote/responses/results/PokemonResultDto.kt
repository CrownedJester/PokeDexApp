package com.crownedjester.soft.pokedexapp.data.remote.responses.results

data class PokemonResultDto(
    val count: Int,
    val results: List<PokemonListItemDto>
)
