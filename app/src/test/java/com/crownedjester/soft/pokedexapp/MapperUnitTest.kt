package com.crownedjester.soft.pokedexapp

import com.crownedjester.soft.pokedexapp.data.remote.responses.type.TypeDetailDto
import com.crownedjester.soft.pokedexapp.data.remote.responses.type.TypeDto
import com.crownedjester.soft.pokedexapp.mappers.toTypes
import org.junit.Assert
import org.junit.Test

class MapperUnitTest {

    @Test
    fun mapper_typeDto_to_types_isCorrect() {
        val expected = listOf("a", "b", "c")
        val data = listOf(
            TypeDto(1, TypeDetailDto("a", "b")),
            TypeDto(1, TypeDetailDto("b", "b")),
            TypeDto(1, TypeDetailDto("c", "b")),
        )

        Assert.assertEquals(expected, data.toTypes().types)

    }

    @Test
    fun mapper_typeDto_to_types_emptyList_isCorrect() {
        val expected = emptyList<String>()
        val data = emptyList<TypeDto>()

        Assert.assertEquals(expected, data.toTypes().types)

    }


}