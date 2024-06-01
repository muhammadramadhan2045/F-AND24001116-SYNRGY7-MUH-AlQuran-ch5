package com.example.mychallenge3.view.detail


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mychallenge3.R
import com.example.mychallenge3.adapter.ListAyatAdapter
import com.example.mychallenge3.databinding.FragmentDetailBinding
import com.example.mychallenge3.view.ViewModelFactory


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val detailViewModel: DetailViewModel by viewModels {
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
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = DetailFragmentArgs.fromBundle(arguments as Bundle).id
        val name = DetailFragmentArgs.fromBundle(arguments as Bundle).namaSurat
        val detailSurat = DetailFragmentArgs.fromBundle(arguments as Bundle).detailSurat

        binding.toolbar.title = name
        binding.toolbar.subtitle = StringBuilder().append(detailSurat.arti).append(" | ").append(detailSurat.jumlahAyat).append(" ayat").toString()

        detailViewModel.getSuratById(id)

        showRecyclerList()


        detailViewModel.loading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        detailViewModel.getSuratFromFavorite(id)

        detailViewModel.insertFavoriteSurat.observe(viewLifecycleOwner) {
            Toast.makeText(context, getString(R.string.insert_success), Toast.LENGTH_SHORT).show()
            binding.fab.setImageResource(R.drawable.ic_bookmark)
        }

        detailViewModel.deleteFavoriteSurat.observe(viewLifecycleOwner) {
            Toast.makeText(context, getString(R.string.delete_success), Toast.LENGTH_SHORT).show()
            binding.fab.setImageResource(R.drawable.ic_bookmark_border)
        }


        detailViewModel.message.observe(viewLifecycleOwner) {message->
            when (message) {
                "Success" -> {
                    binding.tvNoInternet.visibility = View.GONE
                    binding.lottieAnimation.visibility = View.GONE
                }
                "Failed" -> {
                    binding.tvNoInternet.visibility = View.VISIBLE
                    binding.lottieAnimation.visibility = View.VISIBLE
                }
                else -> {
                    binding.tvNoInternet.visibility = View.GONE
                    binding.lottieAnimation.visibility = View.GONE
                }
            }
        }



        detailViewModel.suratFavorite.observe(viewLifecycleOwner) {surat->
            if (surat != null) {
                binding.fab.setImageResource(R.drawable.ic_bookmark)
            } else {
                binding.fab.setImageResource(R.drawable.ic_bookmark_border)
            }
            Log.d("DetailFragmentss", "onViewCreated: $surat")
            binding.fab.setOnClickListener {
                if (surat == null) {
                    detailViewModel.saveToFavoriteSurat(detailSurat)
                } else {
                    detailViewModel.deleteFromFavoriteSurat(detailSurat)
                }
            }
        }

    }

    private fun showRecyclerList() {
        binding.rvDetail.layoutManager = LinearLayoutManager(context)
        val listAyatAdapter = ListAyatAdapter()
        binding.rvDetail.adapter = listAyatAdapter
        detailViewModel.surat.observe(viewLifecycleOwner) {
            listAyatAdapter.submitList(
                it.ayat
            )
        }

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