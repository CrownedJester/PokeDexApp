package com.crownedjester.soft.pokedexapp.util.converters

import androidx.room.TypeConverter
import com.crownedjester.soft.pokedexapp.data.local.entity.Stats
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class StatConverter:Converter<Stats> {

    private val moshi: Moshi = Moshi.Builder().build()

    @TypeConverter
    override fun decodeFromString(value: String): Stats? {
        val adapter: JsonAdapter<Stats> = moshi.adapter(Stats::class.java)

        return adapter.fromJson(value)
    }

    @TypeConverter
    override fun encodeToString(value: Stats): String {
        val adapter: JsonAdapter<Stats> = moshi.adapter(Stats::class.java)

        return adapter.toJson(value)

    }
}