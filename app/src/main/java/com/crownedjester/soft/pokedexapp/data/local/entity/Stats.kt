package com.crownedjester.soft.pokedexapp.data.local.entity

data class Stats(
    val stats: List<Stat>
)

data class Stat(
    val baseStat: Int,
    val effort: Int,
    val name: String
)


