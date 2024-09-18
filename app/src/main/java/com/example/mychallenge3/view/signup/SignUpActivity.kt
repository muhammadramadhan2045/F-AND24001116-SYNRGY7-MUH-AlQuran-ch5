package com.example.mychallenge3.view.signup

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mychallenge3.R
import com.example.mychallenge3.databinding.ActivitySignUpBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private val viewModel: SignUpViewModel by viewModel()

    private var currentDialog: AlertDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()
        setupAction()
    }

    private fun setupAction() {
        binding.signupButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()


            if (email.isEmpty() || password.isEmpty()) {
                AlertDialog.Builder(this).apply {
                    setTitle(getString(R.string.oops))
                    setMessage(getString(R.string.auth_validation))
                    setPositiveButton("OK") { _, _ -> }
                    create()
                    show()
                }
            } else {
                viewModel.register(name, email, password)
            }
        }

        viewModel.loading.observe(this) {
            binding.viewFlipper.displayedChild = if (it) 1 else 0
        }

        viewModel.registerResult.observe(this) { result ->
            if (result != null) {
                if (result.error) {
                    currentDialog = AlertDialog.Builder(this).apply {
                        setTitle("Oops!")
                        setMessage(result.message)
                        setPositiveButton("OK") { _, _ -> }
                        create()
                    }.show()
                } else {
                    currentDialog = AlertDialog.Builder(this).apply {
                        setTitle("Yeah!")
                        setMessage(result.message)
                        setPositiveButton("Lanjut") { _, _ ->
                            finish()
                        }
                        create()

                    }.show()

                }
            }
        }

    }
}