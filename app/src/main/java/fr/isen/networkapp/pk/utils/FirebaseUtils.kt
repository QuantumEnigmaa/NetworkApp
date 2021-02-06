package fr.isen.networkapp.pk.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object FirebaseUtils {
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
    val firebaseDB: FirebaseDatabase = FirebaseDatabase.getInstance()
    val dbRef: DatabaseReference = firebaseDB.getReference("postData")
}