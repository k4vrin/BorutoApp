package com.kavrin.borutoapp.presentation.screens.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kavrin.borutoapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    useCases: UseCases
) : ViewModel() {

	private val _searchQuery = mutableStateOf("")
	val searchQuery: State<String> = _searchQuery

	fun updateSearchQuery(query: String) {
		_searchQuery.value = query
	}
}