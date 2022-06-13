package com.kavrin.borutoapp.presentation.screens.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kavrin.borutoapp.util.Constants.BASE_URL
import com.kavrin.borutoapp.util.PaletteGenerator.convertImageUrlToBitmap
import com.kavrin.borutoapp.util.PaletteGenerator.extractColorFromBitmap
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DetailsScreen(
	navController: NavHostController,
	detailsViewModel: DetailsViewModel = hiltViewModel(),
) {
	val selectedHero by detailsViewModel.selectedHero.collectAsState()

	val colorPalette by detailsViewModel.colorPalette

	if (colorPalette.isNotEmpty())
		DetailsContent(
			navController = navController,
			selectedHero = selectedHero,
			colorPalette = colorPalette
		)
	else
		detailsViewModel.generateColorPalette()

	val context = LocalContext.current
	LaunchedEffect(key1 = true) {
		detailsViewModel.uiEvent.collectLatest { event ->
			when (event) {
				is UiEvent.GenerateColorPalette -> {

					val bitmap = convertImageUrlToBitmap(
						context = context,
						imageUrl = "$BASE_URL${selectedHero?.image}"
					)

					if (bitmap != null) {
						detailsViewModel.setColorPalette(
							color = extractColorFromBitmap(bitmap = bitmap)
						)
					}

				}
			}
		}
	}

}