package com.kavrin.borutoapp.presentation.screens.search

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchScreen(
	navController: NavHostController,
	searchViewModel: SearchViewModel = hiltViewModel(),
) {

	val searchQuery by searchViewModel.searchQuery

	Scaffold(
		topBar = {
			SearchTopBar(
				text = searchQuery,
				onTextChange = {
					searchViewModel.updateSearchQuery(query = it)
				},
				onSearchClicked = {},
				onCloseClicked = {
					navController.popBackStack()
				}
			)
		},
		content = {}
	)

}