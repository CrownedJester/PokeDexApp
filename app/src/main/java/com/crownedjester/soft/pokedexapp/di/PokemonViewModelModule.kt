package com.crownedjester.soft.pokedexapp.di

import com.crownedjester.soft.pokedexapp.domain.repository.local.LocalSourceRepository
import com.crownedjester.soft.pokedexapp.domain.use_cases.ClearAllUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object PokemonViewModelModule {

    @ViewModelScoped
    @Provides
    fun providesClearAllUseCase(localSourceRepository: LocalSourceRepository) =
        ClearAllUseCase(localSourceRepository)


}