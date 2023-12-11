package com.example.kotlin_tracktic

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavController, onBackClick: () -> Unit) {

    var nameValue by remember { mutableStateOf("") }
    var emailValue by remember { mutableStateOf("") }
    var mobileValue by remember { mutableStateOf("") }

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
                    text = "Edit Profile",
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    // padding between text and icon


                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Column(modifier = Modifier.padding(top = 5.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.dogpuppy),

                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(100.dp)
                        .clip(RoundedCornerShape(50))
                        .background(Color.LightGray)
                    // give gap between image and border

//                        .border(BorderStroke(width = 2.dp, color = Color.Black), CircleShape)
                )
                Row {
                    Text(
                        text = "Change Profile Picture",
                        modifier = Modifier
                            .padding(top = 5.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = "Icon",
                        modifier = Modifier
                            .size(10.dp),
                        tint = LocalContentColor.current
                    )
                }


            }
            Text(
                text = "Share your life to the world",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 5.dp),
                fontSize = 12.sp,
                color = Color.LightGray
            )

            Column(modifier = Modifier.padding(top = 10.dp)) {
                // give icon left side of outlinetextfield

                OutlinedTextField(
                    value = nameValue,
                    leadingIcon = {Icon(imageVector = Icons.Default.Person, contentDescription = "Icon")},
                    label = { Text(text = "Full Name", fontSize = 12.sp, color = Color.LightGray
                        , fontWeight = FontWeight.Bold

                    )},

                    onValueChange = { newValue ->
                        nameValue = newValue.toString()
                    },
//                    maxLines = 1,

                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                    textStyle = TextStyle(color = Color.Black, fontSize = 12.sp),
                    modifier = Modifier
                        .background(Color.White),

//                    border = BorderStroke(1.dp, Color.LightGray),
                    shape = RoundedCornerShape(30),
                )
            }

            Column(modifier = Modifier.padding(top = 10.dp)) {
                OutlinedTextField(
                    value = emailValue,
                    leadingIcon = {Icon(imageVector = Icons.Default.Email, contentDescription = "Icon")},
                    label = { Text(text = "Email", fontSize = 12.sp, color = Color.LightGray, fontWeight = FontWeight.Bold) },


                    onValueChange = { newValue ->
                        emailValue = newValue.toString()
                    },
                    maxLines = 1,

                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                    textStyle = TextStyle(color = Color.Black, fontSize = 12.sp),
                    modifier = Modifier
                        .background(Color.White),

                    shape = RoundedCornerShape(30),
                )
            }

            Column(modifier = Modifier.padding(top = 10.dp)) {
                OutlinedTextField(
                    value = mobileValue,
                    leadingIcon = {Icon(imageVector = Icons.Default.Phone, contentDescription = "Icon")},

                    label = { Text(text = "Mobile Number", fontSize = 12.sp, color = Color.LightGray, fontWeight = FontWeight.Bold
                    ) },


                    onValueChange = { newValue ->
                        mobileValue = newValue.toString()
                    },
                    maxLines = 1,

                    colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                    textStyle = TextStyle(color = Color.Black, fontSize = 12.sp),
                    modifier = Modifier
                        .background(Color.White),

                    shape = RoundedCornerShape(30),


                    )
            }

            // Button
            Column(modifier = Modifier.padding(top = 100.dp)) {
                val context = LocalContext.current

                OutlinedButton(
                    onClick = {
                        Toast.makeText(context, "Updated!", Toast.LENGTH_SHORT).show()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = com.example.kotlin_tracktic.ui.theme.Red30,
                        contentColor = Color.Black
                    ),
                    border = BorderStroke(1.dp, Color.LightGray),
                    shape = RoundedCornerShape(20),
                    modifier = Modifier
                        .width(278.dp)
                        .padding(0.dp)
                ) {
                    Row {
                        // Icon
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Icon",
                            modifier = Modifier
                                .size(30.dp)
                                .padding(end = 10.dp),
                            tint = LocalContentColor.current
                        )

                        Text(
                            text = "Update",
                            style = TextStyle(
                                color = com.example.kotlin_tracktic.ui.theme.Red40,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .padding(top = 3.dp),



                            )
                    }
                }
            }

            BottomNavigation(navController = navController)
        }
    }}