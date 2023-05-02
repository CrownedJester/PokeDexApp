package com.crownedjester.soft.pokedexapp.presentation.dashboard_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Scale
import com.crownedjester.soft.pokedexapp.data.local.entity.Types
import com.crownedjester.soft.pokedexapp.domain.model.Pokemon
import com.crownedjester.soft.pokedexapp.extensions.makeFirstUppercase

@Composable
fun PokemonItem(
    pokemon: Pokemon, modifier: Modifier = Modifier
) {

    val (_, name, _, _, types, _, _, art) = pokemon

    val cardColor = Types.typesWithColors[types.types[0]] ?: Color.Transparent

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = defaultCardElevation, pressedElevation = pressedCardElevation
        ),
        colors = CardDefaults.cardColors(
            containerColor = cardColor.copy(alpha = cardBgAlpha)
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(mainRowPadding)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(art)
                    .scale(Scale.FIT)
                    .decoderFactory(SvgDecoder.Factory())
                    .build(),
                contentDescription = "pokemon list art",
                modifier = Modifier.size(imageSize),
                alignment = Alignment.CenterStart
            )

            Spacer(modifier = Modifier.fillMaxWidth(0.1f))

            Column {

                Text(
                    text = name.makeFirstUppercase(),
                    fontSize = nameFontSize,
                    modifier = Modifier.padding(6.dp)
                )

                Spacer(modifier = Modifier.fillMaxHeight(0.03f))

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(
                        typeItemsArrangement
                    )
                ) {
                    items(types.types) {
                        TypeItem(
                            type = it, modifier = Modifier
                                .size(80.dp, 36.dp)

                        )
                    }
                }
            }


        }

    }

}


private val defaultCardElevation = 6.dp
private val pressedCardElevation = 2.dp
private val mainRowPadding = 8.dp
private val nameFontSize = 20.sp
private val typeItemsArrangement = 6.dp
private val imageSize = 96.dp
private const val cardBgAlpha = 0.8f