package com.kavrin.borutoapp.presentation.screens.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.kavrin.borutoapp.domain.model.Hero
import com.kavrin.borutoapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCases: UseCases
) : ViewModel() {

	private val _searchQuery = mutableStateOf("")
	val searchQuery: State<String> = _searchQuery

	private val _searchedHeroes = MutableStateFlow<PagingData<Hero>>(PagingData.empty())
	val searchedHeroes: StateFlow<PagingData<Hero>> = _searchedHeroes

	fun updateSearchQuery(query: String) {
		_searchQuery.value = query
	}

	fun searchHeroes(query: String) {
		viewModelScope.launch(context = Dispatchers.IO) {
			// We use cachedIn here because we do not cache the searched data in the local database
			useCases.searchHeroesUseCase(query = query).cachedIn(viewModelScope).collect {
				_searchedHeroes.value = it
			}
		}
	}

}