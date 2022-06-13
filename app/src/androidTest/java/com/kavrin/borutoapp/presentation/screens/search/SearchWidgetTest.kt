package com.kavrin.borutoapp.presentation.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class SearchWidgetTest {

	@get:Rule
	val composeTestRule = createComposeRule()

	@Test
	fun openSearchWidget_addInputText_assertInputText() {
		val text = mutableStateOf("")

		composeTestRule.setContent {

			SearchWidget(
				text = text.value,
				onTextChange = { text.value = it },
				onSearchClicked = {},
				onCloseClicked = {}
			)
		}

		composeTestRule.onNodeWithContentDescription("TextField")
			.performTextInput("Kavrin")

		composeTestRule.onNodeWithContentDescription("TextField")
			.assertTextEquals("Kavrin")
	}


	@Test
	fun openSearchWidget_addInputText_pressCloseButtonOnce_assertEmptyInputText() {
		val text = mutableStateOf("")

		composeTestRule.setContent {

			SearchWidget(
				text = text.value,
				onTextChange = { text.value = it },
				onSearchClicked = {},
				onCloseClicked = {}
			)
		}

		composeTestRule.onNodeWithContentDescription("TextField")
			.performTextInput("Kavrin")

		composeTestRule.onNodeWithContentDescription("CloseButton")
			.performClick()

		assertTrue(text.value.isEmpty())
	}


	@Test
	fun openSearchWidget_addInputText_pressCloseButtonTwice_assertClosedState() {
		val text = mutableStateOf("")
		val searchWidgetShown = mutableStateOf(true)

		composeTestRule.setContent {

			if (searchWidgetShown.value)
				SearchWidget(
					text = text.value,
					onTextChange = { text.value = it },
					onSearchClicked = {},
					onCloseClicked = {
						searchWidgetShown.value = false
					}
				)
		}

		composeTestRule.onNodeWithContentDescription("TextField")
			.performTextInput("Kavrin")

		composeTestRule.onNodeWithContentDescription("CloseButton")
			.performClick()

		composeTestRule.onNodeWithContentDescription("CloseButton")
			.performClick()

		composeTestRule.onNodeWithContentDescription("SearchWidget")
			.assertDoesNotExist()
	}


	@Test
	fun openSearchWidget_addInputText_pressCloseButtonOnceWhenInputIsEmpty_assertClosedState() {
		val text = mutableStateOf("")
		val searchWidgetShown = mutableStateOf(true)

		composeTestRule.setContent {

			if (searchWidgetShown.value)
				SearchWidget(
					text = text.value,
					onTextChange = { text.value = it },
					onSearchClicked = {},
					onCloseClicked = {
						searchWidgetShown.value = false
					}
				)
		}

		composeTestRule.onNodeWithContentDescription("CloseButton")
			.performClick()

		composeTestRule.onNodeWithContentDescription("SearchWidget")
			.assertDoesNotExist()
	}

}