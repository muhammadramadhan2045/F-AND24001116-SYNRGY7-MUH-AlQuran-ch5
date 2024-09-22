package com.example.mychallenge3.view.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mychallenge3.R
import com.example.mychallenge3.databinding.FragmentMainDashboardBinding
import com.google.firebase.crashlytics.FirebaseCrashlytics
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MainDashboardFragment : Fragment() {

    private var _binding: FragmentMainDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getSession().observe(requireActivity()) { user ->
            binding.tvUserName.text = user.email
        }


        binding.tvDayDate.text = getCurrentTime()
        binding.cvReadQuran.setOnClickListener {
            val toQuranListFragment =
                MainDashboardFragmentDirections.actionMainDashboardFragmentToHomeFragment()
            findNavController().navigate(toQuranListFragment)
        }


        binding.cvMuslimProfile.setOnClickListener {
            val toProfileFragment =
                MainDashboardFragmentDirections.actionMainDashboardFragmentToProfilFragment()
            findNavController().navigate(toProfileFragment)
        }


        binding.cvPrayTime.setOnClickListener {
            AlertDialog.Builder(requireContext())
                .setTitle("Segera Hadir")
                .setMessage("Fitur ini akan segera hadir, tunggu update selanjutnya ya!")
                .setPositiveButton("OK") { dialog, _ ->
                    dialog.dismiss()
                }
                .setIcon(R.drawable.ic_quran)
                .show()

        }


        binding.cvLogOut.setOnClickListener {
            viewModel.logout()
        }

        binding.btCrash.setOnClickListener {
            FirebaseCrashlytics.getInstance().log("Clicked on button")
            throw RuntimeException("Test Crash") // Force a crash
        }

    }

    private fun getCurrentTime(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("EEEE, dd MMM", Locale.getDefault())
        return dateFormat.format(calendar.time)
    }
}