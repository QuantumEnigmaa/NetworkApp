package fr.isen.networkapp.pk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.firebase.auth.FirebaseUser
import fr.isen.networkapp.pk.databinding.ActivityCreateAccountBinding
import fr.isen.networkapp.pk.extensions.Extensions.toast
import fr.isen.networkapp.pk.utils.FirebaseUtils.firebaseAuth
import fr.isen.networkapp.pk.utils.FirebaseUtils.firebaseUser

class CreateAccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateAccountBinding

    lateinit var userEmail: String
    lateinit var userPassword: String
    lateinit var createAccountInputsArray: Array<EditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createAccountInputsArray = arrayOf(binding.accountCreationMail, binding.accountCreationPassword, binding.accountCreationPasswordAgain)
        binding.accountCreationCreate.setOnClickListener {
            signIn()
        }
    }

    override fun onStart() {
        super.onStart()
        val user: FirebaseUser? = firebaseAuth.currentUser
        user?.let {
            startActivity(Intent(this, HomeActivity::class.java))
            toast("Bienvenue !")
        }
    }

    private fun notEmpty(): Boolean = binding.accountCreationEnterMail.text.toString().trim().isNotEmpty() &&
            binding.accountCreationPassword.text.toString().trim().isNotEmpty() &&
            binding.accountCreationPasswordAgain.text.toString().trim().isNotEmpty()

    private fun identicalPassword(): Boolean {
        var identical = false
        if (notEmpty() &&
            binding.accountCreationPassword.text.toString().trim() == binding.accountCreationPasswordAgain.text.toString().trim()
        ) {
            identical = true
        } else if (!notEmpty()) {
            createAccountInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} est nécessaire"
                }
            }
        } else {
            toast("Les mots de passe ne correspondent pas !")
        }
        return identical
    }

    private fun signIn() {
        if (identicalPassword()) {
            // identicalPassword() returns true only  when inputs are not empty and passwords are identical
            userEmail = binding.accountCreationMail.text.toString().trim()
            userPassword = binding.accountCreationPassword.text.toString().trim()

            /*create a user*/
            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        toast("Votre compte a bien été créé")
                        // sendEmailVerification()
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    } else {
                        toast("Erreur lors de la création")
                    }
                }
        }
    }

    /*private fun sendEmailVerification() {
        firebaseUser?.let {
            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    toast("email sent to $userEmail")
                }
            }
        }
    }*/
}