package com.kavrin.borutoapp.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.kavrin.borutoapp.ui.theme.EXTRA_SMALL_PADDING

@Composable
fun OrderedList(
	title: String,
	items: List<String>,
	textColor: Color
) {
	Column {
		//// Title ////
		Text(
			text = title,
			color = textColor,
			fontSize = MaterialTheme.typography.subtitle1.fontSize,
			fontWeight = FontWeight.Bold
		)
		//// Gap ////
		Spacer(modifier = Modifier.height(EXTRA_SMALL_PADDING))

		//// List ////
		items.forEachIndexed { i, item ->
			Text(
				modifier = Modifier
				    .alpha(ContentAlpha.medium),
				text = "${i + 1}. $item",
				color = textColor,
				fontSize = MaterialTheme.typography.body1.fontSize
			)
		}
	}
}


@Preview(showBackground = true)
@Composable
fun OrderListPrev() {
	OrderedList(
		title = "Family",
		items = listOf("Fugaku", "Mioto", "itachi", "sdasad", "ipsum"),
		textColor = Color.Black
	)
}