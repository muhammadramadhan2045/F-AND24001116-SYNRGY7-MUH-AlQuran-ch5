package com.example.mychallenge3.view.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mychallenge3.R
import com.example.mychallenge3.adapter.ListSurahAdapter
import com.example.mychallenge3.domain.model.Surat
import com.example.mychallenge3.databinding.FragmentFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment() {


    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!



    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set action bar title
        activity?.title = getString(R.string.favorite)

        showRecyclerList()

        favoriteViewModel.surat.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.rvFavorite.visibility = View.GONE
                binding.tvNoData.visibility = View.VISIBLE
                binding.lottieAnimation.visibility = View.VISIBLE
            } else {
                binding.rvFavorite.visibility = View.VISIBLE
                binding.tvNoData.visibility = View.GONE
                binding.lottieAnimation.visibility = View.GONE
            }
        }
    }

    private fun showRecyclerList() {
        binding.rvFavorite.layoutManager = LinearLayoutManager(context)
        binding.rvFavorite.setHasFixedSize(true)
        val listSurahAdapter = ListSurahAdapter()
        binding.rvFavorite.adapter = listSurahAdapter

        favoriteViewModel.surat.observe(viewLifecycleOwner) {
            listSurahAdapter.submitList(it) 
        }

        listSurahAdapter.setOnItemClickCallback(object : ListSurahAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Surat) {
                val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(data)
                action.id = data.nomor
                action.namaSurat = data.namaLatin
                action.detailSurat = data
                findNavController().navigate(action)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}