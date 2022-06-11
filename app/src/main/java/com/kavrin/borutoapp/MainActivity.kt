package com.kavrin.borutoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kavrin.borutoapp.navigation.SetupNavGraph
import com.kavrin.borutoapp.ui.theme.BorutoAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BorutoAppTheme {
                // Initialize navController
                navController = rememberNavController()
                // Setup NavGraph and pass navController
                SetupNavGraph(navController = navController)

            }
        }
    }
}