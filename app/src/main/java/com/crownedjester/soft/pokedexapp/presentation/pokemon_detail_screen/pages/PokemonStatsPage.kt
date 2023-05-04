package com.crownedjester.soft.pokedexapp.presentation.pokemon_detail_screen.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.crownedjester.soft.pokedexapp.data.local.entity.Stat
import com.crownedjester.soft.pokedexapp.data.local.entity.Stats
import com.crownedjester.soft.pokedexapp.extensions.beautyName
import com.crownedjester.soft.pokedexapp.extensions.makeFirstUppercase

@Composable
fun PokemonStatsPage(modifier: Modifier = Modifier, stats: Stats) {

    Row(
        modifier = modifier.padding(start = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(rowArrangement)
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(columnArrangement)
        ) {
            stats.data.forEach { stat ->
                Text(
                    text = stat.beautyName.makeFirstUppercase(),
                    fontStyle = FontStyle.Italic,
                    color = Color(117, 117, 117, 255)
                )
            }
        }

        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(columnArrangement)
        ) {
            stats.data.forEach { stat ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(rowArrangement)

                ) {
                    Text(
                        modifier = Modifier.widthIn(32.dp, 40.dp),
                        text = stat.baseStat.toString(),
                    )

                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

                        LinearProgressIndicator(
                            modifier = Modifier.zIndex(90f),
                            progress = stat.baseStat / Stat.MAX_STAT_VALUE,
                            color = Stat.statsWithColors[stat.name] ?: Color.Black
                        )

                        LinearProgressIndicator(
                            modifier = Modifier.zIndex(45f),
                            progress = 1f,
                            color = Color(0, 0, 0, 25)
                        )
                    }

                }
            }

        }
    }

}


private val rowArrangement = 16.dp
private val columnArrangement = 12.dp

