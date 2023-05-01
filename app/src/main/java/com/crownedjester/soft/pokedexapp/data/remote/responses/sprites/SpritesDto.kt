package com.crownedjester.soft.pokedexapp.data.remote.responses.sprites

import com.squareup.moshi.Json

data class SpritesDto(
    @Json(name = "front_default")
    val defaultArt: String,
    val other: OtherDto
)
