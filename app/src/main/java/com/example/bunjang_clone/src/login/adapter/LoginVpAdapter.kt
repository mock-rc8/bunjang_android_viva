package com.example.bunjang_clone.src.login.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bunjang_clone.databinding.LoginViewpagerBinding
import com.example.bunjang_clone.src.login.models.ViewPagerAd

class LoginVpAdapter(ViewPagerList : ArrayList<ViewPagerAd>) : RecyclerView.Adapter<LoginVpAdapter.LoginViewHolder>() {

    private val itemlist = ViewPagerList

    inner class LoginViewHolder(val binding: LoginViewpagerBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : ViewPagerAd){

            binding.ivLoginViewpagerText1.setImageResource(item.bigtest)
            binding.tvLoginViewpagerText1.text = item.smalltest
            binding.ivLoginViewpagerImg1.setImageResource(item.img)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoginViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LoginViewpagerBinding.inflate(layoutInflater, parent, false)
        return LoginViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoginViewHolder, position: Int) {
        holder.bind(itemlist[position])
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }
}