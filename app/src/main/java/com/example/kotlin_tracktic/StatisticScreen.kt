package com.example.kotlin_tracktic

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kotlin_tracktic.ui.theme.Kotlin_trackticTheme
import com.example.kotlin_tracktic.ui.theme.Purple40
import com.example.kotlin_tracktic_theincredibles.R

@Composable
fun StatisticScreen(navController: NavController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .padding(top = 20.dp)
            ) {
                Header()
                Spacer(modifier = Modifier.height(16.dp))

                ExpenseCard(modifier = Modifier.fillMaxWidth(),"Total Expense", "Rp 3,000")
                Spacer(modifier = Modifier.height(16.dp))

                Row (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "Expense Breakdown",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,)
                        Text("Limit Rp 300,000 / week")
                    }
                    Text("Dropdown")
                }
                Spacer(modifier = Modifier.height(16.dp))

                Column {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ExpenseCard(modifier = Modifier,"Food", "Rp 1,700,000")
                        ExpenseCard(modifier = Modifier,"Household", "Rp 300,000")
                    }
                    Spacer(modifier = Modifier.height(16.dp))

                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        ExpenseCard(modifier = Modifier,"Savings", "Rp 5,000,000")
                        ExpenseCard(modifier = Modifier,"Others", "Rp 10,000")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Payment Links",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,)
                Text(text = "Your current linked accounts")
                Spacer(modifier = Modifier.height(16.dp))

                PaymentCard(painterResource(id = R.drawable.gopay), contentDescription = "gopay")
            }

            BottomNavigation(navController = navController)
        }
    }
}

@Composable
fun Header() {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = "Statistic",
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,)
        Text(text = "Dropdown")
    }
}

@Composable
fun ExpenseCard(
    modifier: Modifier,
    title: String,
    amount: String
) {
    Card(
        modifier = Modifier
            .height(100.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(Purple40)
    ) {
        Box(modifier = Modifier
            .height(150.dp)
            .padding(16.dp)
        ) {
            Column (
                modifier = modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    color = Color.White
                )
                Text(
                    text = amount,
                    color = Color.White
                )
                Column(
                    modifier = modifier
                        .defaultMinSize(minWidth = 130.dp)
                        .height(6.dp)
                        .background(Color.White),
                ) {
                    Text(text = "")
                }
            }
        }
    }
}

@Composable
fun PaymentCard(
    painter: Painter,
    contentDescription: String
) {
    Card (
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier
                .width(360.dp)
                .height(360.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun Preview() {
    Kotlin_trackticTheme {
        StatisticScreen(navController = NavController(LocalContext.current))
    }
}