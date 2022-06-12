package com.kavrin.borutoapp.presentation.screens.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.kavrin.borutoapp.R
import com.kavrin.borutoapp.domain.model.Hero
import com.kavrin.borutoapp.presentation.components.InfoBox
import com.kavrin.borutoapp.presentation.components.OrderedList
import com.kavrin.borutoapp.ui.theme.*
import androidx.compose.material.rememberBottomSheetScaffoldState
import com.kavrin.borutoapp.util.Constants.ABOUT_TEXT_MAX_LINES


@Composable
fun DetailsContent(
	navController: NavHostController,
	selectedHero: Hero?,
) {
	val bottomSheetState = rememberBottomSheetState(
		initialValue = BottomSheetValue.Expanded
	)

	val scaffoldState = rememberBottomSheetScaffoldState(
		bottomSheetState = bottomSheetState
	)

	BottomSheetScaffold(
		scaffoldState = scaffoldState,
		sheetPeekHeight = MIN_SHEET_HEIGHT,
		sheetContent = {
			selectedHero?.let { BottomSheetContent(selectedHero = it) }
		},
		content = {}
	)

}

@Composable
fun BottomSheetContent(
	selectedHero: Hero,
	infoBoxIconColor: Color = MaterialTheme.colors.primary,
	sheetBackgroundColor: Color = MaterialTheme.colors.surface,
	contentColor: Color = MaterialTheme.colors.titleColor,
) {

	Column(
		modifier = Modifier
			.background(color = sheetBackgroundColor)
			.padding(all = LARGE_PADDING)
	) {
		//// Title and Icon Container ////
		Row(
			modifier = Modifier
				.fillMaxWidth(),
			verticalAlignment = Alignment.CenterVertically
		) {
			//// Icon ////
			Icon(
				modifier = Modifier
					.size(INFO_ICON_SIZE)
					// 20% of width
					.weight(2f),
				painter = painterResource(id = R.drawable.logo),
				contentDescription = stringResource(id = R.string.app_logo),
				tint = contentColor
			)
			//// Title ////
			Text(
				modifier = Modifier
					// 80% width
					.weight(8f),
				text = selectedHero.name,
				color = contentColor,
				fontSize = MaterialTheme.typography.h4.fontSize,
				fontWeight = FontWeight.Bold
			)
		}
		//// Gap ////
		Spacer(modifier = Modifier.height(LARGE_PADDING))

		//// InfoBox Container ////
		Row(
			modifier = Modifier
				.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			//// Bolt InfoBox ////
			InfoBox(
				icon = painterResource(id = R.drawable.bolt),
				iconColor = infoBoxIconColor,
				bigText = "${selectedHero.power}",
				smallText = stringResource(R.string.power),
				textColor = contentColor
			)
			//// Calender InfoBox ////
			InfoBox(
				icon = painterResource(id = R.drawable.calendar),
				iconColor = infoBoxIconColor,
				bigText = selectedHero.month,
				smallText = stringResource(R.string.month),
				textColor = contentColor
			)
			//// Cake InfoBox ////
			InfoBox(
				icon = painterResource(id = R.drawable.cake),
				iconColor = infoBoxIconColor,
				bigText = selectedHero.day,
				smallText = stringResource(R.string.birthday),
				textColor = contentColor
			)
		}

		//// Gap ////
		Spacer(modifier = Modifier.height(MEDIUM_PADDING))

		//// About Title ////
		Text(
			modifier = Modifier
				.fillMaxWidth(),
			text = stringResource(R.string.about),
			color = contentColor,
			fontSize = MaterialTheme.typography.subtitle1.fontSize,
			fontWeight = FontWeight.Bold
		)

		//// About ////
		Text(
			modifier = Modifier
				.alpha(ContentAlpha.medium),
			text = selectedHero.about,
			color = contentColor,
			fontSize = MaterialTheme.typography.body1.fontSize,
			maxLines = ABOUT_TEXT_MAX_LINES
		)

		//// Gap ////
		Spacer(modifier = Modifier.height(MEDIUM_PADDING))

		//// OrderedLists Container ////
		Row(
			modifier = Modifier
				.fillMaxWidth(),
			horizontalArrangement = Arrangement.SpaceBetween
		) {
			//// Family OrderedList ////
			OrderedList(
				title = stringResource(R.string.family),
				items = selectedHero.family,
				textColor = contentColor
			)

			//// Family OrderedList ////
			OrderedList(
				title = stringResource(R.string.abilities),
				items = selectedHero.abilities,
				textColor = contentColor
			)

			//// Family OrderedList ////
			OrderedList(
				title = stringResource(R.string.nature_types),
				items = selectedHero.natureTypes,
				textColor = contentColor
			)

		}
	}
}

@Preview
@Composable
fun BottomSheetContentPreview() {
	BottomSheetContent(
		selectedHero = Hero(
			id = 15,
			name = "Koji",
			image = "/images/koji.jpg",
			about = "Koji Kashin (果心居士, Kashin Koji) is a clone of Jiraiya that was created by Amado for the purpose of killing Isshiki Ōtsutsuki. A former Inner of Kara, he was in charge of the sector on the outskirts of the Land of Fire. An enigmatic man, Koji has a very stoic and straightforward nature that follows a no-nonsense view. Arrogant as he may appear, he has consistently shown himself to be a very rational and perceptive man.",
			rating = 4.5,
			power = 90,
			month = "Jan",
			day = "1st",
			family = listOf(
				"Jiraiya",
				"Jiraiya",
				"Jiraiya",
				"Jiraiya",
				"Jiraiya"
			),
			abilities = listOf(
				"Senin Mode",
				"Rasengan",
				"Shadow Clone"
			),
			natureTypes = listOf(
				"Fire",
				"Earth"
			)
		)
	)
}