package com.kavrin.borutoapp.presentation.screens.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kavrin.borutoapp.domain.model.Hero
import com.kavrin.borutoapp.domain.use_cases.UseCases
import com.kavrin.borutoapp.util.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
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

	// With sharedFlow we are going to trigger one time event that will not be triggered two times even if we rotate our screen
	private val _uiEvent = MutableSharedFlow<UiEvent>()
	val uiEvent: SharedFlow<UiEvent> = _uiEvent

	private val _colorPalette = mutableStateOf<Map<String, String>>(mapOf())
	val colorPalette: State<Map<String, String>> = _colorPalette

	fun generateColorPalette() {
		viewModelScope.launch {
			_uiEvent.emit(UiEvent.GenerateColorPalette)
		}
	}

	fun setColorPalette(color: Map<String, String>) {
		_colorPalette.value = color
	}

}

sealed class UiEvent {
	object GenerateColorPalette : UiEvent()
}