package com.example.mychallenge3.view.detail


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
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
        val detailSurat= DetailFragmentArgs.fromBundle(arguments as Bundle).detailSurat

        binding.toolbar.title = name

        detailViewModel.getSuratById(id)

        showRecyclerList()


        detailViewModel.loading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }

        detailViewModel.getSuratFromFavorite(id)





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