package com.crownedjester.soft.pokedexapp

import com.crownedjester.soft.pokedexapp.data.local.entity.Types
import com.crownedjester.soft.pokedexapp.util.converters.TypesConverter
import junit.framework.TestCase.assertEquals
import org.junit.Test


class ConverterUnitTest {

    private val encodedData = "{\"types\":[\"shadow\",\"grass\",\"water\"]}"
    private val typesConverter = TypesConverter()

    private val data = Types(
        listOf(
            "shadow", "grass", "water"
        )
    )

    @Test
    fun typesConverter_decoding_isCorrect() {
        val expected = Types(
            listOf(
                "shadow", "grass", "water"
            )
        )

        assertEquals(expected.types, typesConverter.decodeFromString(encodedData)?.types)

    }

    @Test
    fun typesConverter_encoding_isCorrect() {
        val expected = "{\"types\":[\"shadow\",\"grass\",\"water\"]}"

        assertEquals(expected, typesConverter.encodeToString(data))
    }

    @Test
    fun typesConverter_encoding_emptyData_isCorrect() {
        val expected = "{\"types\":[]}"
        val data = Types(emptyList())

        assertEquals(expected, typesConverter.encodeToString(data))
    }
}