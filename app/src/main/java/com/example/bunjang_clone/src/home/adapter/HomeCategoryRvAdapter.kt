package com.example.bunjang_clone.src.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bunjang_clone.databinding.ItemMainCategoryBinding
import com.example.bunjang_clone.src.home.models.HomeCategoryData
import com.example.bunjang_clone.src.login.models.LoginAgencyData

class HomeCategoryRvAdapter(context: Context, categoryList : MutableList<HomeCategoryData>) : RecyclerView.Adapter<HomeCategoryRvAdapter.HomeCategoryViewHolder>() {

    var itemList : MutableList<HomeCategoryData> = categoryList

    val pContext = context


    inner class HomeCategoryViewHolder(val binding: ItemMainCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : HomeCategoryData) {
            Glide.with(pContext)
                .load(item.iconImg)
                .into(binding.itemHomeCategory)

            binding.tvHomeZzim.text = item.categoryName
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeCategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMainCategoryBinding.inflate(layoutInflater, parent, false)
        return HomeCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeCategoryViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}