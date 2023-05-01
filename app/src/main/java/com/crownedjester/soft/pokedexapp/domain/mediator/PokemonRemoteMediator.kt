package com.crownedjester.soft.pokedexapp.domain.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.crownedjester.soft.pokedexapp.data.local.entity.PokemonEntity
import com.crownedjester.soft.pokedexapp.data.local.source.PokemonDatabase
import com.crownedjester.soft.pokedexapp.data.remote.PokeApi
import com.crownedjester.soft.pokedexapp.mappers.toPokemonEntity
import com.crownedjester.soft.pokedexapp.util.parseId
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val api: PokeApi,
    private val pokemonDb: PokemonDatabase
) : RemoteMediator<Int, PokemonEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }

                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
            }

            val pokemonResultDto = api.retrievePokemonList(
                loadKey, state.config.pageSize
            )
            val pokemonDetailsList = pokemonResultDto.results.map {
                api.retrievePokemonDetails(it.url.parseId())
            }

            pokemonDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    pokemonDb.dao.clearAll()
                }
                val pokemonEntities = pokemonDetailsList.map {
                    it.toPokemonEntity()
                }
                pokemonDb.dao.upsertAll(pokemonEntities)
            }

            MediatorResult.Success(
                endOfPaginationReached = pokemonResultDto.results.isEmpty()
            )

        } catch (e: HttpException) {
            MediatorResult.Error(e)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        }


    }

}