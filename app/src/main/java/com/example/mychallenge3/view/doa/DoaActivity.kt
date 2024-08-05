package com.example.mychallenge3.view.doa

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mychallenge3.R
import com.example.mychallenge3.databinding.ActivityDoaBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DoaActivity : AppCompatActivity() {
    private var _binding: ActivityDoaBinding? = null
    private val binding get() = _binding!!

    private val doaViewModel: DoaViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        _binding = ActivityDoaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        doaViewModel.getDoa()
        Log.d("DoaActivity", "onCreateRamasss:${
            doaViewModel.doa.value
        } ")
    }
}