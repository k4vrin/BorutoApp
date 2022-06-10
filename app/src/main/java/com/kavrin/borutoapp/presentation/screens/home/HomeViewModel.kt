package com.kavrin.borutoapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import com.kavrin.borutoapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel() {

	// We don't use cachedIn here because we cache the data in the local database (in RemoteMediator)
	val getAllHeroes = useCases.getAllHeroesUseCase()
}