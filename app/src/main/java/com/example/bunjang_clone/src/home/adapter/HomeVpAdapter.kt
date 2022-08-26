package com.example.bunjang_clone.src.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bunjang_clone.src.home.BrandFragment
import com.example.bunjang_clone.src.home.RecommendPdFragment

class HomeVpAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> RecommendPdFragment()
            else -> BrandFragment()
        }
    }
}