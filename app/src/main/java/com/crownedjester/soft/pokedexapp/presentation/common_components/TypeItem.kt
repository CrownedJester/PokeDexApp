package com.crownedjester.soft.pokedexapp.presentation.common_components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.crownedjester.soft.pokedexapp.data.local.entity.Types

@Composable
fun TypeItem(modifier: Modifier = Modifier, type: String) {

    val typeColor = Types.typesWithColors[type] ?: Color.Black

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(cardCornerRadius),
        elevation = CardDefaults.elevatedCardElevation(cardElevation),
        colors = CardDefaults.cardColors(containerColor = typeColor)

    ) {

        Text(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingTextHorizontal, paddingTextVertical),
            text = type,
            textAlign = TextAlign.Center,
            color = Color.White
        )

    }
}

private val cardElevation = (0.2).dp
private const val cardCornerRadius = 36
private val paddingTextVertical = 4.dp
private val paddingTextHorizontal = 8.dp