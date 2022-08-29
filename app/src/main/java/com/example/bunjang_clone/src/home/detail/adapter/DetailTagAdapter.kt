package com.example.bunjang_clone.src.home.detail.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bunjang_clone.databinding.ItemDetailTagBinding

class DetailTagAdapter(item : MutableList<String>) : RecyclerView.Adapter<DetailTagAdapter.DetailTagViewHolder>() {

    private val tagList = item

    inner class DetailTagViewHolder(val binding: ItemDetailTagBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            Log.d("taglist", "$item")
            binding.tvProductTag.text = "#$item"

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailTagViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemDetailTagBinding.inflate(layoutInflater, parent, false)
        return DetailTagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailTagViewHolder, position: Int) {
        holder.bind(tagList[position])
        holder.binding.tvProductTag.text = tagList[position].replace("#","")
    }

    override fun getItemCount(): Int {
        return tagList.size
    }

}