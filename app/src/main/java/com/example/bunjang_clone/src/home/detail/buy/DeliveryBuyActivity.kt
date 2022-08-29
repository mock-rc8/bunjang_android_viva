package com.example.bunjang_clone.src.home.detail.buy

import android.os.Bundle
import android.util.Log
import com.example.bunjang_clone.config.BaseActivity
import com.example.bunjang_clone.databinding.ActivityDeliveryBuyBinding

class DeliveryBuyActivity() : BaseActivity<ActivityDeliveryBuyBinding>(ActivityDeliveryBuyBinding::inflate) {

    var productIdx = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        productIdx = intent.getIntExtra("productBuyIdx", 0)
        Log.d("productid","$productIdx")
    }
}