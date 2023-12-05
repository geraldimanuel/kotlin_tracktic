package com.example.kotlin_tracktic

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.TransactionScreen.route,
//        modifier = Modifier
    ) {
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(
            route = Screen.MainScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "Andro"
                    nullable = true
                }
            )
        ){ entry ->
            MainScreen(name = entry.arguments?.getString("name"), navController = navController)
        }

        composable(Screen.StatisticScreen.route) {
            StatisticScreen(navController = navController)
        }
        composable(Screen.TransactionScreen.route) {
            TransactionScreen(navController = navController) {
                navController.popBackStack()
            }
        }
        composable(Screen.NotificationScreen.route) {
            NotificationScreen(navController = navController)
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
    }
}