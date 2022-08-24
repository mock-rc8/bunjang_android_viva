package com.example.bunjang_clone.src.login.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bunjang_clone.R
import com.example.bunjang_clone.databinding.ItemLoginAgencyBinding
import com.example.bunjang_clone.src.login.models.LoginAgencyData

class LoginAgencyRvAdapter(context: Context, AgencyList : ArrayList<LoginAgencyData>) : RecyclerView.Adapter<LoginAgencyRvAdapter.LoginAgencyViewHolder>() {

    private lateinit var clickListener : OnItemClickListener
    var itemList : ArrayList<LoginAgencyData> = AgencyList

    private val context = context

    interface OnItemClickListener {
        fun onClick(view: View, position: Int)
    }
    fun clickListener(clickListener: OnItemClickListener) {
        this.clickListener = clickListener
    }

    inner class LoginAgencyViewHolder(val binding: ItemLoginAgencyBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: LoginAgencyData) {
            binding.tvLoginAgency.text = item.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginAgencyViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLoginAgencyBinding.inflate(layoutInflater, parent, false)
        return LoginAgencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoginAgencyViewHolder, position: Int) {
        holder.bind(itemList[position])

        holder.itemView.setOnClickListener {
            clickListener.onClick(it, position)
        }

        if (itemList[position].click) {
            holder.binding.tvLoginAgency.setTextColor(ContextCompat.getColor(context, R.color.light_red))
            holder.binding.ivLoginAgencyClick.setImageResource(R.drawable.icon_radio_click)
        } else {
            holder.binding.tvLoginAgency.setTextColor(ContextCompat.getColor(context, R.color.black))
            holder.binding.ivLoginAgencyClick.setImageResource(R.drawable.icon_radio_unclick)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setAgencyItem(items : ArrayList<LoginAgencyData>) {
        if (!items.isNullOrEmpty()){
            itemList = items
            notifyDataSetChanged()
        }
    }
}