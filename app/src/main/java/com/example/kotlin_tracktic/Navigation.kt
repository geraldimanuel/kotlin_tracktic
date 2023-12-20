package com.example.kotlin_tracktic

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kotlin_tracktic.util.SharedViewModel

@Composable
fun Navigation(
    sharedViewModel: SharedViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route,
//        modifier = Modifier
    ) {
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController)
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
            MainScreen(
                name = entry.arguments?.getString("name"),
                navController = navController,
//                sharedViewModel = sharedViewModel
            )
        }

        composable(Screen.StatisticScreen.route) {
            StatisticScreen(
                navController = navController,
//                sharedViewModel = sharedViewModel
            )
        }
        composable(Screen.TransactionScreen.route) {
            TransactionScreen(navController = navController, sharedViewModel = sharedViewModel){
                navController.popBackStack()
            }
        }
        composable(Screen.EditProfileScreen.route) {
            EditProfileScreen(navController = navController){
                navController.popBackStack()
            }
        }
        composable(Screen.NotificationScreen.route) {
            NotificationScreen(navController = navController)
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController){
                navController.popBackStack()
            }
        }


    }
}