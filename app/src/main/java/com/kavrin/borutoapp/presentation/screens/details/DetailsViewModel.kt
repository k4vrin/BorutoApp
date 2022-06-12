package com.kavrin.borutoapp.presentation.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kavrin.borutoapp.domain.model.Hero
import com.kavrin.borutoapp.domain.use_cases.UseCases
import com.kavrin.borutoapp.util.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
	private val useCases: UseCases,
	savedStateHandle: SavedStateHandle,
) : ViewModel() {

	// With Dispatchers.IO we should use StateFlow
	private val _selectedHero: MutableStateFlow<Hero?> = MutableStateFlow(null)
	val selectedHero: StateFlow<Hero?> = _selectedHero

	init {
		viewModelScope.launch(context = Dispatchers.IO) {
			val heroId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)
			_selectedHero.value = heroId?.let {
				useCases.getSelectedHeroUseCase(heroId = heroId)
			}
		}
	}
}