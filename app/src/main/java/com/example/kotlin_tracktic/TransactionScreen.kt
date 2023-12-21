package com.example.kotlin_tracktic

import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kotlin_tracktic.util.SharedViewModel
import com.example.kotlin_tracktic.util.TransactionData
import com.example.kotlin_tracktic_theincredibles.R
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import androidx.compose.material3.DatePicker
import androidx.compose.runtime.mutableLongStateOf

data class ButtonData(val label: String, val isPressedState: MutableState<Boolean>)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    onBackClick: () -> Unit,

) {

    var textFieldValue by remember { mutableStateOf(0) }
    var remarksValue by remember { mutableStateOf("") }
    var categoryValue by remember { mutableStateOf("") }
    var typeValue by remember { mutableStateOf("") }
    var endDate by rememberSaveable { mutableStateOf(Date()) }
    var endDateMillis by rememberSaveable { mutableStateOf(System.currentTimeMillis()) }



    val buttons = remember {
        listOf(
            ButtonData("20,000", mutableStateOf(false)),
            ButtonData("50,000", mutableStateOf(false)),
            ButtonData("100,000", mutableStateOf(false))
        )
    }

    val buttons2 = remember {
        listOf(
            ButtonData("200,000", mutableStateOf(false)),
            ButtonData("300,000", mutableStateOf(false)),
            ButtonData("500,000", mutableStateOf(false))
        )
    }

    val context = LocalContext.current

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

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                // InputNominal
                TextField(
                    value = textFieldValue.toString(),
                    onValueChange = { newValue ->
                        textFieldValue = newValue.toIntOrNull() ?: 0
                        buttons.forEach { it.isPressedState.value = false }
                        buttons2.forEach { it.isPressedState.value = false }
                    },
                    label = {
                        Text(
                            text = "Input Nominal",
                            style = TextStyle(color = Color.White, fontSize = 14.sp),
                            modifier = Modifier
                                .padding(top = 20.dp)
                        )
                    },
                    maxLines = 1,
                    colors = TextFieldDefaults.textFieldColors(containerColor = com.example.kotlin_tracktic.ui.theme.Purple40),
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .background(com.example.kotlin_tracktic.ui.theme.Purple40)
                        .border(1.dp, com.example.kotlin_tracktic.ui.theme.Purple40)
                        .height(88.dp)
                        .fillMaxWidth()
                )

                //Buttons
                Column(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        buttons.forEach { button ->
                            OutlinedButton(
                                onClick = {
                                    buttons.forEach { it.isPressedState.value = false }
                                    buttons2.forEach { it.isPressedState.value = false }
                                    button.isPressedState.value = true
                                    textFieldValue =
                                        button.label.replace(",", "").toIntOrNull() ?: 0
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (button.isPressedState.value) com.example.kotlin_tracktic.ui.theme.Red30 else Color.Transparent,
                                    contentColor = if (button.isPressedState.value) Color.White else Color.Black
                                ),
                                border = BorderStroke(1.dp, Color.LightGray),
                                shape = RoundedCornerShape(20),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = button.label,
                                    style = TextStyle(
                                        color = if (button.isPressedState.value) com.example.kotlin_tracktic.ui.theme.Red40 else Color.Black,
                                        fontSize = 10.sp
                                    ),
                                    modifier = Modifier.padding(0.dp)
                                )
                            }
                        }
                    }
                }

                Column(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        buttons2.forEach { button ->
                            OutlinedButton(
                                onClick = {
                                    buttons.forEach { it.isPressedState.value = false }
                                    buttons2.forEach { it.isPressedState.value = false }
                                    button.isPressedState.value = true
                                    textFieldValue =
                                        button.label.replace(",", "").toIntOrNull() ?: 0
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (button.isPressedState.value) com.example.kotlin_tracktic.ui.theme.Red30 else Color.Transparent,
                                    contentColor = if (button.isPressedState.value) Color.White else Color.Black
                                ),
                                border = BorderStroke(1.dp, Color.LightGray),
                                shape = RoundedCornerShape(20),
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = button.label,
                                    style = TextStyle(
                                        color = if (button.isPressedState.value) com.example.kotlin_tracktic.ui.theme.Red40 else Color.Black,
                                        fontSize = 10.sp
                                    ),
                                    modifier = Modifier.padding(0.dp)
                                )
                            }
                        }
                    }
                }

                // Expense Category
                Column(modifier = Modifier.padding(top = 10.dp)) {
                    val options = listOf("Expense", "Income")
                    var expanded by remember { mutableStateOf(false) }
                    var selectedOptionText by remember { mutableStateOf(options[0]) }

                    typeValue = selectedOptionText

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded },
                    ) {
                        TextField(
                            readOnly = true,
                            value = selectedOptionText,
                            onValueChange = {},
                            label = { Text("Type") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                            textStyle = TextStyle(
                                color = Color.Black),
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .menuAnchor()
                                .border(1.dp, Color.LightGray)
                                .fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        ) {
                            options.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(selectionOption) },
                                    onClick = {
                                        selectedOptionText = selectionOption
                                        expanded = false
                                        typeValue = selectionOption
                                    },
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                )
                            }
                        }
                    }
                }

                // Transaction Category
                Column(modifier = Modifier.padding(top = 10.dp)) {
                    val options = listOf("Transportation", "Food", "Shop", "Others")
                    var expanded by remember { mutableStateOf(false) }
                    var selectedOptionText by remember { mutableStateOf(options[0]) }

                    categoryValue = selectedOptionText

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = !expanded },
                    ) {
                        TextField(
                            readOnly = true,
                            value = selectedOptionText,
                            onValueChange = {},
                            label = { Text("Category") },
                            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                            textStyle = TextStyle(
                                color = Color.Black),
                            shape = RoundedCornerShape(20),
                            modifier = Modifier
                                .menuAnchor()
                                .border(1.dp, Color.LightGray)
                                .fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        ) {
                            options.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(selectionOption) },
                                    onClick = {
                                        selectedOptionText = selectionOption
                                        expanded = false
                                        categoryValue = selectionOption
                                    },
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                )
                            }
                        }
                    }
                }

                // Date
                Column(modifier = Modifier.padding(top = 10.dp)) {
                    EndDateTextField { endDate ->
                        endDateMillis = endDate // Store the selected date as milliseconds
                    }
                }



                // Remarks
                Column(modifier = Modifier.padding(top = 10.dp)) {
                    TextField(
                        value = remarksValue,
                        onValueChange = { newValue ->
                            remarksValue = newValue.toString()
                        },
                        placeholder = {
                            Text(
                                text = "Input remarks here...",
                                fontSize = 12.sp,
                                color = Color.LightGray
                            )
                        },
                        maxLines = 1,
                        shape = RoundedCornerShape(20),
                        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                        textStyle = TextStyle(color = Color.Black, fontSize = 12.sp),
                        modifier = Modifier
                            .background(Color.White)
                            .border(1.dp, Color.LightGray)
                            .height(88.dp)
                            .fillMaxWidth()
                    )
                }

                // Button
                Column(
                    modifier = Modifier.padding(top = 30.dp),
                ) {
                    OutlinedButton(
                        onClick = {
                            val transactionData = TransactionData(
                                nominal = textFieldValue,
                                category = categoryValue,
                                date = endDate,
                                description = remarksValue,
                                type = typeValue
                            )
                            sharedViewModel.saveData(transactionData, context)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = com.example.kotlin_tracktic.ui.theme.Red30,
                            contentColor = Color.Black
                        ),
                        border = BorderStroke(1.dp, Color.LightGray),
                        shape = RoundedCornerShape(20),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp)
                    ) {
                        Text(
                            text = "Submit",
                            style = TextStyle(
                                color = com.example.kotlin_tracktic.ui.theme.Red40,
                                fontSize = 16.sp
                            ),
                            modifier = Modifier.padding(0.dp)
                        )
                    }
                }
            }

            BottomNavigation(navController = navController)
    }
}}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EndDateTextField(endDate: (Long) -> Unit) {

    var shouldDisplay by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()
    if (isPressed) {
        shouldDisplay = true
    }

    val today = Calendar.getInstance()
    today.set(Calendar.HOUR_OF_DAY, 0)
    today.set(Calendar.MINUTE, 0)
    today.set(Calendar.SECOND, 0)
    today.set(Calendar.MILLISECOND, 0)
    val currentDayMillis = today.timeInMillis
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis(),
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= currentDayMillis
            }
        }
    )

    var selectedDate by rememberSaveable {
        mutableStateOf(
            datePickerState.selectedDateMillis?.toFormattedDateString() ?: ""
        )
    }

    EndDatePickerDialog(
        state = datePickerState,
        shouldDisplay = shouldDisplay,
        onConfirmClicked = { selectedDateInMillis ->
            endDate(selectedDateInMillis) // Pass the Long value directly
        },
        dismissRequest = {
            shouldDisplay = false
        }
    )

    TextField(
        readOnly = true,
        value = selectedDate,
        onValueChange = {},
        trailingIcon = { Icons.Default.DateRange },
        interactionSource = interactionSource,
        label = { Text("Date") },
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
        textStyle = TextStyle(
            color = Color.Black),
        shape = RoundedCornerShape(20),
        modifier = Modifier
            .background(Color.White)
            .border(1.dp, Color.LightGray)
            .fillMaxWidth()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EndDatePickerDialog(
    state: DatePickerState,
    shouldDisplay: Boolean,
    onConfirmClicked: (selectedDateInMillis: Long) -> Unit,
    dismissRequest: () -> Unit
) {
    if (shouldDisplay) {
        DatePickerDialog(
            onDismissRequest = dismissRequest,
            confirmButton = {
                Button(
                    modifier = Modifier.padding(0.dp, 0.dp, 8.dp, 0.dp),
                    onClick = {
                        state.selectedDateMillis?.let {
                            onConfirmClicked(it)
                        }
                        dismissRequest()
                    }
                ) {
                    Text(text = "ok")
                }
            },
            dismissButton = {
                TextButton(onClick = dismissRequest) {
                    Text(text = "cancel")
                }
            },
            content = {
                DatePicker(
                    state = state,
                    showModeToggle = false,
                    headline = {
                        state.selectedDateMillis?.toFormattedDateString()?.let {
                            Text(
                                modifier = Modifier.padding(start = 16.dp),
                                text = it
                            )
                        }
                    }
                )
            }
        )
    }
}

fun Date.toFormattedDateString(): String {
    val sdf = SimpleDateFormat("EEEE, LLLL dd", Locale.getDefault())
    return sdf.format(this)
}

fun Date.toFormattedMonthDateString(): String {
    val sdf = SimpleDateFormat("MMMM dd", Locale.getDefault())
    return sdf.format(this)
}

fun Date.toFormattedDateShortString(): String {
    val sdf = SimpleDateFormat("dd", Locale.getDefault())
    return sdf.format(this)
}

fun Long.toFormattedDateString(): String {
    val sdf = SimpleDateFormat("LLLL dd, yyyy", Locale.getDefault())
    return sdf.format(this)
}

fun Date.hasPassed(): Boolean {
    val calendar = Calendar.getInstance()
    calendar.add(Calendar.SECOND, -1)
    val oneSecondAgo = calendar.time
    return time < oneSecondAgo.time
}
@Preview
@Composable
fun TransactionScreenPreview() {
    TransactionScreen(navController = NavController(LocalContext.current), sharedViewModel = SharedViewModel(), onBackClick = {})
}
