package fr.isen.networkapp.pk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import fr.isen.networkapp.pk.databinding.ActivitySignInBinding
import fr.isen.networkapp.pk.extensions.Extensions.toast
import fr.isen.networkapp.pk.utils.FirebaseUtils.firebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    lateinit var signInEmail: String
    lateinit var signInPassword: String
    lateinit var signInInputsArray: Array<EditText>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ih the user do not have an account
        binding.accountLoginNew.setOnClickListener {
            startActivity(Intent(this, CreateAccountActivity::class.java))
        }

        signInInputsArray = arrayOf(binding.accountLoginEmail, binding.accountLoginPassword)
        binding.accountLoginButton.setOnClickListener {
            singInUser()
        }

    }

    private fun notEmpty(): Boolean = signInEmail.isNotEmpty() && signInPassword.isNotEmpty()

    private fun singInUser() {
        signInEmail = binding.accountLoginEmail.text.toString().trim()
        signInPassword = binding.accountLoginPassword.text.toString().trim()

        if (notEmpty()) {
            firebaseAuth.signInWithEmailAndPassword(signInEmail, signInPassword)
                .addOnCompleteListener { signIn ->
                    if (signIn.isSuccessful) {
                        startActivity(Intent(this, HomeActivity::class.java))
                        toast("Vous êtes authentifié !")
                        finish()
                    } else {
                        toast("Erreur d'authentification")
                    }
                }
        } else {
            signInInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} requis"
                }
            }
        }
    }
}