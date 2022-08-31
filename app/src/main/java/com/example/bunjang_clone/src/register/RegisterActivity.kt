package com.example.bunjang_clone.src.register

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.example.bunjang_clone.R
import com.example.bunjang_clone.config.BaseActivity
import com.example.bunjang_clone.databinding.ActivityRegisterBinding
import com.example.bunjang_clone.src.home.detail.adapter.DetailTagAdapter
import com.example.bunjang_clone.src.register.models.RegisterData
import com.example.bunjang_clone.src.register.models.RegisterResponse

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate), RegisterActivityInterface, OptionDialogFragment.OnDataOption,
AreaFragment.OnDataArea{

    lateinit var ImageUri : Uri

    var itemCnt = 1
    var itemStatus = false
    var itemexchange = false

    var myArea = ""

    var delivery = false
    var isPay = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRv()

        var userId = intent.getIntExtra("userIdx", 0)
        var area = intent.getStringExtra("area")
        Log.d("area","$area")


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

        binding.ivRegisterDeliveryBtn.setOnClickListener {
            if (delivery) {
                delivery = false
                binding.ivRegisterDeliveryBtn.setImageResource(R.drawable.icon_all_unagree)
            } else {
                delivery = true
                binding.ivRegisterDeliveryBtn.setImageResource(R.drawable.icon_all_agree)
            }
        }

        binding.tvRegisterChoiceOption.setOnClickListener {
            val optionFragment = OptionDialogFragment(itemCnt, itemStatus, itemexchange)
            optionFragment.show(supportFragmentManager, optionFragment.tag)
        }

        binding.tvDealArea.setOnClickListener {
            val areaFragment = AreaFragment(myArea)
            areaFragment.show(supportFragmentManager, areaFragment.tag)
        }

        binding.btnRegisterUp.setOnClickListener {

            var productName = binding.etRegisterProductName.text.toString()
            var hashtag = binding.etRegisterProductTags.text.toString()
            var productPrice = binding.etRegisterProductPrice.text.toString().toInt()
            var productExplain = binding.etRegisterProductDetailExplain.text.toString()
            var shopping = if (delivery) "배송비 포함" else "배송비 별도"
            var pay = if (isPay) 1 else 0
            var amount = itemCnt
            var status = if (itemStatus) "새상품" else "중고상품"
            var change = if (itemexchange) "교환상품" else "교환불가능"
            var imageURL = ImageUri

            var areaAddress = myArea

            Log.d("productRegister","$userId,$productName,$imageURL, $hashtag,$productPrice,$productExplain,$shopping,$pay,$amount,$status,$change, ")

//            postProductItem(RegisterData(userIdx = userId, productsName = productName, imageURL= , subImageURL= , address= , mainCategory= , subCategory= ,
//                hashtagText= hashtag, price= productPrice, shoppingFee= shopping, quantity= amount, productStatus= status, exchange= change,
//                productExplaination= productExplain, pay= pay))
        }
    }
//    fun postProductItem(registerData: RegisterData) {
//        RegisterService(this).tryRegisterData(registerData)
//    }

    fun setRv() {
        binding.ivRegisterPicture.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT

            startActivityForResult(intent, 100)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 100 && resultCode == RESULT_OK) {

            ImageUri = data?.data!!

            binding.itemImgUpload.setImageURI(ImageUri)
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

    override fun onDataOption(myArea: String) {
        this.myArea = myArea

        binding.tvDealArea.text = myArea
    }
}