package com.crownedjester.soft.pokedexapp.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext
import com.crownedjester.soft.pokedexapp.extensions.currentConnectivityState
import com.crownedjester.soft.pokedexapp.extensions.observeConnectivityAsFlow
import com.crownedjester.soft.pokedexapp.util.ConnectionState

@Composable
fun connectivityState(): State<ConnectionState> {
    val context = LocalContext.current

    return produceState(initialValue = context.currentConnectivityState) {
        context.observeConnectivityAsFlow().collect { value = it }
    }
}