package com.example.kotlin_tracktic

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MainScreen(name: String?, navController: NavController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Hello, $name")

            Spacer(modifier = Modifier.height(16.dp))

            // Buttons for navigation
            Button(
                onClick = {
                    navController.navigate(Screen.StatisticScreen.route)
                }
            ) {
                Text(text = "Statistic")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.TransactionScreen.route)
                }
            ) {
                Text(text = "Transaction")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.NotificationScreen.route)
                }
            ) {
                Text(text = "Notification")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.ProfileScreen.route)
                }
            ) {
                Text(text = "Profile")
            }
        }
    }
}



