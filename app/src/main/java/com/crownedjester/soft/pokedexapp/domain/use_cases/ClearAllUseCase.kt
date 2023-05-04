package com.crownedjester.soft.pokedexapp.domain.use_cases

import com.crownedjester.soft.pokedexapp.domain.repository.local.LocalSourceRepository
import javax.inject.Inject

class ClearAllUseCase @Inject constructor(private val localSourceRepository: LocalSourceRepository) {

    suspend operator fun invoke(){
        localSourceRepository.clearAll()
    }

}