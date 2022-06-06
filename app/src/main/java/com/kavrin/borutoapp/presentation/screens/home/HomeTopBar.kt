package com.kavrin.borutoapp.presentation.screens.home

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.kavrin.borutoapp.R
import com.kavrin.borutoapp.ui.theme.topBarBgColor
import com.kavrin.borutoapp.ui.theme.topBarContentColor

@Composable
fun HomeTopBar(
	onSearchClicked: () -> Unit
) {
	// A.k.a ActionBar
	TopAppBar(
		//// Title ////
		title = {
			Text(
				text = "Explore",
				color = MaterialTheme.colors.topBarContentColor
			)
		},
		//// Background Color ////
		backgroundColor = MaterialTheme.colors.topBarBgColor,
		//// Actions ////
		actions = {
			IconButton(
				onClick = onSearchClicked
			) {
				Icon(
					imageVector = Icons.Default.Search,
					contentDescription = stringResource(R.string.search_icon)
				)
			}
		}
	)
}

@Preview
@Composable
private fun HomeTopBarPrev() {
	HomeTopBar {}
}