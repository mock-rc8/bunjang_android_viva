package com.example.bunjang_clone.src.login

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bunjang_clone.R
import com.example.bunjang_clone.databinding.ItemLoginAgencyBinding
import com.example.bunjang_clone.databinding.ItemLoginAgreeBinding
import com.example.bunjang_clone.src.login.models.LoginAgencyData
import com.example.bunjang_clone.src.login.models.LoginAgreeData

class LoginAgreeRvAdapter(context: Context, AgreeList : ArrayList<LoginAgreeData>) : RecyclerView.Adapter<LoginAgreeRvAdapter.LoginAgreeViewHolder>() {

    private lateinit var clickListener : OnItemClickListener
    var agreeList : ArrayList<LoginAgreeData> = AgreeList

    private val context = context

    interface OnItemClickListener {
        fun onClick(view: View, position: Int)
    }
    fun clickListener(clickListener: OnItemClickListener) {
        this.clickListener = clickListener
    }

    inner class LoginAgreeViewHolder(val binding: ItemLoginAgreeBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: LoginAgreeData) {
            binding.tvDialogPermission.text = item.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginAgreeViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLoginAgreeBinding.inflate(layoutInflater, parent, false)
        return LoginAgreeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoginAgreeViewHolder, position: Int) {
        holder.bind(agreeList[position])

        holder.itemView.setOnClickListener {
            clickListener.onClick(it, position)
        }
        if (agreeList[position].isCheck){
            holder.binding.ivDialogAgree.setImageResource(R.drawable.icon_agree)
        } else {
            holder.binding.ivDialogAgree.setImageResource(R.drawable.icon_unagree)
        }
    }

    override fun getItemCount(): Int {
        return agreeList.size
    }

    fun setAgencyItem(items : ArrayList<LoginAgreeData>) {
        if (!items.isNullOrEmpty()){
            agreeList = items
            notifyDataSetChanged()
        }
    }
}