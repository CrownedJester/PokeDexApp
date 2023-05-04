package com.crownedjester.soft.pokedexapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.crownedjester.soft.pokedexapp.data.local.entity.PokemonEntity
import com.crownedjester.soft.pokedexapp.domain.use_cases.ClearAllUseCase
import com.crownedjester.soft.pokedexapp.mappers.toPokemon
import com.crownedjester.soft.pokedexapp.presentation.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    pager: Pager<Int, PokemonEntity>,
    private val clearAllUseCase: ClearAllUseCase
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val pokemonPagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { pokemonEntity -> pokemonEntity.toPokemon() }

        }.cachedIn(viewModelScope)

    fun sendEvent(uiEvents: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(uiEvents)
        }

    }

    fun clearAll() {
        viewModelScope.launch(Dispatchers.IO) {
            clearAllUseCase()
        }
    }


}