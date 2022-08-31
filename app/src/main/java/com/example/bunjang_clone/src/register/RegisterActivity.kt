package com.example.bunjang_clone.src.register

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import com.example.bunjang_clone.R
import com.example.bunjang_clone.config.BaseActivity
import com.example.bunjang_clone.databinding.ActivityRegisterBinding
import com.example.bunjang_clone.src.register.models.RegisterResponse

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate), RegisterActivityInterface, OptionDialogFragment.OnDataOption {

    var itemCnt = 1
    var itemStatus = false
    var itemexchange = false

    var isPay = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var userId = intent.getIntExtra("userIdx", 0)
        Log.d("userIdx","$userId")

        binding.clRegisterSafePay.setOnClickListener {
            if (isPay) {
                isPay = false
                binding.clRegisterSafePay.setBackgroundResource(R.drawable.btn_product_event)
                binding.tvRegisterSafeChargeTitle.setTextColor(Color.GRAY)
                binding.ivRegisterPayCheck.setImageResource(R.drawable.icon_pay_uncheck)
            } else {
                isPay = true
                binding.clRegisterSafePay.setBackgroundResource(R.drawable.btn_product_red)
                binding.tvRegisterSafeChargeTitle.setTextColor(Color.BLACK)
                binding.ivRegisterPayCheck.setImageResource(R.drawable.icon_pay_check)
            }
        }
        binding.tvRegisterChoiceOption.setOnClickListener {
            val optionFragment = OptionDialogFragment(itemCnt, itemStatus, itemexchange)
            optionFragment.show(supportFragmentManager, optionFragment.tag)
        }

        binding.btnRegisterUp.setOnClickListener {


        }
    }

    override fun onRegisterSuccess(response: RegisterResponse) {
    }

    override fun onRegisterFail(message: String) {
    }

    override fun onDataOption(itemCnt: Int, itemStatus: Boolean, itemexchange: Boolean) {
        this.itemCnt = itemCnt
        this.itemStatus = itemStatus
        this.itemexchange = itemexchange

        var productCount = ""
        productCount = itemCnt.toString() + "개 • "
        productCount += if (itemStatus) "새상품 • " else "중고상품 • "
        productCount += if (itemexchange) "교환가능" else "교환불가능"

        binding.tvRegisterOptions.text = productCount
    }
}