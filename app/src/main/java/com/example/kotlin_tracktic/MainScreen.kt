package com.example.kotlin_tracktic

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.kotlin_tracktic.ui.theme.Purple40
import com.example.kotlin_tracktic.ui.theme.Red40
import com.example.kotlin_tracktic.util.SharedViewModel
import com.example.kotlin_tracktic.util.TransactionData
import com.example.kotlin_tracktic_theincredibles.R
import com.google.firebase.auth.FirebaseAuth
import java.util.Date

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, sharedViewModel: SharedViewModel) {
    val context = LocalContext.current
    val data = mutableListOf<TransactionData>()
    // Use remember to create a state variable for holding the data
    val (transactionData, setTransactionData) = remember { mutableStateOf<List<TransactionData>>(emptyList()) }


    val auth = FirebaseAuth.getInstance()

    val painter = rememberImagePainter(
        data = auth.currentUser?.photoUrl,
        builder = {
            // You can add additional options here if needed
            crossfade(true)
        }
    )

    LaunchedEffect(true) {
        sharedViewModel.retrieveData(context) { expenseList ->
            val newData = expenseList.map { pair ->
                val (transactionId, expense) = pair
                TransactionData(
                    id = transactionId,
                    nominal = expense.nominal,
                    category = expense.category,
                    date = expense.date,
                    description = expense.description,
                    type = expense.type
                )
            }
            setTransactionData(newData)
        }
    }




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
//                    androidx.compose.material3.Icon(
//                        modifier = Modifier
//                            .height(50.dp),
//                        painter = painterResource(id = R.drawable.baseline_search_24),
//                        contentDescription = "Search"
//                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            item {
                Profile(
                    painter = painter,
                    contentDescription = "Profile",
                    name = auth.currentUser?.displayName ?: ""
                )
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
            items(transactionData.size) {
//                data.forEach {

                val currentItem = transactionData[it]
                    val image = when (currentItem.category) {
                        "Food" -> R.drawable.food
                        "Electronic" -> R.drawable.electronic
                        "Transportation" -> R.drawable.transportation
                        else -> R.drawable.shop
                    }

                ExpenseCard(
                    modifier = Modifier,
                    date = currentItem.date.toFormattedDateString(),
                    price = currentItem.nominal.toString(),
                    title = currentItem.description,
                    category = currentItem.category,
                    image = image,
                    id = currentItem.id,
                    type = currentItem.type
                )
                Spacer(modifier = Modifier.height(16.dp))
//                }

            }

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                BottomNavigation(navController = navController)
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
                    text = "Rp 1,000 than last month",
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
    image: Int,
    id: String,
    type: String
) {
    val context = LocalContext.current
//    val cardColor = if (type == "Income") Color.Green else Color.White



    Card(
        modifier = modifier
            .background(Color.White)
            .border(1.dp, Color.LightGray, RoundedCornerShape(15.dp)),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Box(
            modifier = Modifier
                .height(150.dp)
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = date,
                        color = Color.Black
                    )

                    OutlinedButton(onClick = {
                        SharedViewModel().deleteData(id, context)
                    },
                        border= null

                    ) {
                        Text(
                            text = "X",
                            style = TextStyle(
                                color = Red40,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(0.dp)
                        )

                    }
                }
                Spacer(modifier = Modifier.height(2.dp))
                Divider(color = Color.LightGray, thickness = 1.dp)
                Spacer(modifier = Modifier.height(2.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row {
                        Image(
                            modifier = Modifier
                                .height(50.dp)
                                .width(50.dp),
                            painter = painterResource(image),
                            contentDescription = "icon"
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(
                                text = if (type == "Income") "Income" else category,
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
    MainScreen(navController = NavController(LocalContext.current), sharedViewModel = SharedViewModel())
}