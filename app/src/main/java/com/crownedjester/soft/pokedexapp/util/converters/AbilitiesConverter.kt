package com.crownedjester.soft.pokedexapp.util.converters

import androidx.room.TypeConverter
import com.crownedjester.soft.pokedexapp.data.local.entity.Abilities
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class AbilitiesConverter : Converter<Abilities> {

    private val moshi: Moshi = Moshi.Builder().build()


    @TypeConverter
    override fun decodeFromString(value: String): Abilities? {
        if (value.isBlank() || value.isEmpty()) return Abilities(emptyList())
        val adapter: JsonAdapter<Abilities> = moshi.adapter(Abilities::class.java)

        return adapter.fromJson(value)
    }

    @TypeConverter
    override fun encodeToString(value: Abilities): String {
        val adapter: JsonAdapter<Abilities> = moshi.adapter(Abilities::class.java)

        return adapter.toJson(value)

    }
}