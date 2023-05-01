package com.crownedjester.soft.pokedexapp.data.remote.responses.sprites

import com.squareup.moshi.Json

data class OtherDto(
    @Json(name = "dream_world")
    val dreamWorld: DreamWorldDto
)
