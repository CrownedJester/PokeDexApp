package com.crownedjester.soft.pokedexapp.data.remote

import com.crownedjester.soft.pokedexapp.data.remote.responses.results.PokemonDetailsDto
import com.crownedjester.soft.pokedexapp.data.remote.responses.results.PokemonResultDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApi {

    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }

    @GET("pokemon")
    suspend fun retrievePokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonResultDto

    @GET("pokemon/{id}")
    suspend fun retrievePokemonDetails(
        @Path("id") id: Int
    ): PokemonDetailsDto

}