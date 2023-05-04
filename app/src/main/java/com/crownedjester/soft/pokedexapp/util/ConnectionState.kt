package com.crownedjester.soft.pokedexapp.util

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()

}
