package com.example.bunjang_clone.src.home

import com.example.bunjang_clone.config.ApplicationClass
import com.example.bunjang_clone.src.home.models.RecommendResponse
import com.example.bunjang_clone.src.home.models.ShopNameResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopNameService(val shopNameActivityInterface: ShopNameActivityInterface) {

    fun shopNameData() {
        val shopNameRetrofitInterface = ApplicationClass.sRetrofit.create(ShopNameRetrofitInterface::class.java)
        shopNameRetrofitInterface.getShopName().enqueue(object : Callback<ShopNameResponse> {
            override fun onResponse(call: Call<ShopNameResponse>, response: Response<ShopNameResponse>) {
                shopNameActivityInterface.onShopNameSuccess(response.body() as ShopNameResponse)
            }
            override fun onFailure(call: Call<ShopNameResponse>, t: Throwable) {
                shopNameActivityInterface.onShopNameFail(t.message ?: "통신 오류")
            }
        })

    }
}