package com.crownedjester.soft.pokedexapp

import com.crownedjester.soft.pokedexapp.util.PokemonDataParser
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UtilUnitTest {

    @Test
    fun pokeId_parser_isCorrect() {
        val urlToTest = "https://pokeapi.co/api/v2/pokemon/122/"

        assertEquals(122, PokemonDataParser.parseId(urlToTest))

    }


}