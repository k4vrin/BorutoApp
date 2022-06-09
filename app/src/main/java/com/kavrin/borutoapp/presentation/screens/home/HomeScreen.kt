package com.kavrin.borutoapp.presentation.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.kavrin.borutoapp.presentation.components.RatingWidget

@Composable
fun HomeScreen(
	navController: NavHostController,
	homeViewModel: HomeViewModel = hiltViewModel(),
) {

	val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

	Scaffold(
		topBar = {
			HomeTopBar(onSearchClicked = {})
		}
	) {
		RatingWidget(
			modifier = Modifier
				.padding(it),
			rating = -1.6
		)
	}
}