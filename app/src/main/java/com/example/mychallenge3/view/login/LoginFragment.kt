package com.example.mychallenge3.view.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.mychallenge3.R
import com.example.mychallenge3.databinding.FragmentLoginBinding
import com.example.mychallenge3.domain.model.UserModel
import com.example.mychallenge3.view.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    private val viewModel: LoginViewModel by viewModel()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private var currentDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAction()

        binding.goToSignUpButton.setOnClickListener {
            val toSignUpFragment = LoginFragmentDirections.actionLoginFragmentToSignUpFragment()
            findNavController().navigate(toSignUpFragment)
        }
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                AlertDialog.Builder(requireContext()).apply {
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

        viewModel.loading.observe(requireActivity()) {
            binding.viewFlipper.displayedChild = if (it) 1 else 0
        }

        viewModel.loginResult.observe(requireActivity()) { result ->
            if (result != null) {
                if (result.error) {
                    currentDialog = AlertDialog.Builder(requireContext()).apply {
                        setTitle(getString(R.string.oops))
                        setMessage(result.message)
                        setPositiveButton(getString(R.string.ok)) { _, _ -> }
                        create()
                    }.show()
                } else {
                    val token = result.token ?: ""
                    viewModel.saveSession(
                        UserModel(
                            email = binding.emailEditText.text.toString(),
                            token = token
                        )
                    )
                    currentDialog = AlertDialog.Builder(requireContext()).apply {
                        setTitle(getString(R.string.yeah))
                        setMessage(getString(R.string.login_success_msg))
                        setPositiveButton(getString(R.string.lanjut)) { _, _ ->

                            val navOptions =
                                NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build()
                            if (findNavController().currentDestination?.id == R.id.loginFragment) {
                                findNavController().navigate(
                                    R.id.action_loginFragment_to_mainDashboardFragment,
                                    null,
                                    navOptions
                                )
                            }
                        }
                        create()
                    }.show()
                }
            }
        }
    }
}