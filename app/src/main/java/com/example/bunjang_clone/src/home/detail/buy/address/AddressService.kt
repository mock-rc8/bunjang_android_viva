package com.example.bunjang_clone.src.home.detail.buy.address

import com.example.bunjang_clone.config.ApplicationClass
import com.example.bunjang_clone.src.home.detail.buy.address.models.AddAddressData
import com.example.bunjang_clone.src.home.detail.buy.address.models.AddressResponse
import com.example.bunjang_clone.src.home.detail.buy.models.BuyResponse
import com.example.bunjang_clone.src.home.detail.buy.models.PaymentData
import com.example.bunjang_clone.src.home.detail.buy.models.PaymentResponse
import com.example.bunjang_clone.src.home.detail.buy.models.UserIdx
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddressService(val addressActivityInterface : AddressActivityInterface) {

    fun postAddressData(addAddressData: AddAddressData) {
        val addressRetrofitInterface = ApplicationClass.sRetrofit.create(AddressRetrofitInterface::class.java)
        addressRetrofitInterface.postAddress(addAddressData).enqueue(object : Callback<AddressResponse> {
            override fun onResponse(call: Call<AddressResponse>, response: Response<AddressResponse>, ) {
                addressActivityInterface.onPostAddressSuccess(response.body() as AddressResponse)
            }

            override fun onFailure(call: Call<AddressResponse>, t: Throwable) {
                addressActivityInterface.onPostAddressFail(t.message ?: "통신오류")
            }

        })
    }

}