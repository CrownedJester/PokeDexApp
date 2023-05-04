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
import com.crownedjester.soft.pokedexapp.presentation.common_components.TypesRow

@Composable
fun PokemonItem(
    pokemon: Pokemon, modifier: Modifier = Modifier
) {

    val (_, name, _, _, types, _, _, art) = pokemon

    val cardColor = Types.typesWithColors[types.data.firstOrNull()] ?: Color.Transparent

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = cardColor.copy(alpha = cardBgAlpha)
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = mainRowHorizontalPadding, vertical = mainRowVerticalPadding)
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

            Column(modifier = Modifier, verticalArrangement = Arrangement.Top) {

                Text(
                    text = name,
                    fontSize = nameFontSize,
                    modifier = Modifier.padding(6.dp)
                )

                Spacer(modifier = Modifier.fillMaxHeight(0.03f))

                TypesRow(types = types)


            }


        }

    }

}


private val mainRowVerticalPadding = 8.dp
private val mainRowHorizontalPadding = 14.dp
private val nameFontSize = 20.sp
private val imageSize = 96.dp
private const val cardBgAlpha = 0.8f