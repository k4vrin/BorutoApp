package com.kavrin.borutoapp.navigation

/**
 * Screen
 *
 * @property route
 * Enum Class on Steroid!
 * Hold all our screens. each screen has a unique route
 */
sealed class Screen(val route: String) {

	// Splash Screen
	object Splash : Screen(route = "splash_screen")
	// Onboard Screens
	object Welcome : Screen(route = "welcome_screen")
	// Home Screen
	object Home : Screen(route = "home_screen")
	// Detail Screen will accept one argument. In order to display right hero in the screen
	object Detail :
		Screen(route = "detail_screen/{heroId}") {
		// Required argument is defined like "/{userId}"
		// Optional argument is defined like "?userId={userId}"
		fun passHeroId(heroId: Int): String {
			return "detail_screen/${heroId}"
		}
	}

	object Search : Screen(route = "search_screen")
}
