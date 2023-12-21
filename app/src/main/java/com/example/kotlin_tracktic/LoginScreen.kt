package com.example.kotlin_tracktic

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kotlin_tracktic.ui.theme.Red40
import com.example.kotlin_tracktic.ui.theme.Red30
import com.example.kotlin_tracktic.ui.theme.Yellow30
import com.example.kotlin_tracktic.ui.theme.Purple40
import com.google.firebase.auth.FirebaseAuth


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var text by remember {
        mutableStateOf("")
    }
    var password by rememberSaveable { mutableStateOf("") }

    val onClick = {
        navController.navigate(Screen.RegisterScreen.route)
    }

    val auth = FirebaseAuth.getInstance()

    val loginuser: (String, String) -> Unit = { email, password ->
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    navController.navigate(Screen.StatisticScreen.route)
                }
            }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(horizontal = 50.dp)
    ) {
        Text(
            text = "TRACKTIC",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.W800
            )
        )
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Icon"
                )
            },
            label = {
                Text(
                    text = "Email",
                    fontSize = 12.sp,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
            modifier = Modifier
                .background(Color.White)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "Icon"
                )
            },
            label = {
                Text(
                    text = "Password",
                    fontSize = 12.sp,
                    color = Color.LightGray,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
            textStyle = TextStyle(color = Color.Black, fontSize = 16.sp),
            modifier = Modifier
                .background(Color.White),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
//                navController.navigate(Screen.StatisticScreen.route)
                loginuser(text, password)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple40
            ),
            modifier = Modifier.align(Alignment.End)
                .fillMaxWidth()
        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Divider()
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                navController.navigate(Screen.RegisterScreen.route)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Purple40
            ),
            modifier = Modifier.align(Alignment.End)
                .fillMaxWidth()
        ) {
            Text(
                text = "Don't have an account yet? Register here",
                fontSize = 12.sp,
                color = Purple40
            )
        }

    }
}