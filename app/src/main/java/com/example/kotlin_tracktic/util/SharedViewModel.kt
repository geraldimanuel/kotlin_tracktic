package com.example.kotlin_tracktic.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.kotlin_tracktic.Screen
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.auth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(): ViewModel() {

    private lateinit var auth: FirebaseAuth


    @SuppressLint("SuspiciousIndentation")
    fun saveData(
        transactionData: TransactionData,
        context: Context,
        navController: NavController
    ) = CoroutineScope(Dispatchers.IO).launch{
      val db = Firebase.firestore

        val user = Firebase.auth.currentUser
        val uid = user?.uid

        if (uid != null) {
            val data = hashMapOf(
                "nominal" to transactionData.nominal,
                "category" to transactionData.category,
                "date" to transactionData.date,
                "description" to transactionData.description,
                "type" to transactionData.type,
                "uid" to uid // Include authenticated user's UID
            )

            try {
                db.collection("transactions")
                    .add(data)
                    .addOnSuccessListener {
                        Toast.makeText(context, "Data saved", Toast.LENGTH_SHORT).show()
                        navController.navigate(Screen.StatisticScreen.route)
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Failed to save data: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            } catch (e: Exception) {
                Toast.makeText(context, "Failed to save data: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            // Handle scenario where user is not authenticated
            Toast.makeText(context, "User not authenticated", Toast.LENGTH_SHORT).show()
        }
    }

    fun retrieveExpense(
        context: Context,
        data: (List<TransactionData>) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {

        val db = Firebase.firestore
        val user = Firebase.auth.currentUser
        val uid = user?.uid

        if (user != null) {
            try {
                db.collection("transactions")
                    .whereEqualTo("uid", uid)
                    .whereEqualTo("type", "Expense")
                    .get()
                    .addOnSuccessListener { documents ->
                        val expenseList = mutableListOf<TransactionData>()
                        var totalExpense = 0.0 // Assuming nominal is a Double

                        for (document in documents) {
                            val expenseData = document.toObject(TransactionData::class.java)
                            expenseList.add(expenseData)
                            totalExpense += expenseData.nominal
                        }

                        // Return the list of expenses and total expense through the callback
                        data(expenseList)
                        // Handle total expense here or send it back as needed
                        Log.d("Total Expense", "Total: $totalExpense")
                    }
                    .addOnFailureListener { exception ->
                        Log.w("Error getting documents: ", exception)
                    }
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        } else {
            // Handle scenario where user is not authenticated
            Toast.makeText(context, "User not authenticated", Toast.LENGTH_SHORT).show()
        }
    }

    fun retrieveIncome(
        context: Context,
        data: (List<TransactionData>) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {

        val db = Firebase.firestore
        val user = Firebase.auth.currentUser
        val uid = user?.uid

        if (user != null) {
            try {
                db.collection("transactions")
                    .whereEqualTo("uid", uid)
                    .whereEqualTo("type", "Income")
                    .get()
                    .addOnSuccessListener { documents ->
                        val expenseList = mutableListOf<TransactionData>()
                        var totalExpense = 0.0 // Assuming nominal is a Double

                        for (document in documents) {
                            val expenseData = document.toObject(TransactionData::class.java)
                            expenseList.add(expenseData)
                            totalExpense += expenseData.nominal
                        }

                        // Return the list of expenses and total expense through the callback
                        data(expenseList)
                        // Handle total expense here or send it back as needed
                        Log.d("Total Expense", "Total: $totalExpense")
                    }
                    .addOnFailureListener { exception ->
                        Log.w("Error getting documents: ", exception)
                    }
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        } else {
            // Handle scenario where user is not authenticated
            Toast.makeText(context, "User not authenticated", Toast.LENGTH_SHORT).show()
        }
    }



    fun retrieveData(
        context: Context,
        data: (List<Pair<String, TransactionData>>) -> Unit // Using Pair to hold ID and TransactionData
    ) = CoroutineScope(Dispatchers.IO).launch {

        val db = Firebase.firestore
        val user = Firebase.auth.currentUser
        val uid = user?.uid

        if (user != null) {
            try {
                db.collection("transactions")
                    .whereEqualTo("uid", uid)
                    .get()
                    .addOnSuccessListener { documents ->
                        val transactionList = mutableListOf<Pair<String, TransactionData>>() // Pair to hold ID and TransactionData

                        for (document in documents) {
                            val transaction = document.toObject(TransactionData::class.java)
                            val transactionId = document.id // Get the document ID

                            // Add ID and TransactionData to the list as a Pair
                            transactionList.add(Pair(transactionId, transaction))
                        }

                        // Pass the populated list with IDs back via the callback
                        data(transactionList)
                    }
                    .addOnFailureListener { exception ->
                        Log.w("Error getting documents: ", exception)
                        // Handle failure, maybe notify the user or log the error
                    }
            } catch (e: Exception) {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                // Handle exception, maybe notify the user or log the error
            }
        } else {
            // Handle scenario where user is not authenticated
            Toast.makeText(context, "User not authenticated", Toast.LENGTH_SHORT).show()
        }
    }



    fun deleteData(
        id: String,
        context: Context,
    ) = CoroutineScope(Dispatchers.IO).launch{

        val db = Firebase.firestore

        try {
            db.collection("transactions")
                .document(id)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun signUp(
        email: String,
        password: String,
        context: Context,
        navController: NavController
    ) = CoroutineScope(Dispatchers.IO).launch{
        // auth = FirebaseAuth.getInstance()
        auth = Firebase.auth

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Extract display name from email (without domain part)
                    val displayName = email.substringBefore('@')

                    // Update display name with the extracted name
                    val user = auth.currentUser
                    user?.updateProfile(UserProfileChangeRequest.Builder()
                        .setDisplayName(displayName) // Setting display name without domain
                        .setPhotoUri("https://www.w3schools.com/howto/img_avatar.png".toUri())
                        .build())
                        ?.addOnCompleteListener { profileUpdateTask ->
                            if (profileUpdateTask.isSuccessful) {
                                Toast.makeText(context, "Sign up success", Toast.LENGTH_SHORT).show()
                                navController.navigate(Screen.LoginScreen.route)
                            } else {
                                Toast.makeText(context, "Failed to set display name", Toast.LENGTH_SHORT).show()
                                // Handle the error when setting the display name fails
                            }
                        }
                } else {
                    Toast.makeText(context, "Sign up failed", Toast.LENGTH_SHORT).show()
                    // Handle the signup failure
                }
            }
    }



    fun signIn(
        email: String,
        password: String,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch {
        val auth = FirebaseAuth.getInstance()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.getIdToken(/* forceRefresh = */ true)
                        ?.addOnSuccessListener { result ->
                            val token = result.token
                            saveAuthToken(context, token)
                            Toast.makeText(context, "Sign in success", Toast.LENGTH_SHORT).show()
                            navController.navigate(Screen.StatisticScreen.route)                        }
                        ?.addOnFailureListener {
                            Toast.makeText(context, "Failed to get user token", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(context, "Sign in failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private val AUTH_PREFS_NAME = "AuthPrefs"
    private val KEY_AUTH_TOKEN = "authToken"

    private fun saveAuthToken(context: Context, token: String?) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(AUTH_PREFS_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit {
            putString(KEY_AUTH_TOKEN, token)
            apply()
        }
    }

    fun signInWithGoogle(
        context: Context,
        navController: NavController,
        backToMainScreen: () -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        // auth = FirebaseAuth.getInstance()
        auth = Firebase.auth

        val signInResult = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId("610254144825-al0m2hm47d56djoiomtn5jcekm3i6g81.apps.googleusercontent.com")
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(true)
                    .build())
            .build()

    }

    fun signOut(
        context: Context,
        navController: NavController,
        backToMainScreen: () -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch{
//        auth = FirebaseAuth.getInstance()
        auth = Firebase.auth

        auth.signOut()
        Toast.makeText(context, "Sign out success", Toast.LENGTH_SHORT).show()
        navController.navigate(Screen.LoginScreen.route)
    }

    fun editProfile (
        name: String,
        context: Context,
        navController: NavController,
        backToMainScreen: () -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch{
//        auth = FirebaseAuth.getInstance()
        auth = Firebase.auth

        val user = auth.currentUser

        if (user != null) {
            val profileUpdates = userProfileChangeRequest {
                displayName = name
            }

            user.updateProfile(profileUpdates)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("debug", "User profile updated.")
                        navController.navigate(Screen.ProfileScreen.route)
                    }
                }
        }
    }


    fun printTest () {
        // print in logcat
        Log.d("debug", "test")
    }

}