package com.example.bunjang_clone.src.home.detail.buy

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bunjang_clone.R
import com.example.bunjang_clone.databinding.ItemShippingOptionBinding
import com.example.bunjang_clone.src.home.detail.buy.models.shippingAgree
import com.example.bunjang_clone.src.login.models.LoginAgencyData

class ShippingRvAdapter(context: Context, ChoiceList: ArrayList<shippingAgree>) :
    RecyclerView.Adapter<ShippingRvAdapter.ShippingOptionViewHolder>() {

    var itemList: ArrayList<shippingAgree> = ChoiceList
    private lateinit var clickListener : ShippingRvAdapter.OnChoiceClickListener

    private val context = context

    interface OnChoiceClickListener {
        fun onClick(view: View, position: Int)
    }
    fun clickListener(clickListener: OnChoiceClickListener) {
        this.clickListener = clickListener
    }

    inner class ShippingOptionViewHolder(val binding: ItemShippingOptionBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(item : shippingAgree){
                binding.tvBuyShippingOptionWhere.text = item.text

            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShippingOptionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemShippingOptionBinding.inflate(layoutInflater, parent, false)
        return ShippingOptionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShippingOptionViewHolder, position: Int) {
        holder.bind(itemList[position])

        holder.itemView.setOnClickListener {
            clickListener.onClick(it, position)
        }

        if (itemList[position].isCheck) {
            holder.binding.tvBuyShippingOptionWhere.setTextColor(ContextCompat.getColor(context, R.color.light_red))
        } else {
            holder.binding.tvBuyShippingOptionWhere.setTextColor(ContextCompat.getColor(context, R.color.black))
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setShippingItem(items : ArrayList<shippingAgree>) {
        if (!items.isNullOrEmpty()){
            itemList = items
            notifyDataSetChanged()
        }
    }
}