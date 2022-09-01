package com.example.bunjang_clone.src.home.detail.buy

import com.example.bunjang_clone.config.ApplicationClass
import com.example.bunjang_clone.src.home.detail.buy.models.BuyResponse
import com.example.bunjang_clone.src.home.detail.buy.models.PaymentData
import com.example.bunjang_clone.src.home.detail.buy.models.PaymentResponse
import com.example.bunjang_clone.src.home.detail.buy.models.UserIdx
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuyService(val buyActivityInterface : BuyActivityInterface) {

    fun buyGetData(productIdx: Int) {
        val buyRetrofitInterface = ApplicationClass.sRetrofit.create(BuyRetrofitInterface::class.java)
        buyRetrofitInterface.getBuy(productIdx).enqueue(object : Callback<BuyResponse> {
            override fun onResponse(call: Call<BuyResponse>, response: Response<BuyResponse>) {
                buyActivityInterface.onGetBuyDataSuccess(response.body() as BuyResponse)
            }
            override fun onFailure(call: Call<BuyResponse>, t: Throwable) {
                buyActivityInterface.onGetBuyDataFail(t.message ?: "통신오류")
            }

        })
    }
    fun postPayment(paymentData: PaymentData) {
        val paymentRetrofitInterface = ApplicationClass.sRetrofit.create(BuyRetrofitInterface::class.java)
        paymentRetrofitInterface.getPayment(paymentData).enqueue(object : Callback<PaymentResponse>{
            override fun onResponse(call: Call<PaymentResponse>, response: Response<PaymentResponse>, ) {
                buyActivityInterface.onPaymentSuccess(response.body() as PaymentResponse)
            }
            override fun onFailure(call: Call<PaymentResponse>, t: Throwable) {
                buyActivityInterface.onPaymentFail(t.message ?: "통신오류")
            }

        })
    }
    fun getUserIdx(){
        val userRetrofitInterface = ApplicationClass.sRetrofit.create(BuyRetrofitInterface::class.java)
        userRetrofitInterface.getUseIdx().enqueue(object : Callback<UserIdx>{
            override fun onResponse(call: Call<UserIdx>, response: Response<UserIdx>) {
                buyActivityInterface.onUserDataSuccess(response.body() as UserIdx)
            }

            override fun onFailure(call: Call<UserIdx>, t: Throwable) {
                buyActivityInterface.onUserDataFail(t.message ?: "통신오류")
            }

        })
    }
}