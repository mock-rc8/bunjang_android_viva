package com.example.bunjang_clone.src.register

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bunjang_clone.databinding.ItemRegisterImgUploadBinding

class RegisterPictureAdapter(private val items: ArrayList<Uri>, val context: Context) : RecyclerView.Adapter<RegisterPictureAdapter.PictureViewHolder>() {

    inner class PictureViewHolder(val binding: ItemRegisterImgUploadBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Uri) {
            Glide.with(binding.root.context)
                .load(item)
                .into(binding.itemImgUpload)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRegisterImgUploadBinding.inflate(layoutInflater, parent, false)
        return PictureViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}