package fr.isen.networkapp.pk

import android.annotation.TargetApi
import android.app.Activity
import android.app.ProgressDialog.show
import android.content.ActivityNotFoundException
import android.content.ContentUris
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import fr.isen.networkapp.pk.databinding.ActivityPostBinding
import android.view.View
import android.widget.ImageView
import com.squareup.picasso.Picasso
import fr.isen.networkapp.pk.extensions.Extensions.toast
import fr.isen.networkapp.pk.model.Post
import fr.isen.networkapp.pk.utils.FirebaseUtils.dbRef
import fr.isen.networkapp.pk.utils.FirebaseUtils.firebaseUser


class PostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Taking a picture
        binding.newPictureButton.setOnClickListener {
            dispatchTakePictureIntent()
        }

        // Choosing picture from phone
        binding.addPictureButton.setOnClickListener {
            loadChosenPicture()
        }

        binding.postButton.setOnClickListener {
            //TODO: better username plz
            val testMessage: Post = Post(binding.postTitleInput.text.toString(),binding.postDescription.text.toString(),"Robert")
            dbRef.child(dbRef.push().key.toString()).setValue(testMessage)
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }
    }

    private fun loadChosenPicture() {
        val choosePictureIntent = Intent().setAction(Intent.ACTION_GET_CONTENT)
        choosePictureIntent.type = "image/*"
        startActivityForResult(Intent.createChooser(choosePictureIntent, "choisissez une image"), REQUEST_IMAGE_CHOOSE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_IMAGE_CAPTURE ->
                if (resultCode == RESULT_OK) {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    binding.imageView.setImageBitmap(imageBitmap)
                }
            REQUEST_IMAGE_CHOOSE ->
                if (resultCode == RESULT_OK) {
                    val filepath = data?.data
                    Picasso.get().load(filepath).into(binding.imageView)
                }
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()// display error state to the user
        }
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val REQUEST_IMAGE_CHOOSE = 2
    }

}
