package com.kavrin.borutoapp.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.kavrin.borutoapp.presentation.common.ListContent

@ExperimentalCoilApi
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
	navController: NavHostController,
	searchViewModel: SearchViewModel = hiltViewModel(),
) {

	val searchQuery by searchViewModel.searchQuery
	val heroes = searchViewModel.searchedHeroes.collectAsLazyPagingItems()

	Scaffold(
		topBar = {
			SearchTopBar(
				text = searchQuery,
				onTextChange = {
					searchViewModel.updateSearchQuery(query = it)
				},
				onSearchClicked = {
					searchViewModel.searchHeroes(query = it)
				},
				onCloseClicked = {
					navController.popBackStack()
				}
			)
		},
		content = {
			ListContent(
				heroes = heroes,
				navController = navController
			)
		}
	)

}