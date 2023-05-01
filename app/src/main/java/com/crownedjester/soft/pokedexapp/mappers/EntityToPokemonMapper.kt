package com.crownedjester.soft.pokedexapp.mappers

import com.crownedjester.soft.pokedexapp.data.local.entity.PokemonEntity
import com.crownedjester.soft.pokedexapp.domain.model.Pokemon

fun PokemonEntity.toPokemon() =
    Pokemon(
        id = id,
        name = name,
        height = height,
        weight = weight,
        type = type,
        stats = stats,
        abilities = abilities,
        art = art
    )