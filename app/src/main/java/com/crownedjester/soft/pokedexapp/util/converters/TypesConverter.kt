package com.crownedjester.soft.pokedexapp.util.converters

import androidx.room.TypeConverter
import com.crownedjester.soft.pokedexapp.data.local.entity.Types
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class TypesConverter : Converter<Types> {


    private val moshi: Moshi = Moshi.Builder().build()

    @TypeConverter
    override fun decodeFromString(value: String): Types? {
        val adapter: JsonAdapter<Types> = moshi.adapter(Types::class.java)

        return adapter.fromJson(value)
    }

    @TypeConverter
    override fun encodeToString(value: Types): String {
        val adapter: JsonAdapter<Types> = moshi.adapter(Types::class.java)

        return adapter.toJson(value)

    }
}