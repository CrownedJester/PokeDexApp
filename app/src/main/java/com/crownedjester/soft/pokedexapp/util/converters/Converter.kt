package com.crownedjester.soft.pokedexapp.util.converters

interface Converter<T> {

    fun decodeFromString(value: String): T?

    fun encodeToString(value: T) : String

}