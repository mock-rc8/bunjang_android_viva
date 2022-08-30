package com.example.bunjang_clone.src.home.detail.buy

import android.graphics.Paint
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.core.widget.addTextChangedListener
import com.bumptech.glide.Glide
import com.example.bunjang_clone.config.BaseActivity
import com.example.bunjang_clone.databinding.ActivityDeliveryBuyBinding
import com.example.bunjang_clone.src.home.detail.buy.models.BuyResponse
import com.example.bunjang_clone.src.home.detail.buy.models.BuyResult
import java.text.DecimalFormat


class DeliveryBuyActivity() : BaseActivity<ActivityDeliveryBuyBinding>(ActivityDeliveryBuyBinding::inflate), BuyActivityInterface{

    var productIdx = 0
    var point = 0

    var allCommission = 0
    var commission = 0
    var payment = 0
    var finalPayment = 0

    private lateinit var items: BuyResult

    val priceFormat = DecimalFormat("#,###")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        productIdx = intent.getIntExtra("productBuyIdx", 0)
        Log.d("productid","$productIdx")
        getBuyData(productIdx)

        binding.tvBuyChargeTaxWon.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
    }

    private fun getBuyData(productIdx: Int) {
        BuyService(this).buyGetData(productIdx)
    }

    override fun onGetBuyDataSuccess(response: BuyResponse) {
        items = response.result

        Glide.with(this)
            .load(items.productImgURL)
            .into(binding.ivBuyProduct)

        binding.tvBuyPrice.text = priceFormat.format(items.productPrice).toString() + "원"
        binding.tvProductPrice.text = priceFormat.format(items.productPrice).toString() + "원"
        binding.tvBuyProductName.text = items.productName
        binding.tvDeliveryChargePrice.text = items.shippingFee
        binding.tvBuyNowPoint.text = items.point.toString()
        if (items.commission == "무료") {
            binding.tvBuyTaxPrice.text = "0원"
            binding.tvBuyChargeTaxWon.text = "0원"
        }
        binding.tvBuyPaymentPrice.text = items.productPrice.toString()

        binding.btnBuyUseAllPoint.setOnClickListener {
        }



        binding.etProductPoint.addTextChangedListener {
            if (binding.etProductPoint.text.toString() =="") {
                point = 0
                binding.tvBuyLightningPointPrice.text = "0원"
            } else {
                point = binding.etProductPoint.text.toString().toInt()
                binding.tvBuyLightningPointPrice.text = "-" + priceFormat.format(point) + "원"
            }
        }

    }

    override fun onGetBuyDataFail(message: String) {
    }
}