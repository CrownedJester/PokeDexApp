package com.crownedjester.soft.pokedexapp.domain.repository.remote

import com.crownedjester.soft.pokedexapp.data.remote.responses.results.PokemonDetailsDto
import com.crownedjester.soft.pokedexapp.data.remote.responses.results.PokemonResultDto

interface RemoteSourceRepository {

    suspend fun retrievePokemonList(
        limit: Int,
        offset: Int
    ): PokemonResultDto

    suspend fun retrievePokemonDetails(
        id: Int
    ): PokemonDetailsDto

}