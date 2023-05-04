package com.crownedjester.soft.pokedexapp.presentation.pokemon_detail_screen.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.crownedjester.soft.pokedexapp.data.local.entity.Abilities
import com.crownedjester.soft.pokedexapp.domain.model.Pokemon
import com.crownedjester.soft.pokedexapp.extensions.convertToString

@Composable
fun PokemonAboutPage(
    modifier: Modifier = Modifier,
    height: Int,
    weight: Int,
    abilities: Abilities
) {

    Column(modifier = modifier.padding(start = 24.dp), verticalArrangement = Arrangement.Top) {
        InfoEntry(param = Pokemon.HEIGHT_PARAM, data = "$height cm")

        InfoEntry(param = Pokemon.WEIGHT_PARAM, data = "${weight / 10f} kg")

        InfoEntry(param = Pokemon.ABILITIES_PARAM, data = abilities.data.convertToString(","))
    }

}

@Composable
private fun InfoEntry(modifier: Modifier = Modifier, param: String, data: String) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            modifier = Modifier.widthIn(72.dp, 96.dp),
            text = "$param:",
            color = Color(117, 117, 117, 255),
            fontStyle = FontStyle.Italic
        )

        Text(text = data, color = Color(31, 30, 30, 255))

    }
}