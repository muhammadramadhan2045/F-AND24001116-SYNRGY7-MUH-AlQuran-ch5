package com.example.mychallenge3.view.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mychallenge3.R
import com.example.mychallenge3.adapter.ListSurahAdapter
import com.example.mychallenge3.data.model.Surat
import com.example.mychallenge3.data.source.remote.response.DataItem
import com.example.mychallenge3.databinding.FragmentHomeBinding
import com.example.mychallenge3.view.ViewModelFactory
import com.example.mychallenge3.view.login.LoginActivity


class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    private val homeViewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = "Al-Qur'an"

        binding.toolbar.inflateMenu(R.menu.menu_main)
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_logout -> {
                    homeViewModel.logout()
                    val intent = Intent(context, LoginActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                    true
                }
                R.id.menu_favorite -> {
                    val toFavoriteFragment = HomeFragmentDirections.actionHomeFragmentToFavoriteFragment()
                    findNavController().navigate(toFavoriteFragment)

                    true
                }
                else -> false
            }
        }

        showRecyclerList()

        goToFavoriteFragment()

        showLoading()

    }

    private fun showLoading() {
        homeViewModel.loading.observe(viewLifecycleOwner){
            if(it){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    private fun goToFavoriteFragment() {
        Log.d("HomeFragment", "goToFavoriteFragment: ")
    }



    private fun showRecyclerList() {
        binding.rvSurat.layoutManager = LinearLayoutManager(context)
        val listSurahAdapter = ListSurahAdapter()
        binding.rvSurat.adapter = listSurahAdapter
        homeViewModel.surat.observe(viewLifecycleOwner){
            listSurahAdapter.submitList(it)
        }

        listSurahAdapter.setOnItemClickCallback(object:ListSurahAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Surat) {
                val toDetailFragment = HomeFragmentDirections.actionHomeFragmentToDetailFragment(data)
                toDetailFragment.id = data.nomor
                toDetailFragment.namaSurat = data.namaLatin
                toDetailFragment.detailSurat = data
                findNavController().navigate(toDetailFragment)
            }

        })


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}