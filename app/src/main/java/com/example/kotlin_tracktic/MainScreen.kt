package com.example.kotlin_tracktic

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.example.kotlin_tracktic.ui.theme.Purple40
import com.example.kotlin_tracktic.util.SharedViewModel
import com.example.kotlin_tracktic_theincredibles.R
import com.google.firebase.auth.FirebaseAuth

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, sharedViewModel: SharedViewModel) {

    val data = listOf(
        Transactions("Food", "Monday, 09", "Happy Meal", 2500, "Expense"),
        Transactions("Electronic", "Monday, 08", "Transportation", 2500, "Expense"),
        Transactions("Food", "Monday, 09", "Happy Meal", 2500, "Expense"),
        Transactions("Electronic", "Monday, 08", "Transportation", 2500, "Expense"),
        Transactions("Electronic", "Monday, 08", "Transportation", 2500, "Expense"),
        Transactions("Food", "Monday, 09", "Happy Meal", 2500, "Expense"),
        Transactions("Electronic", "Monday, 08", "Transportation", 2500, "Expense"),
    )

    val auth = FirebaseAuth.getInstance()

    Box(modifier = Modifier.fillMaxSize()) {
        val backgroundColor = Color(0xFFFF3881)
        val contentColor = Color.White
        androidx.compose.material3.FloatingActionButton(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 16.dp,
                    end = 16.dp,
                    bottom = 100.dp
                )
                .align(alignment = Alignment.BottomEnd)
                .zIndex(10f),
            onClick = {
                navController.navigate(Screen.TransactionScreen.route)
            },
            containerColor = backgroundColor,
            contentColor = contentColor
        ) {
            androidx.compose.material3.Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "add icon"
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 20.dp,
                    top = 20.dp,
                    end = 20.dp,
                    bottom = 100.dp
                )
                .zIndex(1f)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        modifier = Modifier.padding(top = 12.dp),
                        text = "Tracktic",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Profile(
                    painter = painterResource(id = R.drawable.cinammoroll),
                    contentDescription = "Happy Meal",
                    name = auth.currentUser?.displayName.toString()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                MoneyCard(modifier = Modifier)
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Text(
                    text = "Expense List",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            items(data.size) {
                data.forEach {
                    ExpenseCard(
                        modifier = Modifier,
                        it.date,
                        it.nominal.toString(),
                        it.desc,
                        it.type,
                        it.type
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

            }

        }
        Column(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
        ) {
//            val backgroundColor = Color(0xFFFF3881)
//            val contentColor = Color.White

            Box(modifier = Modifier.fillMaxSize()) {
                BottomNavigation(navController = navController)
//        androidx.compose.material3.FloatingActionButton(
//            modifier = Modifier
//                .padding(
//                    start = 16.dp,
//                    top = 16.dp,
//                    end = 16.dp,
//                    bottom = 100.dp
//                )
//                .align(alignment = Alignment.BottomEnd)
//                .zIndex(10f),
//            onClick = {
//                navController.navigate(Screen.TransactionScreen.route)
//            },
//            containerColor = backgroundColor,
//            contentColor = contentColor
//        ) {
//            androidx.compose.material3.Icon(
//                imageVector = Icons.Default.Add,
//                contentDescription = "add icon"
//            )
//        }
            }
        }
    }

////    BottomNavigation(navController = navController)
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Column(
//            horizontalAlignment = CenterHorizontally
//        ) {
////            Text(text = "Hello, $name")
//            Column (
//                modifier = Modifier
//                    .padding(20.dp),
//            ) {
//
//            }
//
//            BottomNavigation(navController = navController)
//        }
//    }
}

@Composable
fun Profile(
    painter: Painter,
    contentDescription: String,
    name: String
) {
    Box(modifier = Modifier
        .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                modifier = Modifier
                    .clip(CircleShape)
                    .height(50.dp)
                    .background(Color.Black)
            )
            Spacer(modifier = Modifier.width(24.dp))
            Column (
                modifier = Modifier
                    .height(100.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Hello, "+name+"!",
                    color = Color.Black
                )
//                Text(
//                    text = name,
//                    color = Color.Black
//                )
            }
//            Spacer(modifier = Modifier.width(144.dp))
//            Text(
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .wrapContentHeight(Alignment.CenterVertically),
//                text = "Dropdown",
//                color = Color.Black
//            )
        }
    }
}

@Composable
fun MoneyCard(
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(100.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(Purple40)
    ) {
        Box(modifier = Modifier
            .height(165.dp)
            .padding(20.dp)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Expense Total",
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.White
                )
                Text(
                    text = "Rp 7,500",
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.White,
                    fontSize = 24.sp, // Adjust the font size as needed
                    fontWeight = FontWeight.Bold // Optionally, adjust the font weight
                )
//                Text(
//                    text = " Rp 1,000 than last month",
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    color = Color.White
//                )
            }
        }
    }
}

@Composable
fun ExpenseCard(
    modifier: Modifier = Modifier,
    date: String,
    price: String,
    title: String,
    category: String,
    totalPrice: String
) {
    Card(
        modifier = modifier
            .background(Color.White)
            .border(1.dp, Color.LightGray, RoundedCornerShape(15.dp)),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Box(modifier = Modifier
            .height(150.dp)
            .padding(20.dp)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = date,
                        color = Color.Black
                    )
                    Text(
                        text = totalPrice,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Divider(color = Color.LightGray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(2.dp))
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row {
                        Image(
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            painter = painterResource(id = R.drawable.food),
                            contentDescription = "icon"
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = category,
                                color = Color.Black
                            )
                            Text(
                                text = title,
                                color = Color.Black
                            )
                        }
                    }
                    Text(
                        text = price,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

data class Transactions(
    val category: String,
    val date: String,
    val desc: String,
    val nominal: Number,
    val type: String
)