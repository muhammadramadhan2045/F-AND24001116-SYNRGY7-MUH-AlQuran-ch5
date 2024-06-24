package com.example.mychallenge3.view.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mychallenge3.R
import com.example.mychallenge3.databinding.ActivityMainBinding
import com.example.mychallenge3.view.favorite.FavoriteFragment
import com.example.mychallenge3.view.login.LoginActivity
import com.example.mychallenge3.view.profile.ProfileActivity
import com.example.mychallenge3.view.quran.QuranActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private val viewModel : MainViewModel by viewModel()

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
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            binding?.tvUserName?.text = user.email
        }



        binding?.tvDayDate?.text = getCurrentTime()
        binding?.cvReadQuran?.setOnClickListener {
            startActivity(Intent(this, QuranActivity::class.java))
        }


        binding?.cvMuslimProfile?.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }


        binding?.cvPrayTime?.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Segera Hadir")
                .setMessage("Fitur ini akan segera hadir, tunggu update selanjutnya ya!")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .setIcon(R.drawable.ic_quran)
                .show()

        }


        binding?.cvLogOut?.setOnClickListener {
            viewModel.logout()
        }


    }
    private fun getCurrentTime(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("EEEE, dd MMM", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }

}