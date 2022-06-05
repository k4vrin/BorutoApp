package com.kavrin.borutoapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.kavrin.borutoapp.presentation.screens.splash.SplashScreen
import com.kavrin.borutoapp.presentation.screens.welcome.WelcomeScreen
import com.kavrin.borutoapp.util.Constants.DETAILS_ARGUMENT_KEY

/**
 * Setup nav graph
 *
 * @param navController
 *
 * Setup navGraph with Screens to define all destinations that app use
 * Should be called from main activity
 */
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
	// Define NavHost(start destination)
	NavHost(
		navController = navController,
		startDestination = Screen.Welcome.route
		// Define all destination
	) {
		//// Splash Screen ////
		composable(route = Screen.Splash.route) {
			// Define actual composable screen
			SplashScreen(navController = navController)
		}
		//// On-boarding Screen ////
		composable(route = Screen.Welcome.route) {
			// Define actual composable screen
			WelcomeScreen(navController = navController)

		}
		//// Home Screen ////
		composable(
			route = Screen.Home.route,
			// Define required arg
			arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
				type = NavType.IntType
			})
		) {
			// Define actual composable screen

		}
		//// Details Screen ////
		composable(route = Screen.Detail.route) {
			// Define actual composable screen

		}
		//// Search Screen ////
		composable(route = Screen.Search.route) {
			// Define actual composable screen

		}
	}
}