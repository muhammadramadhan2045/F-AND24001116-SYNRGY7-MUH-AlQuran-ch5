package com.example.mychallenge3.adapter

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mychallenge3.R
import com.example.mychallenge3.domain.model.Surat
import com.example.mychallenge3.data.source.remote.response.AyatItem
import com.example.mychallenge3.databinding.ItemAyatBinding

class   ListAyatAdapter: ListAdapter<AyatItem, ListAyatAdapter.ListAyatViewHolder>(DIFF_CALLBACK){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListAyatViewHolder {
        val binding = ItemAyatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListAyatViewHolder(binding)
    }

    class ListAyatViewHolder(private val binding: ItemAyatBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: AyatItem) {
            binding.tvIdAyat.text = data.nomorAyat.toString()
            binding.tvArabicAyat.text = data.teksArab
            binding.tvLatinAyat.text = data.teksLatin
            binding.tvTerjemahanAyat.text = data.teksIndonesia
            val context = binding.root.context
            // Function to resolve the color attribute
            fun resolveColorAttribute(attr: Int): Int {
                val typedValue = TypedValue()
                context.theme.resolveAttribute(attr, typedValue, true)
                return if (typedValue.resourceId != 0) {
                    ContextCompat.getColor(context, typedValue.resourceId)
                } else {
                    typedValue.data
                }
            }

            val colorTertiaryContainer = resolveColorAttribute(com.google.android.material.R.attr.colorTertiaryContainer)
            val colorOnPrimary = resolveColorAttribute(com.google.android.material.R.attr.colorOnPrimary)
            //when nomor ayat is even, set background color to white
            if (data.nomorAyat % 2 == 1) {
                binding.clItemAyat.setBackgroundColor(colorTertiaryContainer).apply {  }
            }else{
                binding.clItemAyat.setBackgroundColor(colorOnPrimary)
            }
        }

    }

    override fun onBindViewHolder(holder: ListAyatViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    companion object{
        val DIFF_CALLBACK=object : DiffUtil.ItemCallback<AyatItem>(){
            override fun areItemsTheSame(oldItem: AyatItem, newItem: AyatItem): Boolean {
                return oldItem==newItem
            }

            override fun areContentsTheSame(oldItem: AyatItem, newItem: AyatItem): Boolean {
                return oldItem==newItem
            }

        }
    }

}