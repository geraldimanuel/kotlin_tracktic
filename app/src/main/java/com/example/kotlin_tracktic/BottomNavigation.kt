package com.example.kotlin_tracktic

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.kotlin_tracktic.ui.theme.Kotlin_trackticTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Share
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
//import androidx.compose.ui.draw.EmptyBuildDrawCacheParams.size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.kotlin_tracktic.ui.theme.Kotlin_trackticTheme
import com.example.kotlin_tracktic_theincredibles.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigation (navController: NavController) {
    Kotlin_trackticTheme {
        val items = listOf(
            BottomNavigationItem(
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home,
                route = Screen.MainScreen.route + "/{name}",

            ),
            BottomNavigationItem(
                title = "Statistic",
                selectedIcon = Icons.Filled.Share,
                unselectedIcon = Icons.Outlined.Share,
                route = Screen.StatisticScreen.route,
            ),
//            BottomNavigationItem(
//                title = "Add",
//                selectedIcon = Icons.Filled.Add,
//                unselectedIcon = Icons.Outlined.Add,
//                route = Screen.TransactionScreen.route,
//
//            ),
//            BottomNavigationItem(
//                title = "Notif",
//                selectedIcon = Icons.Filled.Notifications,
//                unselectedIcon = Icons.Outlined.Notifications,
//                route = Screen.NotificationScreen.route,
//                badgeCount = 45
//            ),
            BottomNavigationItem(
                title = "Profile",
                selectedIcon = Icons.Filled.Person,
                unselectedIcon = Icons.Outlined.Person,
                route = Screen.ProfileScreen.route,
            ),
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val Pink40 = Color(0xFFE1BEE7)

        var selectedItemIndex by rememberSaveable {
            mutableStateOf(0)
        }
//        Surface(
//            modifier = Modifier
//                .fillMaxWidth()
//                .shadow(20.dp)
//        ) {
            Scaffold(
                bottomBar = {
                    NavigationBar(
                        modifier = Modifier.padding(10.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp))
                            .background(color = Pink40),


                        containerColor = Color(0xFFE0A9A5),


                    ) {
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                selected = currentRoute == item.route,
                                onClick = {
                                    navController.navigate(item.route)
                                },
                                label = {
                                    Text(text = item.title, color = Color.White)

                                },
                                alwaysShowLabel = false,

                                icon = {
                                    BadgedBox(
                                        badge = {
                                            if (item.badgeCount != null) {
                                                Badge {
                                                    Text(text = item.badgeCount.toString())
                                                }
                                            }
                                        }

                                    ) {
                                        Icon(
                                            imageVector = if (currentRoute == item.route) {
                                                item.selectedIcon
                                            } else item.unselectedIcon,
                                            contentDescription = item.title,
                                            modifier = Modifier.size(24.dp),
                                            tint = if (currentRoute == item.route) {
                                                Color(0xFFE0A9A5)
                                            } else {
                                                colorResource(id = R.color.white)
                                            }
                                        )
//                                        if (index == 2) {
//                                            Box(
//                                                modifier = Modifier
//                                                    .size(56.dp)
//                                                    .background(
//                                                        color = White,
//                                                        shape = MaterialTheme.shapes.small,
//                                                    ),
//                                                contentAlignment = Alignment.Center
//                                            ) {
//                                                Icon(
//                                                    imageVector = item.selectedIcon,
//                                                    contentDescription = item.title,
//                                                    modifier = Modifier.size(24.dp),
//                                                    tint = Color(0xFFE0A9A5),
//                                                )
//                                            }
//                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            ) {
            }
        }
//    }
}