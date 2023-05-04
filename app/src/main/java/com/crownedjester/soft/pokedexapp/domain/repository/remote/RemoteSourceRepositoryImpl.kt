package com.crownedjester.soft.pokedexapp.domain.repository.remote

import com.crownedjester.soft.pokedexapp.data.remote.PokeApi
import com.crownedjester.soft.pokedexapp.data.remote.responses.results.PokemonDetailsDto
import com.crownedjester.soft.pokedexapp.data.remote.responses.results.PokemonResultDto
import javax.inject.Inject

class RemoteSourceRepositoryImpl @Inject constructor(private val api: PokeApi) :
    RemoteSourceRepository {
    override suspend fun retrievePokemonList(limit: Int, offset: Int): PokemonResultDto =
        api.retrievePokemonList(limit, offset)

    override suspend fun retrievePokemonDetails(id: Int): PokemonDetailsDto =
        api.retrievePokemonDetails(id)

}