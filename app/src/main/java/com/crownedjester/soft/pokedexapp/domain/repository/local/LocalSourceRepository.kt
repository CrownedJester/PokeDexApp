package com.crownedjester.soft.pokedexapp.domain.repository.local

import androidx.paging.PagingSource
import com.crownedjester.soft.pokedexapp.data.local.entity.PokemonEntity

interface LocalSourceRepository {

    suspend fun upsertAll(pokemons: List<PokemonEntity>)

    fun getPokemonPagingSource(): PagingSource<Int, PokemonEntity>

    suspend fun clearAll()

}