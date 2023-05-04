package com.crownedjester.soft.pokedexapp.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.crownedjester.soft.pokedexapp.data.local.entity.PokemonEntity
import com.crownedjester.soft.pokedexapp.data.local.source.PokemonDatabase
import com.crownedjester.soft.pokedexapp.data.remote.PokeApi
import com.crownedjester.soft.pokedexapp.domain.mediator.PokemonRemoteMediator
import com.crownedjester.soft.pokedexapp.domain.repository.local.LocalSourceRepository
import com.crownedjester.soft.pokedexapp.domain.repository.local.LocalSourceRepositoryImpl
import com.crownedjester.soft.pokedexapp.domain.repository.remote.RemoteSourceRepository
import com.crownedjester.soft.pokedexapp.domain.repository.remote.RemoteSourceRepositoryImpl
import com.crownedjester.soft.pokedexapp.util.PAGE_SIZE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesPokemonDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            PokemonDatabase.NAME
        ).build()

    @Provides
    @Singleton
    fun providesRetrofitBuilder(): Retrofit =
        Retrofit.Builder()
            .baseUrl(PokeApi.BASE_URL)
            .client(PokeApi.httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesPokeApiService(retrofit: Retrofit): PokeApi =
        retrofit.create(PokeApi::class.java)

    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun providesPokemonPager(
        localRepository: LocalSourceRepository,
        remoteRepository: RemoteSourceRepository,
        pokemonDb: PokemonDatabase
    ): Pager<Int, PokemonEntity> =
        Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            remoteMediator = PokemonRemoteMediator(
                remoteSourceRepository = remoteRepository,
                localSourceRepository = localRepository,
                pokemonDb = pokemonDb
            ),
            pagingSourceFactory = {
                pokemonDb.dao.getPokemonsPagingSource()
            }
        )

    @Singleton
    @Provides
    fun providesRemoteSourceRepository(api: PokeApi): RemoteSourceRepository {
        return RemoteSourceRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun providesLocalSourceRepository(pokemonDb: PokemonDatabase): LocalSourceRepository {
        return LocalSourceRepositoryImpl(pokemonDb.dao)
    }


}