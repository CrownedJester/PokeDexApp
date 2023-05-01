package com.crownedjester.soft.pokedexapp

import com.crownedjester.soft.pokedexapp.util.ID_PARSER_ERROR_RESPONSE
import com.crownedjester.soft.pokedexapp.util.parseId
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class UtilUnitTest {

    @Test
    fun parserId_isCorrect() {
        val url = "https://pokeapi.co/api/v2/pokemon/122/"

        assertEquals(122, url.parseId())

    }

    @Test
    fun parseId_emptyData_isCorrect() {
        val url = ""
        val expected = ID_PARSER_ERROR_RESPONSE

        assertEquals(expected, url.parseId())
    }


}