package com.crownedjester.soft.pokedexapp.data.local.source

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.crownedjester.soft.pokedexapp.data.local.entity.PokemonEntity

@Dao
interface PokemonDao {

    @Upsert
    suspend fun upsertAll(pokemons: List<PokemonEntity>)

    @Query("select * from pokemon_table")
    fun getPokemonsPagingSource(): PagingSource<Int, PokemonEntity>

    @Query("delete from pokemon_table")
    suspend fun clearAll()

}