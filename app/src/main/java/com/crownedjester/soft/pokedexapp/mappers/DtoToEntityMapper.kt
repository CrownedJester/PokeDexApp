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

fun PokemonDetailsDto.toPokemonEntity() = PokemonEntity(
    id = id,
    name = name,
    height = height,
    weight = weight,
    type = types.toTypes(),
    stats = stats.toStats(),
    abilities = abilities.toAbilities(),
    art = sprites.other.dream_world.front_default
)

fun List<TypeDto>.toTypes(): Types = Types(
    map {
        it.type.name
    }
)

fun List<StatsDto>.toStats(): Stats = Stats(
    map {
        Stat(
            baseStat = it.base_stat, effort = it.effort, name = it.stat.name
        )
    }
)


fun List<AbilitiesDto>.toAbilities(): Abilities = Abilities(
    map {
        Ability(
            name = it.ability.name, isHidden = it.is_hidden
        )
    }
)

