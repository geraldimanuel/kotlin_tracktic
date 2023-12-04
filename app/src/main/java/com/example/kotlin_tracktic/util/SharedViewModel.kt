package com.example.kotlin_tracktic.util

import android.annotation.SuppressLint
import android.content.Context
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
//          .document(transactionData.id)

        print("data saved")

        try {
            db.collection("transactions")
                .add(transactionData)
                .addOnSuccessListener {
                    Toast.makeText(context, "Data saved", Toast.LENGTH_SHORT).show()
                }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun retrieveData(
        id: String,
        context: Context,
        data: (TransactionData) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch{
        val firestoreRef = Firebase.firestore
            .collection("transactions")
            .document(id)

        try {
            firestoreRef.get()
                .addOnSuccessListener {
                   if (it.exists()) {
                       val transactionData = it.toObject(TransactionData::class.java)
                       data(transactionData!!)
                   } else {
                       Toast.makeText(context, "Data not found", Toast.LENGTH_SHORT).show()
                   }
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
}