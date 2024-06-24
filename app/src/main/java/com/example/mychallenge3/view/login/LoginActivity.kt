package com.example.mychallenge3.view.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mychallenge3.R
import com.example.mychallenge3.domain.model.UserModel
import com.example.mychallenge3.databinding.ActivityLoginBinding
import com.example.mychallenge3.view.main.MainActivity
import com.example.mychallenge3.view.signup.SignUpActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {
    private val viewModel: LoginViewModel by viewModel()
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()
        setupAction()

        binding.goToSignUpButton.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }


    private fun setupAction() {
        binding.loginButton.setOnClickListener {
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
                viewModel.login(email, password)
            }
        }

        viewModel.loading.observe(this) {
            binding.viewFlipper.displayedChild = if (it) 1 else 0
        }

        viewModel.loginResult.observe(this) { result ->
            result.onSuccess {
                val token = it.loginResult?.token ?: ""
                viewModel.saveSession(
                    UserModel(
                        email = binding.emailEditText.text.toString(),
                        token = token
                    )
                )
                AlertDialog.Builder(this).apply {
                    setTitle(getString(R.string.yeah))
                    setMessage(getString(R.string.login_success_msg))
                    setPositiveButton(getString(R.string.lanjut)) { _, _ ->
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                        finish()
                    }
                    create()
                    show()
                }
            }
            result.onFailure {
                AlertDialog.Builder(this).apply {
                    setTitle(getString(R.string.oops))
                    setMessage(getString(R.string.logn_failed_msg))
                    setPositiveButton(getString(R.string.ok)) { _, _ -> }
                    create()
                    show()
                }
            }
        }
    }


}