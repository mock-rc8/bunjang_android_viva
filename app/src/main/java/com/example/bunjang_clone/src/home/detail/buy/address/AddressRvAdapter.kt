package com.example.bunjang_clone.src.home.detail.buy.address

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bunjang_clone.databinding.ItemAddAddressBinding
import com.example.bunjang_clone.src.home.detail.buy.address.models.AddressData

class AddressRvAdapter(context: Context, private val dataList : ArrayList<AddressData>) : RecyclerView.Adapter<AddressRvAdapter.AddressViewHolder>() {

    inner class AddressViewHolder(var binding: ItemAddAddressBinding) : RecyclerView.ViewHolder(binding.root) {
        fun find(item : AddressData) {
            binding.tvItemAddressName.text = item.name
            binding.tvAddressPhoneNumber.text = item.phoneNumber
            binding.tvItemAddress.text = item.address
            binding.tvAddressDetail.text = item.addressDetail
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAddAddressBinding.inflate(layoutInflater, parent, false)
        return AddressViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.find(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}