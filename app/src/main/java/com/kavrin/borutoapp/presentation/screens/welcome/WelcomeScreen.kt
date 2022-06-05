package com.kavrin.borutoapp.presentation.screens.welcome

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.google.accompanist.pager.*
import com.kavrin.borutoapp.R
import com.kavrin.borutoapp.domain.model.OnBoardingPage
import com.kavrin.borutoapp.ui.theme.*
import com.kavrin.borutoapp.util.Constants.ON_BOARDING_PAGE_COUNT

@ExperimentalPagerApi
@Composable
fun WelcomeScreen(navController: NavHostController) {
	val pages = listOf(
		OnBoardingPage.First,
		OnBoardingPage.Second,
		OnBoardingPage.Third,
	)

	val pagerState = rememberPagerState()

	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(color = MaterialTheme.colors.welcomeScreenBgColor),
		verticalArrangement = Arrangement.Center
	) {
		// accompanist
		//// Pager ////
		HorizontalPager(
			modifier = Modifier
				.weight(10f),
			state = pagerState,
			count = ON_BOARDING_PAGE_COUNT,
			verticalAlignment = Alignment.CenterVertically
		) { position ->
			PagerScreen(onBoardingPage = pages[position])
		}

		//// Pager Indicator ////
		HorizontalPagerIndicator(
			modifier = Modifier
				.weight(1f)
				.align(Alignment.CenterHorizontally),
			pagerState = pagerState,
			activeColor = MaterialTheme.colors.activeIndicatorColor,
			inactiveColor = MaterialTheme.colors.inactiveIndicatorColor,
			indicatorWidth = PAGING_INDICATOR_WIDTH,
			spacing = PAGING_INDICATOR_SPACING
		)

		FinishButton(
			modifier = Modifier
				.weight(1f),
			pagerState = pagerState
		) {}

	}
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
	Column(
		modifier = Modifier
			.fillMaxWidth(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Top
	) {
		//// OnBoarding Image ////
		Image(
			modifier = Modifier
				.fillMaxWidth(fraction = 0.5f)
				.fillMaxHeight(fraction = 0.6f),
			painter = painterResource(id = onBoardingPage.image),
			contentDescription = stringResource(R.string.on_boarding_image)
		)

		//// OnBoarding Title ////
		Text(
			modifier = Modifier
				.fillMaxWidth(),
			text = onBoardingPage.title,
			color = MaterialTheme.colors.onBoardingTitleColor,
			fontSize = MaterialTheme.typography.h4.fontSize,
			fontWeight = FontWeight.Bold,
			textAlign = TextAlign.Center
		)

		//// Vertical Spacer ////
		Spacer(modifier = Modifier.height(SMALL_PADDING))

		//// OnBoarding Desc. ////
		Text(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = EXTRA_LARGE_PADDING),
			text = onBoardingPage.description,
			color = MaterialTheme.colors.onBoardingDescriptionColor,
			fontSize = MaterialTheme.typography.subtitle1.fontSize,
			fontWeight = FontWeight.Medium,
			textAlign = TextAlign.Center
		)
	}
}

@ExperimentalPagerApi
@Composable
fun FinishButton(
	modifier: Modifier = Modifier,
	pagerState: PagerState,
	onClick: () -> Unit,
) {
	Row(
		modifier = modifier
			.padding(horizontal = EXTRA_LARGE_PADDING),
		verticalAlignment = Alignment.Top,
		horizontalArrangement = Arrangement.Center
	) {
		AnimatedVisibility(
			modifier = Modifier
				.fillMaxWidth(),
			visible = pagerState.currentPage == 2
		) {
			Button(
				onClick = onClick,
				colors = ButtonDefaults.buttonColors(
					backgroundColor = MaterialTheme.colors.buttonBgColor,
					contentColor = Color.White
				)
			) {
				Text(text = "Finish")
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun FirstOnBoardingPage() {
	Column(
		modifier = Modifier
			.fillMaxSize()
	) {
		PagerScreen(onBoardingPage = OnBoardingPage.First)
	}

}

@Preview(showBackground = true)
@Composable
private fun SecondOnBoardingPage() {
	Column(
		modifier = Modifier
			.fillMaxSize()
	) {
		PagerScreen(onBoardingPage = OnBoardingPage.Second)
	}
}

@Preview(showBackground = true)
@Composable
private fun ThirdOnBoardingPage() {
	Column(
		modifier = Modifier
			.fillMaxSize()
	) {
		PagerScreen(onBoardingPage = OnBoardingPage.Third)
	}
}