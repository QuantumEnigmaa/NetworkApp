package fr.isen.networkapp.pk.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

object FirebaseUtils {
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
    private val firebaseDB: FirebaseDatabase = FirebaseDatabase.getInstance()
    val dbRef: DatabaseReference = firebaseDB.getReference("postData")
    private val firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
    val storageRef: StorageReference = firebaseStorage.getReference("images")
}