package com.example.bunjang_clone.src.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bunjang_clone.databinding.ItemHomeAdSliderBinding

class AdSliderAdapter(adViewList: ArrayList<Int>) : RecyclerView.Adapter<AdSliderAdapter.AdViewHolder>() {

    private val itemList = adViewList

    inner class AdViewHolder(val binding: ItemHomeAdSliderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Int){
            Glide.with(binding.root.context)
                .load(item)
                .into(binding.itemAdSlider)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeAdSliderBinding.inflate(layoutInflater, parent, false)
        return AdViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}