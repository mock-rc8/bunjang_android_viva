package com.example.bunjang_clone.src.home.detail.buy

import android.util.Log
import com.example.bunjang_clone.config.ApplicationClass
import com.example.bunjang_clone.src.home.detail.buy.models.BuyResponse
import com.example.bunjang_clone.src.home.detail.models.DetailResponse
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
}