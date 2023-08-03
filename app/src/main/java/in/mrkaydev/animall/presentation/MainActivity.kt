package `in`.mrkaydev.animall.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import `in`.mrkaydev.animall.presentation.screens.HomeScreen
import `in`.mrkaydev.animall.presentation.screens.SplashScreen
import `in`.mrkaydev.animall.presentation.viewmodels.MilkSaleViewModel
import `in`.mrkaydev.animall.ui.theme.AnimallTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimallTheme {
                val navController = rememberNavController()
                val viewModel: MilkSaleViewModel = hiltViewModel()
                NavHost(navController = navController, startDestination = "splash") {
                    composable("splash") { SplashScreen(navController) }
                    composable("home") { HomeScreen(navController, viewModel) }
                }
            }
        }
    }
}