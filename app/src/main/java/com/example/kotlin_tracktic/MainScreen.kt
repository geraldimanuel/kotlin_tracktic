package com.example.kotlin_tracktic

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kotlin_tracktic.ui.theme.Purple40

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(name: String?, navController: NavController) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = CenterHorizontally
        ) {
            Text(text = "Hello, $name")
            BottomNavigation(navController = navController)
        }
    }
}

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//        }
//    }
//}
//
@Composable
fun Profile(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = Modifier
        .height(100.dp)
        .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                modifier = Modifier
                    .height(100.dp)
                    .background(Color.Black)
            )
            Spacer(modifier = Modifier.width(32.dp))
            Column (
                modifier = Modifier
                    .height(100.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    color = Color.Black
                )
                Text(
                    text = title,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.width(90.dp))
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
            .padding(16.dp)
        ) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Happy Meal",
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.White
                )
                Text(
                    text = "Happy Meal",
                    modifier = Modifier
                        .fillMaxWidth(),
                    color = Color.White
                )
                Text(
                    text = "Happy Meal",
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
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(100.dp)
            .background(Color.White),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Box(modifier = Modifier
            .height(150.dp)
            .padding(16.dp)
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
                        text = "Happy Meal",
                        color = Color.Black
                    )
                    Text(
                        text = "Happy Meal",
                        color = Color.Black
                    )
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Happy Meal",
                        color = Color.Black
                    )
                    Text(
                        text = "Happy Meal",
                        color = Color.Black
                    )
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = "Happy Meal",
                        color = Color.Black
                    )
                    Text(
                        text = "Happy Meal",
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
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Image(
                modifier = Modifier
                    .height(50.dp),
                painter = painterResource(id = R.drawable.cinammoroll),
                contentDescription = "Logo")
            Icon(
                modifier = Modifier
                    .height(50.dp),
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = "Search")
        }
        Profile(
            painter = painterResource(id = R.drawable.cinammoroll),
            contentDescription = "Happy Meal",
            title = "Happy Meal"
        )
        MoneyCard(modifier = Modifier.padding(16.dp))
        Text(
            text = "Expense List",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        )
        ExpenseCard(modifier = Modifier.padding(16.dp))
        ExpenseCard(modifier = Modifier.padding(16.dp))
    }
}