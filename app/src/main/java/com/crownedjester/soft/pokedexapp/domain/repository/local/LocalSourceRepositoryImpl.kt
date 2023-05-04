package com.crownedjester.soft.pokedexapp.domain.repository.local

import androidx.paging.PagingSource
import com.crownedjester.soft.pokedexapp.data.local.entity.PokemonEntity
import com.crownedjester.soft.pokedexapp.data.local.source.PokemonDao
import javax.inject.Inject

class LocalSourceRepositoryImpl @Inject constructor(private val dao: PokemonDao) :
    LocalSourceRepository {
    override suspend fun upsertAll(pokemons: List<PokemonEntity>) {
        dao.upsertAll(pokemons)
    }

    override fun getPokemonPagingSource(): PagingSource<Int, PokemonEntity> =
        dao.getPokemonsPagingSource()

    override suspend fun clearAll() {
        dao.clearAll()
    }


}