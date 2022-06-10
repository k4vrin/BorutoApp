package com.kavrin.borutoapp.presentation.common

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.kavrin.borutoapp.R
import com.kavrin.borutoapp.domain.model.Hero
import com.kavrin.borutoapp.navigation.Screen
import com.kavrin.borutoapp.presentation.components.RatingWidget
import com.kavrin.borutoapp.ui.theme.*
import com.kavrin.borutoapp.util.Constants.BASE_URL

@ExperimentalCoilApi
@Composable
fun ListContent(
	heroes: LazyPagingItems<Hero>,
	navController: NavHostController
) {
	Log.d("ListContent", "ListContent: ${heroes.loadState}")
	LazyColumn(
		contentPadding = PaddingValues(all = SMALL_PADDING),
		verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
	) {
		items(
			items = heroes,
			key = { hero ->
				hero.id
			}
		) { hero ->
			hero?.let {
				HeroItem(
					hero = hero,
					navController = navController
				)
			}
		}
	}

}


@ExperimentalCoilApi
@Composable
fun HeroItem(
	hero: Hero,
	navController: NavHostController
) {
	val painter = rememberImagePainter(data = "$BASE_URL${hero.image}") {
		placeholder(drawableResId = R.drawable.placeholder)
		error(drawableResId = R.drawable.placeholder)
	}

	Box(
		modifier = Modifier
			.height(HERO_ITEM_HEIGHT)
			.clickable {
				navController.navigate(Screen.Detail.passHeroId(heroId = hero.id))
			},
		contentAlignment = Alignment.BottomStart
	) {

		//// Image ////
		Surface(
			shape = RoundedCornerShape(size = LARGE_PADDING)
		) {
			Image(
				modifier = Modifier
				    .fillMaxSize(),
				painter = painter,
				contentDescription = stringResource(R.string.hero_image),
				contentScale = ContentScale.Crop
			)
		}

		//// Overlay ////
		Surface(
			modifier = Modifier
				.fillMaxWidth()
				.fillMaxHeight(0.4f),
			color = Color.Black.copy(alpha = ContentAlpha.medium),
			shape = RoundedCornerShape(
				bottomStart = LARGE_PADDING,
				bottomEnd = LARGE_PADDING
			)
		) {
			Column(
				modifier = Modifier
					.fillMaxSize()
					.padding(all = MEDIUM_PADDING)
			) {
				//// Title ////
				Text(
					text = hero.name,
					color = MaterialTheme.colors.topBarContentColor,
					fontSize = MaterialTheme.typography.h5.fontSize,
					fontWeight = FontWeight.Bold,
					maxLines = 1,
					overflow = TextOverflow.Ellipsis
				)
				//// About ////
				Text(
					text = hero.about,
					color = Color.White.copy(alpha = ContentAlpha.medium),
					fontSize = MaterialTheme.typography.subtitle1.fontSize,
					maxLines = 3,
					overflow = TextOverflow.Ellipsis
				)
				//// Rating Widget ////
				Row(
					modifier = Modifier
					    .padding(top = SMALL_PADDING),
					verticalAlignment = Alignment.CenterVertically
				) {

					RatingWidget(
						modifier = Modifier
						    .padding(end = SMALL_PADDING),
						rating = hero.rating
					)

					Text(
						text = "(${hero.rating})",
						textAlign = TextAlign.Center,
						color = Color.White.copy(alpha = ContentAlpha.medium)
					)

				}
			}
		}
	}
}

@ExperimentalCoilApi
@Preview
@Composable
fun HeroItemPrev() {
	HeroItem(
		hero = Hero(
			id = 1,
			name = "Sasuke",
			image = "",
			about = "Lorem ipsum dolor sit amet...",
			rating = 4.5,
			power = 100,
			month = "",
			day = "",
			family = listOf(),
			abilities = listOf(),
			natureTypes = listOf()
		),
		navController = rememberNavController()
	)
}





