package com.example.bunjang_clone.src.register

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bunjang_clone.databinding.ItemUploadCategoryBinding
import com.example.bunjang_clone.src.register.models.CategoryResult

class CategoryRvAdapter(item: MutableList<CategoryResult>) : RecyclerView.Adapter<CategoryRvAdapter.CategoryViewHolder>() {

    private lateinit var categoryClickListener : CategoryClickListener
    private var categoryList = item


    interface CategoryClickListener {
        fun onClick(view: View, position: Int, name: String)
    }
    fun categoryClickListener(categoryClickListener: CategoryClickListener) {
        this.categoryClickListener = categoryClickListener
    }

    inner class CategoryViewHolder(val binding: ItemUploadCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(items: CategoryResult) {
            binding.itemCategoryTitle.text = items.categoryName

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUploadCategoryBinding.inflate(layoutInflater, parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])

        holder.itemView.setOnClickListener {
            categoryClickListener.onClick(it, categoryList[position].mainCategoryIdx, categoryList[position].categoryName)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
}