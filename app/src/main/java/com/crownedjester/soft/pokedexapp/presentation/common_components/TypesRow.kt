package com.crownedjester.soft.pokedexapp.presentation.common_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.crownedjester.soft.pokedexapp.data.local.entity.Types

@Composable
fun TypesRow(types: Types, modifier: Modifier = Modifier) {

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            typeItemsArrangement
        )
    ) {
        items(types.data) {
            TypeItem(
                type = it, modifier = Modifier
                    .size(itemWidth, itemHeight)

            )
        }
    }
}

private val typeItemsArrangement = 6.dp
private val itemWidth = 80.dp
private val itemHeight = 36.dp