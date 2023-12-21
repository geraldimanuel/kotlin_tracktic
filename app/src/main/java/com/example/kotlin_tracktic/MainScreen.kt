package com.example.kotlin_tracktic

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kotlin_tracktic.ui.theme.Purple40
import com.example.kotlin_tracktic.util.SharedViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(name: String?, navController: NavController, sharedViewModel: SharedViewModel) {

    val data = listOf(
        Transactions("Food", "Monday, 09", "Happy Meal", 2500, "Expense"),
        Transactions("Electronic", "Monday, 08", "Transportation", 2500, "Expense"),
        Transactions("Food", "Monday, 09", "Happy Meal", 2500, "Expense"),
    )

    Scaffold (
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) {
        Box (
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Column (
                                modifier = Modifier
                                    .padding(20.dp),
                            ) {
                                Row (
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                ) {
                                    Text(modifier = Modifier.padding(top = 12.dp), text = "Logo", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
//                                    androidx.compose.material3.Icon(
//                                        modifier = Modifier
//                                            .height(50.dp),
//                                        painter = painterResource(id = R.drawable.baseline_search_24),
//                                        contentDescription = "Search"
//                                    )
                                }
                                Spacer(modifier = Modifier.height(16.dp))

//                                Profile(
//                                    painter = painterResource(id = R.drawable.cinammoroll),
//                                    contentDescription = "Happy Meal",
//                                    name = name ?: "Bella"
//                                )
                                Spacer(modifier = Modifier.height(16.dp))

                                MoneyCard(modifier = Modifier)
                                Spacer(modifier = Modifier.height(16.dp))

                                Text(
                                    text = "Expense List",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                )
                                Spacer(modifier = Modifier.height(16.dp))

                                ExpenseCard(modifier = Modifier, "Monday, 09", "- Rp 2,500", "Happy Meal", "Shop", "- Rp 5,000")
                                Spacer(modifier = Modifier.height(16.dp))

                                ExpenseCard(modifier = Modifier, "Monday, 08", "- Rp 2,500", "Electronic", "Transportation", "- Rp 5,000")
                                ExpenseCard(modifier = Modifier, "Monday, 08", "- Rp 2,500", "Electronic", "Transportation", "- Rp 5,000")
                            }

//            BottomNavigation(navController = navController)
                        }
                    }
                }
            }
        }
    }
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
                    text = "Morning,",
                    color = Color.Black
                )
                Text(
                    text = name,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(144.dp))
            Text(
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically),
                text = "Dropdown",
                color = Color.Black
            )
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
            .height(150.dp)
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
                    color = Color.White
                )
                Text(
                    text = " Rp 1,000 than last month",
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.White
                )
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
            .height(200.dp)
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
//                        Image(
//                            modifier = Modifier
//                                .height(50.dp)
//                                .width(50.dp),
//                            painter = painterResource(id = R.drawable.food),
//                            contentDescription = "icon"
//                        )
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
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row {
//                        Image(
//                            modifier = Modifier
//                                .height(50.dp)
//                                .width(50.dp),
//                            painter = painterResource(id = R.drawable.shop),
//                            contentDescription = "icon"
//                        )
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

@Preview
@Composable
fun DefaultPreview() {
    MainScreen(name = "Bella", navController = NavController(LocalContext.current), sharedViewModel = SharedViewModel())
}

data class Transactions(
    val category: String,
    val date: String,
    val desc: String,
    val nominal: Number,
    val type: String
)