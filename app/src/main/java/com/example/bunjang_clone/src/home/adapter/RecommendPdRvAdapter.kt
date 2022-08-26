package com.example.bunjang_clone.src.home.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bunjang_clone.R
import com.example.bunjang_clone.databinding.ItemHomeRecommendBinding
import com.example.bunjang_clone.src.home.models.RecommendItem
import java.text.DecimalFormat

class RecommendPdRvAdapter() : RecyclerView.Adapter<RecommendPdRvAdapter.RecommendViewHolder>() {

    private var productList = mutableListOf<RecommendItem>()

    val priceFormat = DecimalFormat("#,###")

    inner class RecommendViewHolder(val binding: ItemHomeRecommendBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecommendItem) {
            Glide.with(binding.root.context)
                .load(item.productURL)
                .into(binding.ivItemHomeRecommend)

            binding.tvItemHomeRecommendPrice.text = "${priceFormat.format(item.price.toDouble())}원"
            binding.tvItemHomeRecommendTitle.text = item.productName
            binding.tvItemHomeRecommendLocation.text = item.address
            binding.tvItemHomeRecommendTime.text = item.created
            if (item.pay == 0){
                binding.ivItemHomeRecommendPay.visibility = View.VISIBLE
            } else {
                binding.ivItemHomeRecommendPay.visibility = View.GONE
            }
            if (item.userHeart == 0){
                binding.ivItemHomeRecommendHeart.setImageResource(R.drawable.icon_item_home_heart)
            } else {
                binding.ivItemHomeRecommendHeart.setImageResource(R.drawable.icon_item_home_heart_fill)
            }
            binding.tvItemHomeRecommendHits.text = item.heartCount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemHomeRecommendBinding.inflate(layoutInflater, parent, false)
        return RecommendViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendViewHolder, position: Int) {
        holder.bind(productList[position])

    }

    override fun getItemCount(): Int {
        return productList.size
    }
    fun setRecommendItem(items : MutableList<RecommendItem>) {
        productList = items.toMutableList()
        notifyDataSetChanged()
    }
}