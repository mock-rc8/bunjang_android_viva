package com.example.bunjang_clone.src.home.detail.buy.address.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bunjang_clone.databinding.ItemAddAddressBinding
import com.example.bunjang_clone.databinding.ItemAddressBinding
import com.example.bunjang_clone.src.home.detail.buy.address.models.AddressData
import com.example.bunjang_clone.src.login.adapter.LoginAgencyRvAdapter
import com.example.bunjang_clone.src.login.models.LoginAgencyData

class AddressDialogRvAdapter(context: Context, dataList: ArrayList<AddressData>) :
    RecyclerView.Adapter<AddressDialogRvAdapter.AddressDialogViewHolder>() {

    private lateinit var addressClickListener : OnItemClickListener

    var itemList : ArrayList<AddressData> = dataList

    interface OnItemClickListener {
        fun onClick(view: View, position: Int)
    }
    fun clickListener(addressClickListener: OnItemClickListener) {
        this.addressClickListener = addressClickListener
    }

    inner class AddressDialogViewHolder(var binding: ItemAddressBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun find(item: AddressData) {
            binding.itemAddressName.text = item.name
            binding.itemAddressPhone.text = item.phoneNumber
            binding.itemMainAddress.text = item.address
            binding.itemSubAddress.text = item.addressDetail
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressDialogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemAddressBinding.inflate(layoutInflater, parent, false)
        return AddressDialogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddressDialogViewHolder, position: Int) {
        holder.find(itemList[position])

        holder.itemView.setOnClickListener {
            addressClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setAddressItem(items : ArrayList<AddressData>) {
        if (!items.isNullOrEmpty()){
            itemList = items
            notifyDataSetChanged()
        }
    }
}