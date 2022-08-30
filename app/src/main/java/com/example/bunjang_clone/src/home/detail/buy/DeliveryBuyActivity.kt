package com.example.bunjang_clone.src.home.detail.buy

import android.graphics.Paint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bunjang_clone.R
import com.example.bunjang_clone.config.BaseActivity
import com.example.bunjang_clone.databinding.ActivityDeliveryBuyBinding
import com.example.bunjang_clone.src.home.detail.buy.models.BuyResponse
import com.example.bunjang_clone.src.home.detail.buy.models.BuyResult
import com.example.bunjang_clone.src.home.detail.buy.models.shippingAgree
import com.example.bunjang_clone.src.login.adapter.LoginAgencyRvAdapter
import com.example.bunjang_clone.src.login.models.LoginAgreeData
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.DecimalFormat


class DeliveryBuyActivity() : BaseActivity<ActivityDeliveryBuyBinding>(ActivityDeliveryBuyBinding::inflate), BuyActivityInterface{

    var productIdx = 0
    var point = 0

    var allCommission = 0
    var commission = 0
    var payment = 0
    var finalPayment = 0

    private lateinit var items: BuyResult

    var ChoiceList = ArrayList<shippingAgree>()
    private lateinit var shippingOptionDialog: BottomSheetDialog
    private lateinit var shippingAdapter: ShippingRvAdapter

    val priceFormat = DecimalFormat("#,###")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        productIdx = intent.getIntExtra("productBuyIdx", 0)
        Log.d("productid","$productIdx")
        getBuyData(productIdx)

        shippingDialog()

        binding.tvBuyChargeTaxWon.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
    }

    private fun getBuyData(productIdx: Int) {
        BuyService(this).buyGetData(productIdx)
    }

    private fun shippingDialog() {
        ChoiceList.add(shippingAgree("문앞", false))
        ChoiceList.add(shippingAgree("직접 받고 부재 시 문앞", false))
        ChoiceList.add(shippingAgree("경비실", false))
        ChoiceList.add(shippingAgree("우편함", false))
        ChoiceList.add(shippingAgree("직접입력", false))

        val shippingView = layoutInflater.inflate(R.layout.dialog_product_shipping, null)
        shippingOptionDialog = BottomSheetDialog(this)
        shippingOptionDialog.setContentView(shippingView)

        shippingAdapter = ShippingRvAdapter(this, ChoiceList)

        shippingAdapter.setShippingItem(ChoiceList)

        val shippingRv = shippingView.findViewById<RecyclerView>(R.id.rv_buy_shipping_option)
        shippingRv.adapter = shippingAdapter

        binding.clProductRequest.setOnClickListener {
            shippingOptionDialog.show()
        }

        shippingAdapter.clickListener(object : ShippingRvAdapter.OnChoiceClickListener {
            override fun onClick(view: View, position: Int) {
                binding.tvBuyShippingOptions.text = shippingAdapter.itemList[position].text
                for (i in 0 until ChoiceList.size) {
                    shippingAdapter.itemList[i].isCheck = false
                }
                shippingAdapter.itemList[position].isCheck = true
                shippingAdapter.notifyDataSetChanged()
                shippingOptionDialog.dismiss()
            }

        })
    }


    override fun onGetBuyDataSuccess(response: BuyResponse) {
        items = response.result

        Log.d("commission","$items")

        Glide.with(this)
            .load(items.productImgURL)
            .into(binding.ivBuyProduct)

        binding.tvBuyPrice.text = priceFormat.format(items.productPrice).toString() + "원"
        binding.tvProductPrice.text = priceFormat.format(items.productPrice).toString() + "원"
        binding.tvBuyProductName.text = items.productName
        binding.tvDeliveryChargePrice.text = items.shippingFee
        binding.tvBuyNowPoint.text = items.point.toString()
        binding.tvBuyPaymentPrice.text = items.productPrice.toString()

        // 수수료
        allCommission = (items.productPrice * 0.035).toInt()
        binding.tvBuyChargeTaxWon.text = allCommission.toString()

        // 3500원 할인 수수료
        commission = if (3500 > allCommission) 0 else (allCommission) - 3500
        binding.tvBuyTaxPrice.text = commission.toString()

        // 수수료 더한 결제금액
        payment = (items.productPrice) + commission
        binding.tvBuyPaymentPrice.text = payment.toString()

        // 마지막 결과값
        finalPayment = payment - point
        binding.tvBuyFinalPricePrice.text = finalPayment.toString()

        binding.etProductPoint.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.etProductPoint.text.toString() =="") {
                    point = 0
                    binding.tvBuyLightningPointPrice.text = "0원"
                } else {
                    point = binding.etProductPoint.text.toString().toInt()
                    binding.tvBuyLightningPointPrice.text = "-" + priceFormat.format(point) + "원"
                    finalPayment = payment - point
                    binding.tvBuyFinalPricePrice.text = finalPayment.toString()
                }
            }

        })


    }

    override fun onGetBuyDataFail(message: String) {
    }
}