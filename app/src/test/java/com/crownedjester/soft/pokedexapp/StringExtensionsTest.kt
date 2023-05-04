package com.crownedjester.soft.pokedexapp

import com.crownedjester.soft.pokedexapp.extensions.makeFirstUppercase
import org.junit.Assert
import org.junit.Test

class StringExtensionsTest {


    @Test
    fun makeFirstUppercase_firstLowercase_isCorrect() {
        val expected = "Pickachu"
        val valueToTest = "pickachu"

        Assert.assertEquals(expected, valueToTest.makeFirstUppercase())
    }

    @Test
    fun makeFirstUppercase_emptyValue_isCorrect() {
        val expected = ""
        val value = ""

        Assert.assertEquals(expected, value.makeFirstUppercase())
    }

    @Test
    fun makeFirstUppercase_firstUppercase_isCorrect() {
        val expected = "Pickachu"
        val value = "Pickachu"

        Assert.assertEquals(expected, value.makeFirstUppercase())
    }

}