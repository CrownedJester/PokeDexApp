package com.crownedjester.soft.pokedexapp.mappers

import com.crownedjester.soft.pokedexapp.data.local.entity.Abilities
import com.crownedjester.soft.pokedexapp.data.local.entity.Ability
import com.crownedjester.soft.pokedexapp.data.local.entity.PokemonEntity
import com.crownedjester.soft.pokedexapp.data.local.entity.Stat
import com.crownedjester.soft.pokedexapp.data.local.entity.Stats
import com.crownedjester.soft.pokedexapp.data.local.entity.Types
import com.crownedjester.soft.pokedexapp.data.remote.responses.abilities.AbilitiesDto
import com.crownedjester.soft.pokedexapp.data.remote.responses.results.PokemonDetailsDto
import com.crownedjester.soft.pokedexapp.data.remote.responses.stats.StatsDto
import com.crownedjester.soft.pokedexapp.data.remote.responses.type.TypeDto

fun PokemonDetailsDto.toPokemonEntity() =
    PokemonEntity(
        id = id,
        height = height,
        weight = weight,
        type = typesDto.toTypes(),
        stats = statsDto.toStats(),
        abilities = abilitiesDto.toAbilities(),
        art = sprites.other.dreamWorld.artUrl ?: sprites.defaultArt
    )

fun List<TypeDto>.toTypes(): Types {
    val typesResult = mutableListOf<String>()
    this.forEach {
        typesResult.add(it.type.name)
    }
    return Types(typesResult)
}

fun List<StatsDto>.toStats(): Stats {
    val statsResult = mutableListOf<Stat>()

    this.forEach {
        statsResult.add(
            Stat(
                baseStat = it.baseStat,
                effort = it.effort,
                name = it.stat.name
            )
        )
    }
    return Stats(statsResult)
}

fun List<AbilitiesDto>.toAbilities(): Abilities {
    val abilitiesResult = mutableListOf<Ability>()

    this.forEach {
        abilitiesResult.add(
            Ability(
                name = it.ability.name,
                isHidden = it.isHidden
            )
        )
    }

    return Abilities(abilitiesResult)
}