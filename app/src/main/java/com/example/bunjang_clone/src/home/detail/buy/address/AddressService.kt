package com.example.bunjang_clone.src.home.detail.buy.address

import com.example.bunjang_clone.config.ApplicationClass
import com.example.bunjang_clone.src.home.detail.buy.address.models.*
import com.example.bunjang_clone.src.home.detail.buy.models.BuyResponse
import com.example.bunjang_clone.src.home.detail.buy.models.PaymentData
import com.example.bunjang_clone.src.home.detail.buy.models.PaymentResponse
import com.example.bunjang_clone.src.home.detail.buy.models.UserIdx
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

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
    fun getAddressData() {
        val getAddressRetrofitInterface = ApplicationClass.sRetrofit.create(AddressRetrofitInterface::class.java)
        getAddressRetrofitInterface.getAddress().enqueue(object : Callback<GetAddressData>{
            override fun onResponse(call: Call<GetAddressData>, response: Response<GetAddressData>, ) {
                addressActivityInterface.onGetAddressSuccess(response.body() as GetAddressData)
            }

            override fun onFailure(call: Call<GetAddressData>, t: Throwable) {
                addressActivityInterface.onGetAddressFail(t.message ?: "통신오류")
            }

        })
    }
    fun patchDeleteData(deleteAddressData: DeleteAddressData) {
        val deleteAddressRetrofitInterface = ApplicationClass.sRetrofit.create(AddressRetrofitInterface::class.java)
        deleteAddressRetrofitInterface.patchAddress(deleteAddressData).enqueue(object : Callback<DeleteResponse>{
            override fun onResponse(call: Call<DeleteResponse>, response: Response<DeleteResponse>, ) {
                addressActivityInterface.onPatchDeleteAddressSuccess(response.body() as DeleteResponse)
            }

            override fun onFailure(call: Call<DeleteResponse>, t: Throwable) {
                addressActivityInterface.onPatchDeleteAddressFail(t.message ?: "통신오류")
            }

        })

    }
}