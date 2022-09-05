package com.example.bunjang_clone.src.home

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.bunjang_clone.R
import com.example.bunjang_clone.config.BaseFragment
import com.example.bunjang_clone.databinding.FragmentHomeBinding
import com.example.bunjang_clone.src.home.adapter.AdSliderAdapter
import com.example.bunjang_clone.src.home.adapter.HomeCategoryRvAdapter
import com.example.bunjang_clone.src.home.adapter.HomeVpAdapter
import com.example.bunjang_clone.src.home.models.*
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.abs
import kotlin.math.min

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home), ShopNameActivityInterface, HomeAdActivityInterface {

    private var adViewThred = true

    private lateinit var adTread : Thread

    private lateinit var adSliderAdapter: AdSliderAdapter
    private lateinit var homeCategoryAdapter : HomeCategoryRvAdapter

    private var adPageCnt = 0

    private lateinit var items: ShopResult
    private lateinit var adItems: AdResult
    
    private val information = arrayListOf("추천상품", "브랜드\uD83D\uDD34")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getShopName()
        getHomeAd()

        // 중간 추천상품, 브랜드
        productAdapter()

        setCategory()

        binding.appbarHome.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val alpha = min(abs(verticalOffset / 3), 255)
            binding.toolbarHome.setBackgroundColor(Color.argb(alpha, 255, 255, 255))
            if (alpha > 255 / 2){
                binding.ivHomeMore.setColorFilter(Color.rgb(0,0,0))
                binding.ivHomeAlarm.setColorFilter(Color.rgb(0,0,0))
                binding.ivHomeSearch.setColorFilter(Color.rgb(0,0,0))
            } else {
                binding.ivHomeMore.setColorFilter(Color.rgb(255,255,255))
                binding.ivHomeAlarm.setColorFilter(Color.rgb(255,255,255))
                binding.ivHomeSearch.setColorFilter(Color.rgb(255,255,255))
            }
        })

    }

    private fun setCategory() {
        val categorylist = mutableListOf<HomeCategoryData>()
        categorylist.add(HomeCategoryData(R.drawable.icon_home_zzim, "찜"))
        categorylist.add(HomeCategoryData(R.drawable.icon_home_apc, "아페세"))
        categorylist.add(HomeCategoryData(R.drawable.icon_home_lately, "최근본상품"))
        categorylist.add(HomeCategoryData(R.drawable.icon_home_sneakers, "스니커즈"))
        categorylist.add(HomeCategoryData(R.drawable.icon_home_feed, "내피드"))
        categorylist.add(HomeCategoryData(R.drawable.icon_home_camping, "캠핑"))
        categorylist.add(HomeCategoryData(R.drawable.icon_home_price, "내폰시세"))
        categorylist.add(HomeCategoryData(R.drawable.icon_home_bike, "자전거"))
        categorylist.add(HomeCategoryData(R.drawable.icon_home_village, "우리동네"))
        categorylist.add(HomeCategoryData(R.drawable.icon_home_star, "스타굿즈"))
        categorylist.add(HomeCategoryData(R.drawable.icon_home_friend, "친구초대"))
        categorylist.add(HomeCategoryData(R.drawable.icon_home_motorcycle, "오토바이/스..."))
        categorylist.add(HomeCategoryData(R.drawable.icon_home_menu, "전체메뉴"))
        categorylist.add(HomeCategoryData(R.drawable.icon_home_clock, "시계"))

        homeCategoryAdapter = HomeCategoryRvAdapter(requireContext(), categorylist)
        binding.rvHomeCategory.adapter = homeCategoryAdapter

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.rvHomeCategory.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                binding.hsvHomeIndicator.scrollX = ((binding.rvHomeCategory.computeHorizontalScrollOffset()/400.0)*200.0).toInt()
            }
        }


    }

    private fun getShopName() {
        ShopNameService(this).shopNameData()
    }

    private fun getHomeAd() {
        HomeAdService(this).homAdData()
    }

    private fun productAdapter() {
        val homeAdapter = HomeVpAdapter(this)
        binding.vpPdBrand.adapter = homeAdapter
        TabLayoutMediator(binding.tlHomeProduct, binding.vpPdBrand) {
                tab,position ->
            tab.text = information[position]
        }.attach()
    }

    override fun onDestroy() {
        super.onDestroy()

        adViewThred = false
    }

    override fun onShopNameSuccess(response: ShopNameResponse) {
        items = response.result
        binding.tvHomeIdTitle.text = "전국에서 갓 올라온\n${response.result.storeName}의 취향"
        Log.d("shopname", "${response.result.storeName}")
    }

    override fun onShopNameFail(message: String) {
    }

    override fun onHomAdSuccess(response: HomeAdResponse) {
        adItems = response.result
        var adList = adItems.adImageList.toMutableList()

        adSliderAdapter = AdSliderAdapter(adList)

        binding.vpHomeAd.adapter = adSliderAdapter

        binding.vpHomeAd.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.vpHomeAd.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tvHomeAdPage.text = "${binding.vpHomeAd.currentItem + 1}/${adList.size}"

                adPageCnt = binding.vpHomeAd.currentItem
                super.onPageSelected(position)
            }
        })

        adTread = Thread() {
            val handler = Handler(Looper.getMainLooper())
            while (adViewThred) {
                handler.post {
                    binding.vpHomeAd.setCurrentItem(adPageCnt++ % adList.size, false)
                }
                Thread.sleep(2000)
            }
        }
        adTread.start()
    }

    override fun onHomAdFail(message: String) {
    }
}