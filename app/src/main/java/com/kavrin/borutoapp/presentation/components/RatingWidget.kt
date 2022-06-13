package com.kavrin.borutoapp.presentation.components

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.kavrin.borutoapp.R
import com.kavrin.borutoapp.ui.theme.EXTRA_SMALL_PADDING
import com.kavrin.borutoapp.ui.theme.LightGray
import com.kavrin.borutoapp.ui.theme.STAR_WIDGET_SIZE
import com.kavrin.borutoapp.ui.theme.StarColor
import com.kavrin.borutoapp.util.Constants.EMPTY_STARS_KEY
import com.kavrin.borutoapp.util.Constants.FILLED_STARS_KEY
import com.kavrin.borutoapp.util.Constants.HALF_FILLED_STARS_KEY

@Composable
fun RatingWidget(
	rating: Double,
	modifier: Modifier = Modifier,
	scaleFactor: Float = 2f,
	spaceBetween: Dp = EXTRA_SMALL_PADDING,
) {
	val result = calcStars(rating = rating)

	val starPathString = stringResource(id = R.string.star_path)
	val starPath = remember {
		PathParser().parsePathString(pathData = starPathString).toPath()
	}
	val starPathBounds = remember {
		starPath.getBounds()
	}

	//// Container ////
	Row(
		modifier = modifier,
		horizontalArrangement = Arrangement.spacedBy(spaceBetween)
	) {
		//// Filled Stars ////
		result[FILLED_STARS_KEY]?.let {
			repeat(it) {
				FilledStar(
					starPath = starPath,
					starPathBounds = starPathBounds,
					scaleFactor = scaleFactor
				)
			}
		}

		//// Half Filled Stars ////
		result[HALF_FILLED_STARS_KEY]?.let {
			repeat(it) {
				HalfFilledStar(
					starPath = starPath,
					starPathBounds = starPathBounds,
					scaleFactor = scaleFactor
				)
			}
		}

		//// Empty Stars ////
		result[EMPTY_STARS_KEY]?.let {
			repeat(it) {
				EmptyStar(
					starPath = starPath,
					starPathBounds = starPathBounds,
					scaleFactor = scaleFactor
				)
			}
		}
	}
}


@Composable
fun FilledStar(
	starPath: Path,
	starPathBounds: Rect,
	scaleFactor: Float,
) {

	Canvas(
		modifier = Modifier
			.size(STAR_WIDGET_SIZE)
			.semantics {
				contentDescription = "FilledStar"
			}
	) {
		val canvasSize = size

		scale(scale = scaleFactor) {
			val pathWidth = starPathBounds.width
			val patHeight = starPathBounds.height
			val left = (canvasSize.width / 2f) - (pathWidth / 1.42f)
			val top = (canvasSize.height / 2f) - (patHeight / 1.42f)

			translate(
				left = left,
				top = top
			) {
				drawPath(
					path = starPath,
					color = StarColor
				)
			}
		}
	}

}

@Composable
fun HalfFilledStar(
	starPath: Path,
	starPathBounds: Rect,
	scaleFactor: Float,
) {

	Canvas(
		modifier = Modifier
			.size(STAR_WIDGET_SIZE)
			.semantics {
				contentDescription = "HalfFilledStar"
			}
	) {
		val canvasSize = size

		scale(scale = scaleFactor) {
			val pathWidth = starPathBounds.width
			val patHeight = starPathBounds.height
			val left = (canvasSize.width / 2f) - (pathWidth / 1.42f)
			val top = (canvasSize.height / 2f) - (patHeight / 1.42f)

			translate(
				left = left,
				top = top
			) {
				drawPath(
					path = starPath,
					color = LightGray.copy(alpha = 0.5f)
				)
				clipPath(path = starPath) {
					drawRect(
						color = StarColor,
						size = Size(
							width = starPathBounds.maxDimension / 1.42f,
							height = starPathBounds.maxDimension * scaleFactor
						)
					)
				}
			}
		}
	}
}

@Composable
fun EmptyStar(
	starPath: Path,
	starPathBounds: Rect,
	scaleFactor: Float,
) {
	Canvas(
		modifier = Modifier
			.size(STAR_WIDGET_SIZE)
			.semantics {
				contentDescription = "EmptyStar"
			}
	) {
		val canvasSize = size

		scale(scale = scaleFactor) {
			val pathWidth = starPathBounds.width
			val patHeight = starPathBounds.height
			val left = (canvasSize.width / 2f) - (pathWidth / 1.42f)
			val top = (canvasSize.height / 2f) - (patHeight / 1.42f)

			translate(
				left = left,
				top = top
			) {
				drawPath(
					path = starPath,
					color = LightGray.copy(alpha = 0.5f)
				)
			}
		}
	}
}

@Composable
fun calcStars(
	rating: Double,
): Map<String, Int> {

	val maxStars by remember { mutableStateOf(5) }
	var filledStars by remember { mutableStateOf(0) }
	var halfFilledStars by remember { mutableStateOf(0) }
	var emptyStars by remember { mutableStateOf(0) }

	LaunchedEffect(key1 = rating) {
		val (firstNumber, lastNumber) = rating.toString()
			.split(".")
			.map { it.toInt() }
		if (firstNumber in 0..5 && lastNumber in 0..9) {
			filledStars = firstNumber
			if (lastNumber in 1..5) {
				halfFilledStars++
			}
			if (lastNumber in 6..9) {
				filledStars++
			}
			if (firstNumber == 5 && lastNumber > 0) {
				emptyStars = maxStars
				filledStars = 0
				halfFilledStars = 0
			}
		} else {
			Log.d("RatingWidget", "calcStars: Invalid rating Number")
		}
	}

	emptyStars = maxStars - (filledStars + halfFilledStars)
	return mapOf(
		FILLED_STARS_KEY to filledStars,
		HALF_FILLED_STARS_KEY to halfFilledStars,
		EMPTY_STARS_KEY to emptyStars
	)
}


@Preview(showBackground = true)
@Composable
fun FilledStarPrev() {
	val starPathString = stringResource(id = R.string.star_path)
	val starPath = remember {
		PathParser().parsePathString(pathData = starPathString).toPath()
	}
	val starPathBounds = remember {
		starPath.getBounds()
	}
	FilledStar(
		starPath = starPath,
		starPathBounds = starPathBounds,
		scaleFactor = 3f
	)
}


@Preview(showBackground = true)
@Composable
fun HalfStarPrev() {
	val starPathString = stringResource(id = R.string.star_path)
	val starPath = remember {
		PathParser().parsePathString(pathData = starPathString).toPath()
	}
	val starPathBounds = remember {
		starPath.getBounds()
	}
	HalfFilledStar(
		starPath = starPath,
		starPathBounds = starPathBounds,
		scaleFactor = 3f
	)
}

@Preview(showBackground = true)
@Composable
fun EmptyStarPrev() {
	val starPathString = stringResource(id = R.string.star_path)
	val starPath = remember {
		PathParser().parsePathString(pathData = starPathString).toPath()
	}
	val starPathBounds = remember {
		starPath.getBounds()
	}
	EmptyStar(
		starPath = starPath,
		starPathBounds = starPathBounds,
		scaleFactor = 3f
	)
}