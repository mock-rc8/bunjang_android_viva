package com.example.bunjang_clone.src.home.detail.buy

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bunjang_clone.R
import com.example.bunjang_clone.config.BaseActivity
import com.example.bunjang_clone.databinding.ActivityDeliveryBuyBinding
import com.example.bunjang_clone.src.MainActivity
import com.example.bunjang_clone.src.home.detail.buy.address.AddressActivityInterface
import com.example.bunjang_clone.src.home.detail.buy.address.AddressFragment
import com.example.bunjang_clone.src.home.detail.buy.address.AddressService
import com.example.bunjang_clone.src.home.detail.buy.address.models.AddressData
import com.example.bunjang_clone.src.home.detail.buy.address.models.AddressDialogRvAdapter
import com.example.bunjang_clone.src.home.detail.buy.address.models.AddressResponse
import com.example.bunjang_clone.src.home.detail.buy.address.models.GetAddressData
import com.example.bunjang_clone.src.home.detail.buy.models.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.DecimalFormat


class DeliveryBuyActivity() :
    BaseActivity<ActivityDeliveryBuyBinding>(ActivityDeliveryBuyBinding::inflate),
    BuyActivityInterface {

    var productIdx = 0
    var point = 0

    var allCommission = 0
    var commission = 0
    var payment = 0
    var finalPayment = 0

    // 배송지 요청사항
    var shippi = false

    // 개인정보 동의
    var agree = false

    var myuser = 0

    var paymentStatus = false

    lateinit var addressView: View
    lateinit var addressDialog: BottomSheetDialog

    private lateinit var items: BuyResult

    var ChoiceList = ArrayList<shippingAgree>()
    private lateinit var shippingOptionDialog: BottomSheetDialog
    private lateinit var shippingAdapter: ShippingRvAdapter

    private lateinit var addressDialogAdapter: AddressDialogRvAdapter

    var dataList = ArrayList<AddressData>()

    val priceFormat = DecimalFormat("#,###")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getUserData()

        productIdx = intent.getIntExtra("productBuyIdx", 0)
        Log.d("productid", "$productIdx")
        getBuyData(productIdx)

        shippingDialog()

        bottomAddAddress()

        binding.tvBuyChargeTaxWon.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG


        binding.ivBuySimplePayment.setOnClickListener {
            paymentStatus = false
            binding.ivBuySimplePayment.setImageResource(R.drawable.icon_radio_click)

            binding.ivBuyOtherPayment.setImageResource(R.drawable.icon_radio_unclick)
        }

        binding.ivBuyOtherPayment.setOnClickListener {
            paymentStatus = true
            binding.ivBuySimplePayment.setImageResource(R.drawable.icon_radio_unclick)

            binding.ivBuyOtherPayment.setImageResource(R.drawable.icon_radio_click)
        }


        binding.ivBuyAgreeBtn.setOnClickListener {
            if (agree) {
                binding.ivBuyAgreeBtn.setImageResource(R.drawable.icon_all_unagree)
                agree = false
            } else {
                binding.ivBuyAgreeBtn.setImageResource(R.drawable.icon_all_agree)
                agree = true
            }

        }

        binding.btnProductBuy.setOnClickListener {

            var transaction = "택배결제"
            var require = binding.tvBuyShippingOptions.text.toString()
            var point = binding.etBuyProductPoint.text.toString()
            var paymentMe = if (!paymentStatus) "번개장터 간편결제" else "다른 결제수단"

            Log.d("btnProductBuy",
                "$myuser, $productIdx, $transaction,$point, $require, $finalPayment, $paymentMe, $commission")
            postPaymentProduct(PaymentData(userIdx = myuser,
                productIdx = productIdx,
                transactionMethod = transaction,
                requirement = require,
                commissionPrice = commission,
                usePoint = point.toInt(),
                finalPrice = finalPayment,
                paymentMethod = paymentMe))
        }


    }

    fun bottomAddAddress() {
        addressView = layoutInflater.inflate(R.layout.dialog_address, null)
        addressDialog = BottomSheetDialog(this)
        addressDialog.setContentView(addressView)

        addressDialogAdapter = AddressDialogRvAdapter(this, dataList)

        addressDialogAdapter.setAddressItem(dataList)

        binding.clProductWhere.setOnClickListener {
            val addressDialogFragment = AddressFragment()
            addressDialogFragment.show(supportFragmentManager, addressDialogFragment.tag)
        }

        addressDialogAdapter.clickListener(object : AddressDialogRvAdapter.OnItemClickListener {
            override fun onClick(view: View, position: Int) {
                binding.tvAddressRegister.visibility = View.VISIBLE
                binding.clBuyBasic.visibility = View.GONE
                binding.tvBuyerName.visibility = View.GONE
                binding.tvBuyerPhoneNumber.visibility = View.GONE
                binding.tvBuyerMainAddress.visibility = View.GONE
                binding.tvBuyerSubAddress.visibility = View.GONE

                binding.tvBuyerName.text = addressDialogAdapter.itemList[position].name
                binding.tvBuyerMainAddress.text = addressDialogAdapter.itemList[position].address
                binding.tvBuyerSubAddress.text = addressDialogAdapter.itemList[position].addressDetail
                binding.tvBuyerPhoneNumber.text = addressDialogAdapter.itemList[position].phoneNumber

                addressDialogAdapter.notifyDataSetChanged()
                addressDialog.dismiss()

            }

        })
    }


    fun postPaymentProduct(paymentData: PaymentData) {
        BuyService(this).postPayment(paymentData)
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
                shippi = true
            }

        })
    }

    fun getUserData() {
        BuyService(this).getUserIdx()
    }


    override fun onGetBuyDataSuccess(response: BuyResponse) {
        items = response.result

        Log.d("commission", "$items")

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

        binding.etBuyProductPoint.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.etBuyProductPoint.text.toString() == "") {
                    point = 0
                    binding.tvBuyLightningPointPrice.text = "0원"
                } else {
                    point = binding.etBuyProductPoint.text.toString().toInt()
                    binding.tvBuyLightningPointPrice.text = "-" + priceFormat.format(point) + "원"
                    finalPayment = payment - point
                    binding.tvBuyFinalPricePrice.text = finalPayment.toString()
                }
            }

        })

    }

    override fun onGetBuyDataFail(message: String) {
    }

    override fun onPaymentSuccess(response: PaymentResponse) {
        if (response.isSuccess) {
            var intent = Intent(this, MainActivity::class.java)
            Toast.makeText(this, "결제 성공", Toast.LENGTH_SHORT).show()
            startActivity(intent)
            finish()
        }
    }

    override fun onPaymentFail(message: String) {

    }

    override fun onUserDataSuccess(response: UserIdx) {
        myuser = response.result.userIdx
    }

    override fun onUserDataFail(message: String) {
    }
}