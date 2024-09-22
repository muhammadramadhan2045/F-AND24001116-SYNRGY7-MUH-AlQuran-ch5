package com.example.mychallenge3.view.signup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.example.mychallenge3.R
import com.example.mychallenge3.databinding.FragmentSignUpBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get()= _binding!!
    private var currentDialog: AlertDialog? = null

    private val viewModel: SignUpViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this
        _binding= FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAction()
    }

    private fun setupAction() {
        binding.signupButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
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
                viewModel.register(name, email, password)
            }
        }

        viewModel.loading.observe(requireActivity()) {
            binding.viewFlipper.displayedChild = if (it) 1 else 0
        }

        viewModel.registerResult.observe(requireActivity()) { result ->
            if (result != null) {
                if (result.error) {
                    currentDialog = AlertDialog.Builder(requireContext()).apply {
                        setTitle("Oops!")
                        setMessage(result.message)
                        setPositiveButton("OK") { _, _ -> }
                        create()
                    }.show()
                } else {
                    currentDialog = AlertDialog.Builder(requireContext()).apply {
                        setTitle("Yeah!")
                        setMessage(result.message)
                        setPositiveButton("Lanjut") { _, _ ->
                            findNavController().popBackStack()
                        }
                        create()

                    }.show()

                }
            }
        }

    }
}
