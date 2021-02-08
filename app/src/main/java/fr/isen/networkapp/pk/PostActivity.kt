package fr.isen.networkapp.pk

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import fr.isen.networkapp.pk.databinding.ActivityPostBinding
import android.widget.ProgressBar
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import fr.isen.networkapp.pk.extensions.Extensions.toast
import fr.isen.networkapp.pk.model.Image
import fr.isen.networkapp.pk.model.Post
import fr.isen.networkapp.pk.utils.FirebaseUtils.dbRef
import fr.isen.networkapp.pk.utils.FirebaseUtils.firebaseUser
import fr.isen.networkapp.pk.utils.FirebaseUtils.storageRef
import java.util.*
import kotlin.collections.ArrayList


class PostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPostBinding
    private lateinit var filepath: Uri

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

        binding.progressCircular.visibility = View.INVISIBLE
        binding.postButton.setOnClickListener {
            //TODO: better username plz
            binding.progressCircular.apply {
                progressMax = 100f
                setProgressWithAnimation(50f, 1000)
                progressBarWidth = 5f
                backgroundProgressBarWidth = 2f
                progressBarColor = Color.BLUE
            }
            uploadData()
        }
    }

    private fun uploadData() {
        binding.progressCircular.visibility = View.VISIBLE
        if (filepath != null) {
            val imgFile: StorageReference = storageRef.child(UUID.randomUUID().toString())
            imgFile.putFile(filepath).addOnSuccessListener {
                imgFile.downloadUrl.addOnSuccessListener {
                    val image: Image = Image(it.toString())
                    val url: String = image.getImageUrl().toString()
                    val testMessage: Post = Post(binding.postTitleInput.text.toString(),binding.postDescription.text.toString(),
                        firebaseUser.toString(), url, ArrayList())
                    dbRef.child(dbRef.push().key.toString()).setValue(testMessage)
                    toast("Post créé !")
                    startActivity(Intent(this, HomeActivity::class.java))
                    finish()
                }.addOnFailureListener {
                    it.message?.let { it1 -> toast(it1) }
                }
            }.addOnFailureListener {
                it.message?.let { it1 -> toast(it1) }
            }.addOnProgressListener {
                //TODO: handle progress bar
            }
        } else {
            toast("Veuillez choisir une image")
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
                    filepath = data?.data!!
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
