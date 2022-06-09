package com.kavrin.borutoapp.presentation.screens.home

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun HomeScreen(
	navController: NavHostController,
	homeViewModel: HomeViewModel = hiltViewModel()
) {

	val allHeroes = homeViewModel.getAllHeroes.collectAsLazyPagingItems()

	Scaffold(
		topBar = {
			HomeTopBar(onSearchClicked = {})
		}
	) {}
}