package com.example.bunjang_clone.src.register

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import com.example.bunjang_clone.R
import com.example.bunjang_clone.config.ApplicationClass
import com.example.bunjang_clone.config.ApplicationClass.Companion.fbStorage
import com.example.bunjang_clone.config.BaseActivity
import com.example.bunjang_clone.databinding.ActivityRegisterBinding
import com.example.bunjang_clone.src.MainActivity
import com.example.bunjang_clone.src.register.models.CategoryData
import com.example.bunjang_clone.src.register.models.RegisterData
import com.example.bunjang_clone.src.register.models.RegisterResponse
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate), RegisterActivityInterface, OptionDialogFragment.OnDataOption,
AreaFragment.OnDataArea, CategoryFragment.OnDataCategory{

    lateinit var ImageUri : Uri

    var itemCnt = 1
    var itemStatus = false
    var itemexchange = false

    var myArea = ""

    var mainCategory =""
    var subCategory =""

    var delivery = false
    var isPay = false

    var fileName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var mainimg = "https://user-images.githubusercontent.com/96619472/188822417-0165d8d4-7d49-4c46-8674-35f5384a403e.jpg"
        var subimglist = listOf(
            "https://user-images.githubusercontent.com/96619472/188822426-a55d2cc1-4719-41f5-a7aa-b39371be841d.jpg",
            "https://user-images.githubusercontent.com/96619472/188822428-6b63bd15-1fdc-476f-b1ef-60b2bbf39e18.jpg"
        )

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

        binding.tvRegisterProductCategories.setOnClickListener {
            val categoryFragment = CategoryFragment(mainCategory)
            categoryFragment.show(supportFragmentManager, categoryFragment.tag)
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
            var shopping = if (delivery) "????????? ??????" else "????????? ??????"
            var pay = if (isPay) 1 else 0
            var amount = itemCnt
            var status = if (itemStatus) "?????????" else "????????????"
            var change = if (itemexchange) "????????????" else "???????????????"
            var imageUrl = mainimg
            var subimgUrl = subimglist
            var mainCategory = mainCategory
            var subCategory = "PC??????"
            var areaAddress = myArea

            Log.d("productRegister","$userId,$productName,$imageUrl,$subimgUrl,$areaAddress,$mainCategory, $subCategory, $hashtag," +
                    "$productPrice,$productExplain,$shopping,$pay,$amount,$status,$change")

            postProductItem(RegisterData(userIdx = userId, productsName = productName, imageURL= imageUrl, subImageURL= subimgUrl, address= areaAddress,
                mainCategory= mainCategory, subCategory= subCategory, hashtagText= hashtag, price= productPrice, shoppingFee= shopping, quantity= amount,
                productStatus= status, exchange= change, productExplaination= productExplain, pay= pay))
        }
    }
    fun postProductItem(registerData: RegisterData) {
        RegisterService(this).tryRegisterData(registerData)
    }

    fun setUploadImage() {
        val storageReference = fbStorage.child("image")

        storageReference.putFile(ImageUri).addOnCompleteListener{
            if (it.isSuccessful){
                storageReference.child("image").downloadUrl.addOnSuccessListener {
                    val imageurl = it
                    Log.d("imageurl","$imageurl")
                }
            }
        }

    }

    fun setRv() {
        binding.itemImgUpload.setOnClickListener {
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
        if (response.isSuccess) {
            var intent = Intent(this, MainActivity::class.java)
            Toast.makeText(this, "?????? ??????", Toast.LENGTH_SHORT).show()
            startActivity(intent)
            finish()
        }
    }

    override fun onRegisterFail(message: String) {
    }

    override fun onMainCategorySuccess(response: CategoryData) {

    }

    override fun onMainCategoryFail(message: String) {

    }

    override fun onSubCategorySuccess(response: CategoryData) {

    }

    override fun onSubCategoryFail(message: String) {

    }

    override fun onDataOption(itemCnt: Int, itemStatus: Boolean, itemexchange: Boolean) {
        this.itemCnt = itemCnt
        this.itemStatus = itemStatus
        this.itemexchange = itemexchange

        var productCount = ""
        productCount = itemCnt.toString() + "??? ??? "
        productCount += if (itemStatus) "????????? ??? " else "???????????? ??? "
        productCount += if (itemexchange) "????????????" else "???????????????"

        binding.tvRegisterOptions.text = productCount
    }

    override fun onDataOption(myArea: String) {
        this.myArea = myArea

        binding.tvDealArea.text = myArea
    }

    override fun onDataCategory(mainCategory: String) {
        this.mainCategory = mainCategory

        binding.tvRegisterProductCategories.text = mainCategory
    }

}