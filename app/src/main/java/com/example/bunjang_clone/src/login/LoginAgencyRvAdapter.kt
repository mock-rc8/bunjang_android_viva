package com.example.bunjang_clone.src.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bunjang_clone.databinding.ItemLoginAgencyBinding
import com.example.bunjang_clone.src.login.models.LoginAgencyData

class LoginAgencyRvAdapter(context: Context, AgencyList : ArrayList<LoginAgencyData>) : RecyclerView.Adapter<LoginAgencyRvAdapter.LoginAgencyViewHolder>() {

    private lateinit var clickListener : OnItemClickListener
    var itemList : ArrayList<LoginAgencyData> = AgencyList

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
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}