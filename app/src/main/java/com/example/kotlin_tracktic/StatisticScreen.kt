package com.example.kotlin_tracktic

import android.util.Log
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.kotlin_tracktic.util.SharedViewModel
import com.example.kotlin_tracktic_theincredibles.R

@Composable
fun StatisticScreen(navController: NavController) {

    val context = LocalContext.current
    val sharedViewModel = remember { SharedViewModel() }

    val totalExpenseState = remember { mutableStateOf("Rp 0") }
    val totalIncomeState = remember { mutableStateOf("Rp 0") }
    val totalState = remember { mutableStateOf("Rp 0") }

    val totalFoodExpense = remember { mutableStateOf("Rp 0") }
    val totalTransportationExpense = remember { mutableStateOf("Rp 0") }
    val totalShopExpense = remember { mutableStateOf("Rp 0") }
    val totalOthersExpense = remember { mutableStateOf("Rp 0") }

    LaunchedEffect(true) {
        var totalIncome = 0.0
        var totalExpense = 0.0

        // Function to calculate total and update the state
        fun updateTotal() {
            // Calculate total only when both income and expense are fetched
            if (totalIncome != 0.0 && totalExpense != 0.0) {
                val total = totalIncome - totalExpense
                totalState.value = "Rp ${String.format("%.2f", total)}"
            }
        }

        // Retrieve total income
        sharedViewModel.retrieveIncome(context) { incomeList ->
            for (income in incomeList) {
                totalIncome += income.nominal
            }
            totalIncomeState.value = "Rp ${String.format("%.2f", totalIncome)}"

            // Calculate total only after retrieving income
            updateTotal()
        }

        // Retrieve total expense
        sharedViewModel.retrieveExpense(context) { expenseList ->
            for (expense in expenseList) {
                totalExpense += expense.nominal
            }
            totalExpenseState.value = "Rp ${String.format("%.2f", totalExpense)}"

            // Calculate total only after retrieving expense
            updateTotal()
        }

        sharedViewModel.retrieveExpense(context) { expenseList ->
            var totalFood = 0.0
            for (expense in expenseList) {
                if (expense.category == "Food") {
                    totalFood += expense.nominal
                }
            }
            totalFoodExpense.value = "Rp ${String.format("%.2f", totalFood)}"
        }

        sharedViewModel.retrieveExpense(context) { expenseList ->
            var totalTransportation = 0.0
            for (expense in expenseList) {
                if (expense.category == "Transportation") {
                    totalTransportation += expense.nominal
                }
            }
            totalTransportationExpense.value = "Rp ${String.format("%.2f", totalTransportation)}"
        }

        sharedViewModel.retrieveExpense(context) { expenseList ->
            var totalShop = 0.0
            for (expense in expenseList) {
                if (expense.category == "Shop") {
                    totalShop += expense.nominal
                }
            }
            totalShopExpense.value = "Rp ${String.format("%.2f", totalShop)}"
        }

        sharedViewModel.retrieveExpense(context) { expenseList ->
            var totalOthers = 0.0
            for (expense in expenseList) {
                if (expense.category == "Others") {
                    totalOthers += expense.nominal
                }
            }
            totalOthersExpense.value = "Rp ${String.format("%.2f", totalOthers)}"
        }
    }



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

                ExpenseCard(modifier = Modifier.fillMaxWidth(),"Total Income", totalIncomeState.value)
                Spacer(modifier = Modifier.height(16.dp))
                ExpenseCard(modifier = Modifier.fillMaxWidth(),"Total Expense", totalExpenseState.value)
                Spacer(modifier = Modifier.height(16.dp))
                ExpenseCard(modifier = Modifier.fillMaxWidth(),"Total", totalState.value)
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
//                        Text("Limit Rp 300,000 / week")
                    }
//                    Text("Dropdown")
                }
                Spacer(modifier = Modifier.height(16.dp))

                Column( modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ExpenseCard(
                            modifier = Modifier.weight(1f),
                            title = "Food",
                            amount = totalFoodExpense.value
                        )
                        ExpenseCard(
                            modifier = Modifier.weight(1f),
                            title = "Transportation",
                            amount = totalTransportationExpense.value
                        )
                    }
//                    Spacer(modifier = Modifier.height(8.dp))

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        ExpenseCard(
                            modifier = Modifier.weight(1f),
                            title = "Shop",
                            amount = totalShopExpense.value
                        )
                        ExpenseCard(
                            modifier = Modifier.weight(1f),
                            title = "Others",
                            amount = totalOthersExpense.value
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

//                Text(text = "Payment Links",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.SemiBold,)
//                Text(text = "Your current linked accounts")
//                Spacer(modifier = Modifier.height(16.dp))
//
//                PaymentCard(painterResource(id = R.drawable.gopay), contentDescription = "gopay")
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
//        Text(text = "Dropdown")
    }
}

@Composable
fun ExpenseCard(
    modifier: Modifier,
    title: String,
    amount: String
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(Purple40)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth() // Menyesuaikan lebar dengan container
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = amount,
                color = Color.White
            )
//            LinearProgressIndicator(
//                progress = 0.5f, // Setel nilai progres yang diperlukan (0.0f hingga 1.0f)
//                modifier = Modifier
//                    .height(6.dp)
//                    .fillMaxWidth() // Menyesuaikan lebar dengan container
//            )
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