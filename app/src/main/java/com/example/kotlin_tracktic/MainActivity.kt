package com.example.kotlin_tracktic

import android.os.Bundle
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
}