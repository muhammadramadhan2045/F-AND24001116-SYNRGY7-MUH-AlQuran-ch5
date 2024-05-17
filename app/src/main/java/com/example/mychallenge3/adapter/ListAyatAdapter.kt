package com.example.mychallenge3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mychallenge3.data.model.Surat
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