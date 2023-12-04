package com.example.kotlin_tracktic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class ButtonData(val label: String, var isPressed: Boolean)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(navController: NavController, onBackClick: () -> Unit) {

    val buttons = remember {
        listOf(
            ButtonData("20,000", false),
            ButtonData("50,000", false),
            ButtonData("100,000", false),
        )
    }

    val buttons2 = remember {
        listOf(
            ButtonData("200,000", false),
            ButtonData("300,000", false),
            ButtonData("500,000", false)
        )
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        // HEADER
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            onBackClick.invoke()
                        }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.fillMaxSize(),
                        tint = LocalContentColor.current
                    )
                }
                Text(
                    text = "Add Transaction",
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
        }

            // Toggle Category
            Column() {
                var value by remember { mutableStateOf(0) }

                TextField(
                    value = value.toString(),
                    onValueChange = {
                        value = it.toIntOrNull() ?: 0
                    },
                    label = { Text(
                        text = "Input Nominal",
                        style = TextStyle(color = Color.White, fontSize = 14.sp),
                        modifier = Modifier
                            .padding(top = 20.dp)
                        ) },
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(containerColor = com.example.kotlin_tracktic.ui.theme.Purple40),
                    textStyle = TextStyle(color = Color.White, fontSize = 24.sp,fontWeight = FontWeight.ExtraBold),
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .background(com.example.kotlin_tracktic.ui.theme.Purple40)
                        .border(1.dp, com.example.kotlin_tracktic.ui.theme.Purple40)
                        .height(88.dp)
                )
            }

            Column(modifier = Modifier.padding(top = 10.dp)) {
                Row(modifier = Modifier.width(278.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly) {
                    buttons.forEachIndexed { index, button ->
                        OutlinedButton(
                            onClick = { buttons[index].isPressed = !buttons[index].isPressed },
                            border = BorderStroke(1.dp, Color.LightGray),
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .width(90.dp)
                                .padding(0.dp)
                        ) {
                            Text(
                                text = button.label,
                                style = TextStyle(
                                    color = if (button.isPressed) com.example.kotlin_tracktic.ui.theme.Red40 else Color.Black,
                                    fontSize = 10.sp
                                ),
                                modifier = Modifier.padding(0.dp)
                            )
                        }
                    }
                }
            }

            Column(modifier = Modifier.padding(top = 5.dp)) {
                Row(modifier = Modifier.width(278.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly) {
                    buttons2.forEachIndexed { index, button ->
                        OutlinedButton(
                            onClick = { buttons[index].isPressed = !buttons[index].isPressed },
                            border = BorderStroke(1.dp, Color.LightGray),
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .width(90.dp)
                                .padding(0.dp)
                        ) {
                            Text(
                                text = button.label,
                                style = TextStyle(
                                    color = if (button.isPressed) com.example.kotlin_tracktic.ui.theme.Red40 else Color.Black,
                                    fontSize = 10.sp
                                ),
                                modifier = Modifier.padding(0.dp)
                            )
                        }
                    }
                }
            }

//            // Input Nominal
//            Column(modifier = Modifier.padding(top = 20.dp)) {
//                Row(modifier = Modifier.width(278.dp),
//                    horizontalArrangement = Arrangement.SpaceEvenly){
//                    OutlinedButton(onClick = { isPressed = !isPressed },
//                        border = BorderStroke(1.dp, Color.LightGray),
//                        shape = RoundedCornerShape(20),
//                        modifier = Modifier
//                            .width(90.dp)
//                            .padding(0.dp)
//                    ) {
//                        Text(text = "20,000",
//                            style = TextStyle(color = (if(isPressed){com.example.kotlin_tracktic.ui.theme.Red40}else{Color.Black}), fontSize = 10.sp),
//                            modifier = Modifier
//                                .padding(0.dp))
//                    }
//                    OutlinedButton(onClick = { isPressed2 = !isPressed2 },
//                        border = BorderStroke(1.dp, Color.LightGray),
//                        shape = RoundedCornerShape(20),
//                        modifier = Modifier
//                            .width(90.dp)
//                            .padding(0.dp)
//                    ) {
//                        Text(text = "50,000",
//                            style = TextStyle(color = (if(isPressed2){com.example.kotlin_tracktic.ui.theme.Red40}else{Color.Black}), fontSize = 10.sp),
//                            modifier = Modifier
//                                .padding(0.dp))
//                    }
//                    OutlinedButton(onClick = { isPressed3 = !isPressed3 },
//                        border = BorderStroke(1.dp, Color.LightGray),
//                        shape = RoundedCornerShape(20),
//                        modifier = Modifier
//                            .width(90.dp)
//                            .padding(0.dp)
//                    ) {
//                        Text(text = "100,000",
//                            style = TextStyle(color = (if(isPressed3){com.example.kotlin_tracktic.ui.theme.Red40}else{Color.Black}), fontSize = 10.sp),
//                            modifier = Modifier
//                                .padding(0.dp))
//                    }
//                }
//            }
//
//            // Input Nominal
//            Column(modifier = Modifier.padding(top = 10.dp)) {
//                Row(modifier = Modifier.width(278.dp),
//                    horizontalArrangement = Arrangement.SpaceEvenly){
//                    OutlinedButton(onClick = { isPressed4 = !isPressed4 },
//                        border = BorderStroke(1.dp, Color.LightGray),
//                        shape = RoundedCornerShape(20),
//                        modifier = Modifier
//                            .width(90.dp)
//                            .padding(0.dp)
//                    ) {
//                        Text(text = "200,000",
//                            style = TextStyle(color = (if(isPressed4){com.example.kotlin_tracktic.ui.theme.Red40}else{Color.Black}), fontSize = 10.sp),
//                            modifier = Modifier
//                                .padding(0.dp))
//                    }
//                    OutlinedButton(onClick = { isPressed5 = !isPressed5 },
//                        border = BorderStroke(1.dp, Color.LightGray),
//                        shape = RoundedCornerShape(20),
//                        modifier = Modifier
//                            .width(90.dp)
//                            .padding(0.dp)
//                    ) {
//                        Text(text = "300,000",
//                            style = TextStyle(color = (if(isPressed5){com.example.kotlin_tracktic.ui.theme.Red40}else{Color.Black}), fontSize = 10.sp),
//                            modifier = Modifier
//                                .padding(0.dp))
//                    }
//                    OutlinedButton(onClick = { isPressed6 = !isPressed6 },
//                        border = BorderStroke(1.dp, Color.LightGray),
//                        shape = RoundedCornerShape(20),
//                        modifier = Modifier
//                            .width(90.dp)
//                            .padding(0.dp)
//                    ) {
//                        Text(text = "500,000",
//                            style = TextStyle(color = (if(isPressed6){com.example.kotlin_tracktic.ui.theme.Red40}else{Color.Black}), fontSize = 10.sp),
//                            modifier = Modifier
//                                .padding(0.dp))
//                    }
//                }
//            }

            // Transaction Category
            Column() {

            }

            // Date
            Column() {

            }

            // Remarks
            Column() {

            }

            BottomNavigation(navController = navController)
    }
}}