package com.crownedjester.soft.pokedexapp

import com.crownedjester.soft.pokedexapp.data.local.entity.Types
import com.crownedjester.soft.pokedexapp.util.converters.TypesConverter
import junit.framework.TestCase.assertEquals
import org.junit.Test


class ConverterUnitTest {

    private val encodedData = "{\"data\":[\"shadow\",\"grass\",\"water\"]}"
    private val typesConverter = TypesConverter()

    private val types = Types(
        listOf(
            "shadow", "grass", "water"
        )
    )

    @Test
    fun typesConverter_decoding_isCorrect() {

        assertEquals(types.data, typesConverter.decodeFromString(encodedData)?.data)

    }

    @Test
    fun typesConverter_decoding_emptyString_isCorrect() {
        val expected = Types(
            listOf()
        )
        val givenData = ""

        assertEquals(expected.data, typesConverter.decodeFromString(givenData)?.data)

    }

    @Test
    fun typesConverter_encoding_isCorrect() {

        assertEquals(encodedData, typesConverter.encodeToString(types))
    }

    @Test
    fun typesConverter_encoding_emptyList_isCorrect() {
        val expected = "{\"data\":[]}"
        val data = Types(emptyList())

        assertEquals(expected, typesConverter.encodeToString(data))
    }
}