package com.kavrin.borutoapp.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.kavrin.borutoapp.util.Constants.DARK_VIBRANT
import com.kavrin.borutoapp.util.Constants.ON_DARK_VIBRANT
import com.kavrin.borutoapp.util.Constants.VIBRANT

object PaletteGenerator {

	suspend fun convertImageUrlToBitmap(
		imageUrl: String,
		context: Context
	): Bitmap? {
		val loader = ImageLoader(context = context)
		val request = ImageRequest.Builder(context = context)
			.data(imageUrl)
			.allowHardware(false)
			.build()
		val imageResult = loader.execute(request = request)
		return if (imageResult is SuccessResult)
			(imageResult.drawable as BitmapDrawable).bitmap
		else
			null
	}

	fun extractColorFromBitmap(bitmap: Bitmap): Map<String, String> {
		return mapOf(
			VIBRANT to parseColorSwatch(color = Palette.from(bitmap).generate().vibrantSwatch),
			DARK_VIBRANT to parseColorSwatch(color = Palette.from(bitmap).generate().darkVibrantSwatch),
			ON_DARK_VIBRANT to parseBodyColor(color = Palette.from(bitmap).generate().darkVibrantSwatch?.bodyTextColor)
		)
	}

	private fun parseColorSwatch(color: Palette.Swatch?): String {
		return if (color != null) {
			val parsedColor = Integer.toHexString(color.rgb)
			"#$parsedColor"
		} else {
			"#000000"
		}
	}

	private fun parseBodyColor(color: Int?): String {
		return if (color != null) {
			val parsedColor = Integer.toHexString(color)
			"#$parsedColor"
		} else {
			"#FFFFFF"
		}
	}
}