package com.crownedjester.soft.pokedexapp.data.remote.responses.sprites

import com.squareup.moshi.Json

data class DreamWorldDto(
    @Json(name = "front_default")
    val artUrl: String
)
