package com.example.bunjang_clone.src.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.example.bunjang_clone.R
import com.example.bunjang_clone.config.BaseFragment
import com.example.bunjang_clone.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
    
    private val information = arrayListOf("추천상품", "브랜드")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeAdapter = HomeVpAdapter(this)
        binding.vpPdBrand.adapter = homeAdapter
        TabLayoutMediator(binding.tlHomeProduct, binding.vpPdBrand) {
            tab,position ->
            tab.text = information[position]
        }.attach()
    }
}