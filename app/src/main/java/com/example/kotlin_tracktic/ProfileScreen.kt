package com.example.kotlin_tracktic

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
//import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


data class ButtonProfile(val label: String, val isPressedState: MutableState<Boolean>)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, onBackClick: () -> Unit) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // HEADER
            Column() {
                Column {
                    Text(
                        text = "Profile",
                        fontSize = 27.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(40.dp)
                    )
                }

            }
            Icon(
                imageVector = Icons.Default.Create,
                contentDescription = "Edit Profile",
                modifier = Modifier
                    .size(10.dp)
                    .clickable{
                        navController.navigate(MainDestinations.EDIT_PROFILE_ROUTE)
                    }
            )

                BottomNavigation(navController = navController)
            }
        }

object MainDestinations{
    const val PROFILE_ROUTE = "profile"
    const val EDIT_PROFILE_ROUTE = "edit_profile"
}

@Composable
fun SetupNavHost(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = MainDestinations.PROFILE_ROUTE){
        composable(MainDestinations.PROFILE_ROUTE){
            ProfileScreen(navController = navController, onBackClick = { navController.popBackStack() })
        }
        composable(MainDestinations.EDIT_PROFILE_ROUTE){
            EditProfileScreen(navController = navController, onBackClick = { navController.popBackStack() })
        }
    }

}

