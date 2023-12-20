package com.example.kotlin_tracktic.util

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.kotlin_tracktic.Screen
import com.example.kotlin_tracktic_theincredibles.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(): ViewModel() {

    private lateinit var auth: FirebaseAuth


    @SuppressLint("SuspiciousIndentation")
    fun saveData(
        transactionData: TransactionData,
        context: Context
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

    fun retrieveData(
        user: String,
        context: Context,
//        data: (TransactionData) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch{

        val db = Firebase.firestore
        try {
            db.collection("transactions")
//                .whereEqualTo("category", user)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {
                        Log.d("debug data","${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("Error getting documents: ", exception)
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }

    }

    fun deleteData(
        id: String,
        context: Context,
        navController: NavController,
        backToMainScreen: () -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch{

        val db = Firebase.firestore

        try {
            db.collection("transactions")
                .document(id)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Data saved", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun signUp(
        email: String,
        password: String,
        context: Context,
        navController: NavController,
        backToMainScreen: () -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch{
//        auth = FirebaseAuth.getInstance()
        auth = Firebase.auth

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Sign up success", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                } else {
                    Toast.makeText(context, "Sign up failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun signIn(
        email: String,
        password: String,
        context: Context,
        navController: NavController,
        backToMainScreen: () -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch{
//        auth = FirebaseAuth.getInstance()
        auth = Firebase.auth

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Sign in success", Toast.LENGTH_SHORT).show()
                    navController.navigate(Screen.StatisticScreen.route)
                } else {
                    Toast.makeText(context, "Sign in failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun signInWithGoogle(
        context: Context,
        navController: NavController,
        backToMainScreen: () -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch{
//        auth = FirebaseAuth.getInstance()
        auth = Firebase.auth

        BeginSignInRequest.builder()
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

}