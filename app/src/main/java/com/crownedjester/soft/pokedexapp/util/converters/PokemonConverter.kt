package com.crownedjester.soft.pokedexapp.util.converters

import com.crownedjester.soft.pokedexapp.domain.model.Pokemon
import com.squareup.moshi.Moshi

object PokemonConverter : Converter<Pokemon> {

    private val moshi: Moshi = Moshi.Builder().build()


    override fun decodeFromString(value: String): Pokemon? {
        val adapter = moshi.adapter(Pokemon::class.java)

        return adapter.fromJson(value)
    }

    override fun encodeToString(value: Pokemon): String {
        val adapter = moshi.adapter(Pokemon::class.java)

        return adapter.toJson(value)
    }
}