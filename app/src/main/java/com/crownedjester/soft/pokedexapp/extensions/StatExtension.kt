package com.crownedjester.soft.pokedexapp.extensions

import com.crownedjester.soft.pokedexapp.data.local.entity.Stat

val Stat.beautyName
    get() = when (this.name) {
        Stat.HP -> "Hp"
        Stat.ATK -> "Attack"
        Stat.DEF -> "Defense"
        Stat.SP_ATK -> "Sp.Atk"
        Stat.SP_DEF -> "Sp.Def"
        Stat.SPEED -> "Speed"
        else -> {
            throw Exception("Unknown Stat + ${this.name}")
        }
    }