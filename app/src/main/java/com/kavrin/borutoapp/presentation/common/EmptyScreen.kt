package com.kavrin.borutoapp.presentation.common

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.kavrin.borutoapp.R
import com.kavrin.borutoapp.domain.model.Hero
import com.kavrin.borutoapp.ui.theme.DarkGray
import com.kavrin.borutoapp.ui.theme.LightGray
import com.kavrin.borutoapp.ui.theme.NETWORK_ERROR_ICON_HEIGHT
import com.kavrin.borutoapp.ui.theme.SMALL_PADDING
import java.net.ConnectException
import java.net.SocketTimeoutException

@Composable
fun EmptyScreen(
	error: LoadState.Error? = null,
	heroes: LazyPagingItems<Hero>? = null,
) {
	var message by remember {
		mutableStateOf("Find your Favorite Hero!")
	}
	var icon by remember {
		mutableStateOf(R.drawable.search_document)
	}
	if (error != null) {
		message = parseErrorMessage(error = error)
		icon = R.drawable.network_error
	}
	var startAnimation by remember {
		mutableStateOf(false)
	}
	val alphaAnim by animateFloatAsState(
		targetValue = if (startAnimation) ContentAlpha.disabled else 0f,
		animationSpec = tween(
			durationMillis = 1000
		)
	)
	LaunchedEffect(key1 = true) {
		startAnimation = true
	}

	EmptyContent(
		alphaAnim = alphaAnim,
		icon = icon,
		message = message,
		heroes = heroes,
		error = error
	)
}

@Composable
fun EmptyContent(
	alphaAnim: Float,
	icon: Int,
	message: String,
	heroes: LazyPagingItems<Hero>? = null,
	error: LoadState.Error? = null,
) {

	var isRefreshing by remember {
		mutableStateOf(false)
	}
	SwipeRefresh(
		// Only enabled when we have error
		swipeEnabled = error != null,
		state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
		onRefresh = {
			isRefreshing = true
			heroes?.refresh()
			isRefreshing = false
		}
	) {

		Column(
			modifier = Modifier
				.fillMaxSize()
					// Swipe to refresh won't work without this
					// if it was lazyColumn we wouldn't need this
				.verticalScroll(rememberScrollState()),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			Icon(
				modifier = Modifier
					.size(NETWORK_ERROR_ICON_HEIGHT)
					.alpha(alphaAnim),
				painter = painterResource(id = icon),
				contentDescription = stringResource(R.string.network_error_icon),
				tint = if (isSystemInDarkTheme()) LightGray else DarkGray
			)

			Text(
				modifier = Modifier
					.padding(top = SMALL_PADDING)
					.alpha(alphaAnim),
				text = message,
				color = if (isSystemInDarkTheme()) LightGray else DarkGray,
				textAlign = TextAlign.Center,
				fontWeight = FontWeight.Medium,
				fontSize = MaterialTheme.typography.subtitle1.fontSize
			)
		}
	}
}

fun parseErrorMessage(error: LoadState.Error): String {
	return when (error.error) {
		is SocketTimeoutException -> {
			"Server Unavailable."
		}
		is ConnectException -> {
			"Internet Unavailable."
		}
		else -> "Unknown Error."
	}
}

@Preview(showBackground = true)
@Composable
fun EmptyScreenPreview() {
	EmptyContent(
		alphaAnim = 1f,
		icon = R.drawable.network_error,
		message = "Internet Fucked"
	)
}
