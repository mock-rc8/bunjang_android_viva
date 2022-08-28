package com.example.bunjang_clone.src.home.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.bunjang_clone.config.BaseActivity
import com.example.bunjang_clone.databinding.ActivityProductDetailBinding
import com.example.bunjang_clone.src.home.detail.models.DetailResponse
import com.example.bunjang_clone.src.home.detail.models.DetailResult
import java.text.DecimalFormat

class ProductDetailActivity() : BaseActivity<ActivityProductDetailBinding>(ActivityProductDetailBinding::inflate), DetailActivityInterface {

    var productIdx = 0
    private var prdouctPageCnt = 0

    private lateinit var tagAdapter : DetailTagAdapter

    private lateinit var imgAdapter : ProductSliderAdapter

    var vpImgSlider = mutableListOf<String>()

    private lateinit var items: DetailResult
    val priceFormat = DecimalFormat("#,###")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        productIdx = intent.getIntExtra("productIdx", 0)
        getDetailData(productIdx)
    }

//    private fun imgViewPage() {
//        binding.vpProduct.adapter = imgSliderAdapter
//
//        binding.vpProduct.orientation = ViewPager2.ORIENTATION_HORIZONTAL
//
//        binding.vpProduct.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
//            override fun onPageSelected(position: Int) {
//                binding.tvProductPage.text = "${binding.vpProduct.currentItem + 1}/${}"
//
//                adPageCnt = binding.vpHomeAd.currentItem
//                super.onPageSelected(position)
//            }
//        })
//    }

    private fun getDetailData(productIdx: Int) {
        DetailService(this).detailGetData(productIdx)
    }


    override fun onGetDetailDataSuccess(response: DetailResponse) {

        items = response.result

        binding.tvProductPrice.text = priceFormat.format(items.price).toString() + "원"
        if (items.pay == 0){
            binding.ivProductPay.visibility = View.VISIBLE
        } else {
            binding.ivProductPay.visibility = View.GONE
        }
        binding.tvProductTitle.text = items.productName
        binding.tvProductLocation.text = items.address
        binding.tvProductTime.text = items.created
        binding.tvProductTalk.text = items.talk.toString()
        binding.tvProductHeart.text = items.heart.toString()
        binding.tvProductEye.text = items.views.toString()
        binding.tvProductExplain1.text = items.status
        binding.tvProductExplain2.text = "총" +items.quantity.toString()+"개"
        binding.tvProductExplain3.text = items.shippingFee
        binding.tvProductExplain4.text = items.exchange
        binding.tvProductTitleExplain.text = items.productExplaination

        var detailTag = items.hashtag.toMutableList()
        Log.d("detailTag", "$detailTag")
        tagAdapter = DetailTagAdapter(detailTag)
        binding.rvDetailTag.adapter = tagAdapter

        var imgList = items.productImgURLlist.toMutableList()
        imgAdapter = ProductSliderAdapter(imgList)
        binding.vpProduct.adapter = imgAdapter

        binding.vpProduct.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tvProductPage.text = "${binding.vpProduct.currentItem + 1}/${imgList.size}"

                prdouctPageCnt = binding.vpProduct.currentItem
                super.onPageSelected(position)
            }
        })

    }

    override fun onGetDetailDataFail(message: String) {

    }
}