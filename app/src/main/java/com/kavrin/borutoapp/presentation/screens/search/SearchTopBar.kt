package com.kavrin.borutoapp.presentation.screens.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.kavrin.borutoapp.R
import com.kavrin.borutoapp.ui.theme.TOP_APP_BAR_HEIGHT
import com.kavrin.borutoapp.ui.theme.topBarBgColor
import com.kavrin.borutoapp.ui.theme.topBarContentColor

@Composable
fun SearchTopBar(
	text: String,
	onTextChange: (String) -> Unit,
	onSearchClicked: (String) -> Unit,
	onCloseClicked: () -> Unit,
) {
	SearchWidget(
		text = text,
		onTextChange = onTextChange,
		onSearchClicked = onSearchClicked,
		onCloseClicked = onCloseClicked
	)
}

@Composable
fun SearchWidget(
	text: String,
	onTextChange: (String) -> Unit,
	onSearchClicked: (String) -> Unit,
	onCloseClicked: () -> Unit,
) {
	//// Container ////
	Surface(
		modifier = Modifier
			.fillMaxWidth()
			.height(TOP_APP_BAR_HEIGHT)
			.semantics {
				contentDescription = "SearchWidget"
			},
		elevation = AppBarDefaults.TopAppBarElevation,
		color = MaterialTheme.colors.topBarBgColor
	) {

		//// Search Text Field ////
		TextField(
			modifier = Modifier
				.fillMaxWidth()
				.semantics {
					contentDescription = "TextField"
				},
			value = text,
			onValueChange = { onTextChange(it) },
			//// PlaceHolder ////
			placeholder = {
				Text(
					modifier = Modifier
						.alpha(ContentAlpha.medium),
					text = stringResource(R.string.search_placeholder),
					color = Color.White
				)
			},
			textStyle = TextStyle(
				color = MaterialTheme.colors.topBarContentColor
			),
			singleLine = true,
			//// Search Icon ////
			leadingIcon = {
				IconButton(
					modifier = Modifier
						.alpha(ContentAlpha.medium),
					onClick = {}
				) {
					Icon(
						imageVector = Icons.Default.Search,
						contentDescription = stringResource(id = R.string.search_icon),
						tint = MaterialTheme.colors.topBarContentColor
					)
				}
			},
			//// Close Icon ////
			trailingIcon = {
				IconButton(
					modifier = Modifier
						.semantics {
							contentDescription = "CloseButton"
						},
					onClick = {
						if (text.isNotEmpty()) {
							onTextChange("")
						} else {
							onCloseClicked()
						}
					}
				) {
					Icon(
						imageVector = Icons.Default.Close,
						contentDescription = stringResource(R.string.close_icon),
						tint = MaterialTheme.colors.topBarContentColor
					)
				}
			},
			//// Keyboard Search Option ////
			keyboardOptions = KeyboardOptions(
				imeAction = ImeAction.Search
			),
			//// Keyboard onSearch Option clicked ////
			keyboardActions = KeyboardActions(
				onSearch = {
					onSearchClicked(text)
				}
			),
			colors = TextFieldDefaults.textFieldColors(
				backgroundColor = Color.Transparent,
				cursorColor = MaterialTheme.colors.topBarContentColor,
				focusedIndicatorColor = Color.Transparent,
				unfocusedIndicatorColor = Color.Transparent
			)
		)

	}
}

@Preview
@Composable
fun SearchWidgetPrev() {
	SearchWidget(
		text = "",
		onTextChange = {},
		onSearchClicked = {},
		onCloseClicked = {}
	)
}