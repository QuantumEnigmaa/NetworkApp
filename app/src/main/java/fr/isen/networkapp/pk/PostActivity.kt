package fr.isen.networkapp.pk

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import fr.isen.networkapp.pk.databinding.ActivityPostBinding
import android.view.View
import android.widget.ImageView
import fr.isen.networkapp.pk.model.Post
import fr.isen.networkapp.pk.utils.FirebaseUtils.dbRef
import fr.isen.networkapp.pk.utils.FirebaseUtils.firebaseUser


class PostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostBinding
    private val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.newPictureButton.setOnClickListener {
            dispatchTakePictureIntent()
        }

        binding.postButton.setOnClickListener {
            val testMessage: Post = Post("postTest", "blablabla", "jean-didier")
            dbRef.child("testpost").setValue(testMessage)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.imageView.setImageBitmap(imageBitmap)
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            // display error state to the user
        }
    }



}
