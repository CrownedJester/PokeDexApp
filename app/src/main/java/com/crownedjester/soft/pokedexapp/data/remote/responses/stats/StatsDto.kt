package com.crownedjester.soft.pokedexapp.data.remote.responses.stats

import com.squareup.moshi.Json

data class StatsDto(
    @Json(name = "base_stat")
    val baseStat: Int,
    val effort: Int,
    val stat: Stat
)
