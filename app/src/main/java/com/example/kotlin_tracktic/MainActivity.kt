package com.example.kotlin_tracktic

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.kotlin_tracktic.ui.theme.Kotlin_trackticTheme
import com.example.kotlin_tracktic.util.SharedViewModel
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val sharedViewModel : SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)

        // Check for the presence of an authentication token
        val hasAuthToken = checkForAuthToken()


        // Decide the initial destination based on the token presence
        val initialDestination = if (hasAuthToken) {
            // print on logcat
             Log.d("MainActivity", "hasAuthToken")
//            navController.navigate(Screen.MainScreen.route)
        } else {
            Log.d("MainActivity", "LoginScreen")
//            navController.navigate(Screen.LoginScreen.route)
        }



        setContent {

            Kotlin_trackticTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(sharedViewModel = sharedViewModel)
                }
            }
        }
    }

    private fun checkForAuthToken(): Boolean {
        // Implement logic to check if the authentication token exists
        // You can use SharedPreferences or any storage mechanism here
        // Return true if token exists, false otherwise
        // For example, if using SharedPreferences:
        val sharedPreferences = getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
        val authToken = sharedPreferences.getString("authToken", null)
        return authToken != null
    }
}