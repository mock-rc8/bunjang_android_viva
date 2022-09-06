package com.example.bunjang_clone.src.home.detail.buy.address

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import com.example.bunjang_clone.config.BaseActivity
import com.example.bunjang_clone.databinding.ActivityAddAddressBinding
import com.example.bunjang_clone.src.home.detail.buy.address.models.AddAddressData
import com.example.bunjang_clone.src.home.detail.buy.address.models.AddressData
import com.example.bunjang_clone.src.home.detail.buy.address.models.AddressResponse
import com.example.bunjang_clone.src.login.models.LoginData

class AddAddressActivity() : BaseActivity<ActivityAddAddressBinding>(ActivityAddAddressBinding::inflate), AddressActivityInterface {

    private lateinit var addressAdapter : AddressRvAdapter

    var dataList = ArrayList<AddressData>()

    var name = ""
    var phoneNumber = ""
    var address = ""
    var addressDetail = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addAddress()

        addressRv()

        putAddress()

        passAddress()


    }

    fun addAddress() {
        binding.tvAddAddressTab.setOnClickListener {
            binding.clAddAddressEmpty.visibility = View.GONE
            binding.rvAddressList.visibility = View.GONE
            binding.llAddAddress.visibility = View.VISIBLE
        }
    }
    fun addressRv() {
        addressAdapter = AddressRvAdapter(this, dataList)
        binding.rvAddressList.adapter = addressAdapter

        rvEmpty()
    }

    fun rvEmpty() {
        if(addressAdapter.itemCount == 0) {
            binding.rvAddressList.visibility = View.GONE
            binding.clAddAddressEmpty.visibility = View.VISIBLE
        } else {
            binding.rvAddressList.visibility = View.VISIBLE
            binding.clAddAddressEmpty.visibility = View.GONE
        }
    }

    fun putAddress() {
        binding.etAddressSubAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btnAddressNext.isClickable = binding.etAddressSubAddress.length() >= 1
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })
    }
    fun passAddress() {
        binding.btnAddressNext.setOnClickListener {
            name = binding.etAddressName.text.toString()
            phoneNumber = binding.etAddressPhoneNumber.text.toString()
            address = binding.etAddressMainAddress.text.toString()
            addressDetail = binding.etAddressSubAddress.text.toString()

            val postRequest = AddAddressData(receiverName = name, receiverPhoneNum = phoneNumber, address = address, detailAddress = addressDetail)
            AddressService(this).postAddressData(postRequest)
        }
    }

    override fun onPostAddressSuccess(response: AddressResponse) {
        if (response.isSuccess) {
            val intent = Intent(this, AddAddressActivity::class.java)
            startActivity(intent)
            finish()
            Log.d("responseCode", "${response.code}")
        }
    }

    override fun onPostAddressFail(message: String) {
        TODO("Not yet implemented")
    }
}