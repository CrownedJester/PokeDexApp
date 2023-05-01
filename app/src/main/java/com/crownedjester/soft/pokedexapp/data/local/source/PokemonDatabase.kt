package com.crownedjester.soft.pokedexapp.data.local.source

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.crownedjester.soft.pokedexapp.data.local.entity.PokemonEntity
import com.crownedjester.soft.pokedexapp.util.converters.AbilitiesConverter
import com.crownedjester.soft.pokedexapp.util.converters.StatConverter
import com.crownedjester.soft.pokedexapp.util.converters.TypesConverter

@Database(
    entities = [PokemonEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(AbilitiesConverter::class, TypesConverter::class, StatConverter::class)
abstract class PokemonDatabase : RoomDatabase() {

    abstract val dao: PokemonDao

    companion object{
        const val NAME = "pokemon_database"
    }
}