package com.crownedjester.soft.pokedexapp.presentation.util

import android.widget.Toast

sealed class UiEvent {

    data class Navigate(val route: String) : UiEvent()
    data class ShowToast(val message: String, val length: Int = Toast.LENGTH_SHORT) : UiEvent()

    object NavigateUp : UiEvent()

    //Used to init compose collectAsState
    object IDLE : UiEvent()


}
