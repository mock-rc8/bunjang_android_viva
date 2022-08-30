package com.example.bunjang_clone.src.register

import android.graphics.Color
import android.os.Bundle
import com.example.bunjang_clone.R
import com.example.bunjang_clone.config.BaseActivity
import com.example.bunjang_clone.databinding.ActivityRegisterBinding
import com.example.bunjang_clone.src.register.models.RegisterResponse

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate), RegisterActivityInterface, OptionDialogFragment.OnDataPass {

    var itemCnt = 1
    var isExchange = false
    var isNew = false


    var isPay = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            val optionFragment = OptionDialogFragment(itemCnt, isExchange, isNew)
            optionFragment.show(supportFragmentManager, optionFragment.tag)
        }
    }

    override fun onRegisterSuccess(response: RegisterResponse) {
        TODO("Not yet implemented")
    }

    override fun onRegisterFail(message: String) {
        TODO("Not yet implemented")
    }

    override fun onDataPass(itemCnt: Int, isExchange: Boolean, isNew: Boolean) {
        TODO("Not yet implemented")
    }
}