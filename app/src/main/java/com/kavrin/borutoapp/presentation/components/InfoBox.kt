package com.kavrin.borutoapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.kavrin.borutoapp.R
import com.kavrin.borutoapp.ui.theme.EXTRA_SMALL_PADDING
import com.kavrin.borutoapp.ui.theme.INFO_ICON_SIZE
import com.kavrin.borutoapp.ui.theme.titleColor

@Composable
fun InfoBox(
	icon: Painter,
	iconColor: Color,
	bigText: String,
	smallText: String,
	textColor: Color
) {
	//// Icon Container ////
	Row(
		verticalAlignment = Alignment.CenterVertically
	) {
		//// Icon ////
		Icon(
			modifier = Modifier
			    .size(INFO_ICON_SIZE),
			painter = icon,
			contentDescription = "Info Icon",
			tint = iconColor
		)
		//// Gap ////
		Spacer(modifier = Modifier.width(EXTRA_SMALL_PADDING))

		//// Text Container ////
		Column(
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			//// Big Text ////
			Text(
				text = bigText,
				color = textColor,
				fontSize = MaterialTheme.typography.h6.fontSize,
				fontWeight = FontWeight.Black
			)
			//// Small Text ////
			Text(
				modifier = Modifier
				    .alpha(ContentAlpha.medium),
				text = smallText,
				color = textColor,
				fontSize = MaterialTheme.typography.overline.fontSize
			)
		}
	}
}


@Preview(showBackground = true)
@Composable
fun InfoBoxPre() {
	InfoBox(
		icon = painterResource(id = R.drawable.bolt),
		iconColor = MaterialTheme.colors.primary,
		bigText = "95",
		smallText = "Power",
		textColor = MaterialTheme.colors.titleColor
	)
}