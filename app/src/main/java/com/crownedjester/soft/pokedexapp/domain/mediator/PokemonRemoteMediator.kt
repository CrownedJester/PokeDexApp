package com.crownedjester.soft.pokedexapp.domain.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.crownedjester.soft.pokedexapp.data.local.entity.PokemonEntity
import com.crownedjester.soft.pokedexapp.data.local.source.PokemonDatabase
import com.crownedjester.soft.pokedexapp.domain.repository.local.LocalSourceRepository
import com.crownedjester.soft.pokedexapp.domain.repository.remote.RemoteSourceRepository
import com.crownedjester.soft.pokedexapp.mappers.toPokemonEntity
import com.crownedjester.soft.pokedexapp.util.parseId
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val remoteSourceRepository: RemoteSourceRepository,
    private val localSourceRepository: LocalSourceRepository,
    private val pokemonDb: PokemonDatabase
) : RemoteMediator<Int, PokemonEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 0
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    lastItem?.id ?: 0
                }

                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
            }

            val pokemonResultDto = remoteSourceRepository.retrievePokemonList(
                limit = state.config.pageSize, offset = loadKey
            )

            val pokemonDetailsList = pokemonResultDto.results.map { pokemonDto ->
                remoteSourceRepository.retrievePokemonDetails(pokemonDto.url.parseId())
            }

            pokemonDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    localSourceRepository.clearAll()
                }

                val pokemonEntities = pokemonDetailsList.map { pokemonDto ->
                    pokemonDto.toPokemonEntity()
                }

                localSourceRepository.upsertAll(pokemonEntities)
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