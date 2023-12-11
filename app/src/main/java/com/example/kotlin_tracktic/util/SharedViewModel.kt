package com.example.kotlin_tracktic.util

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel(): ViewModel() {

    @SuppressLint("SuspiciousIndentation")
    fun saveData(
        transactionData: TransactionData,
        context: Context
    ) = CoroutineScope(Dispatchers.IO).launch{
      val db = Firebase.firestore

        val data = hashMapOf(
            "nominal" to transactionData.nominal,
            "category" to transactionData.category,
            "date" to transactionData.date,
            "description" to transactionData.description,
            "type" to transactionData.type
        )

        try {
            db.collection("transactions")
                .add(data)
                .addOnSuccessListener {
                    Toast.makeText(context, "Data saved", Toast.LENGTH_SHORT).show()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
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
        val firestoreRef = Firebase.firestore
            .collection("transactions")
            .document(id)

        try {
            firestoreRef.delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Data saved", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun printData(){
        Log.d("TransactionScreen", "Submitted!")
    }

}