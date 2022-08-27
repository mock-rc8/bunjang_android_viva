package com.example.bunjang_clone.src.home.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bunjang_clone.databinding.ItemProductImgSliderBinding
import com.example.bunjang_clone.src.home.detail.models.DetailResult

//class ProductSliderAdapter() : RecyclerView.Adapter<ProductSliderAdapter.ImgViewHolder>() {
//
//
//
//    inner class ImgViewHolder(val binding: ItemProductImgSliderBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(item: String){
//            Glide.with(binding.root.context)
//                .load(item)
//                .into(binding.itemProductImgSlider)
//        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImgViewHolder {
//        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding = ItemProductImgSliderBinding.inflate(layoutInflater, parent, false)
//        return ImgViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ImgViewHolder, position: Int) {
//        holder.bind(imgList[position])
//    }
//
//    override fun getItemCount(): Int {
//        return imgList.size
//    }
//}