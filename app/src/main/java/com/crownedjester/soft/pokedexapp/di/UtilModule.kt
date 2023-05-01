package com.crownedjester.soft.pokedexapp.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UtilModule {


    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder().build()


}