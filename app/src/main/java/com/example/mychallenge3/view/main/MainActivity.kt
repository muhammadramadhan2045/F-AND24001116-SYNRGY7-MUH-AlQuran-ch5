package com.example.mychallenge3.view.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mychallenge3.R
import com.example.mychallenge3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {

                val navOptions =
                    NavOptions.Builder().setPopUpTo(R.id.mainDashboardFragment, true).build()
                if (findNavController(R.id.container).currentDestination?.id == R.id.mainDashboardFragment) {
                    findNavController(R.id.container).navigate(
                        R.id.action_mainDashboardFragment_to_loginFragment,
                        null,
                        navOptions
                    )
                }
            }
        }
    }


}