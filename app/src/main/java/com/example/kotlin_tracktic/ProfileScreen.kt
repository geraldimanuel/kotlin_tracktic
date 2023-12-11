package com.example.kotlin_tracktic

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.SolidColor
import com.example.kotlin_tracktic.ui.theme.Red30
import com.example.kotlin_tracktic.ui.theme.Yellow30
import com.example.kotlin_tracktic.ui.theme.Purple40

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column() {
            Column(modifier = Modifier.padding(16.dp)) {
                Column {
                    Text(
                        text = "Profile",
                        fontSize = 27.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 25.dp)
                    )
                }
            }
            Column(modifier = Modifier.padding(16.dp)
                .align(Alignment.CenterHorizontally)) {
                Column () {
                    Image(
                        painter = painterResource(id = R.drawable.chaeunwoo),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(120.dp)
                            .clip(RoundedCornerShape(50))
                            .background(Color.LightGray)
                    )
                }
            }
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 5.dp, top = 16.dp)
                .align(Alignment.CenterHorizontally)) {
                Column {
                    Text(
                        text = "Cha Eun Woo",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }

            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .align(Alignment.CenterHorizontally)) {
                Column {
                    Text(
                        text = "chaeunwoo@gmail.com",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.LightGray,
                    )
                }
            }

            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 20.dp)) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    ProfileOption(icon = Icons.Filled.Person, label = "Edit Profile", onClick = { }, logoColor = Red30)
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(16.dp))
                    ProfileOption(icon = Icons.Filled.Lock, label = "Terms & Conditions", onClick = { }, logoColor = Purple40)
                    Spacer(modifier = Modifier.height(16.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(16.dp))
                    ProfileOption(icon = Icons.Filled.ExitToApp, label = "Log Out", onClick = { }, logoColor = Yellow30)
                }
            }
            BottomNavigation(
                navController = navController
            )
        }
    }
}

@Composable
fun ProfileOption(icon: ImageVector, label: String, onClick: () -> Unit, logoColor: Color = Color.Unspecified) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick.invoke() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = logoColor
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = label, fontSize = 16.sp)
        }
    }
}