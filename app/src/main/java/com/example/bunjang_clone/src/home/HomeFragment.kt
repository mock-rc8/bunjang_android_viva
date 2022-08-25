package com.example.bunjang_clone.src.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.bunjang_clone.R
import com.example.bunjang_clone.config.BaseFragment
import com.example.bunjang_clone.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    private var adViewThred = true

    private lateinit var adTread : Thread

    private lateinit var adSliderAdapter: AdSliderAdapter

    var adPageCnt = 0
    
    private val information = arrayListOf("추천상품", "브랜드\uD83D\uDD34")

    val adViewList = arrayListOf(R.drawable.img_home_ad1, R.drawable.img_home_ad2, R.drawable.img_home_ad3,
        R.drawable.img_home_ad4, R.drawable.img_home_ad5, R.drawable.img_home_ad6, R.drawable.img_home_ad7)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 광고 
        adViewPage()

        // 중간 추천상품, 브랜드
        productAdapter()

        adTread = Thread() {
            val handler = Handler(Looper.getMainLooper())
            while (adViewThred) {
                handler.post {
                    binding.vpHomeAd.setCurrentItem(adPageCnt++ % adViewList.size, false)
                }
                Thread.sleep(2000)
            }
        }
        adTread.start()

    }

    private fun productAdapter() {
        val homeAdapter = HomeVpAdapter(this)
        binding.vpPdBrand.adapter = homeAdapter
        TabLayoutMediator(binding.tlHomeProduct, binding.vpPdBrand) {
                tab,position ->
            tab.text = information[position]
        }.attach()
    }

    private fun adViewPage() {
        adSliderAdapter = AdSliderAdapter(adViewList)

        binding.vpHomeAd.adapter = adSliderAdapter

        binding.vpHomeAd.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.vpHomeAd.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tvHomeAdPage.text = "${binding.vpHomeAd.currentItem + 1}/${adViewList.size}"

                adPageCnt = binding.vpHomeAd.currentItem
                super.onPageSelected(position)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()

        adViewThred = false
    }
}