package com.example.mychallenge3.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mychallenge3.data.model.Surat
import com.example.mychallenge3.databinding.ItemSuratBinding

class ListSurahAdapter  : ListAdapter<Surat, ListSurahAdapter.ListSurahViewHolder>(DIFF_CALLBACK){
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListSurahViewHolder {
        val binding = ItemSuratBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListSurahViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ListSurahViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(getItem(holder.adapterPosition))
        }
    }

    class ListSurahViewHolder(private val binding: ItemSuratBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(data: Surat){
            binding.tvItemName.text=data.namaLatin
            binding.tvItemDescription.text = StringBuilder().append(data.arti).append(" | ").append(data.jumlahAyat).append(" ayat")
            binding.tvArabicName.text = data.nama
        }

    }


    companion object{
        val DIFF_CALLBACK=object : DiffUtil.ItemCallback<Surat>(){
            override fun areItemsTheSame(oldItem: Surat, newItem: Surat): Boolean {
                return oldItem==newItem
            }

            override fun areContentsTheSame(oldItem: Surat, newItem: Surat): Boolean {
                return oldItem==newItem
            }

        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Surat)
    }

}