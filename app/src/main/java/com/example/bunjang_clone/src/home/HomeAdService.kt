package com.example.bunjang_clone.src.home

import com.example.bunjang_clone.config.ApplicationClass
import com.example.bunjang_clone.src.home.models.HomeAdResponse
import com.example.bunjang_clone.src.home.models.RecommendResponse
import com.example.bunjang_clone.src.home.models.ShopNameResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeAdService(val homeAdActivityInterface: HomeAdActivityInterface) {

    fun homAdData() {
        val homeAdRetrofitInterface = ApplicationClass.sRetrofit.create(HomeAdRetrofitInterface::class.java)
        homeAdRetrofitInterface.getHomeAd().enqueue(object : Callback<HomeAdResponse> {
            override fun onResponse(call: Call<HomeAdResponse>, response: Response<HomeAdResponse>) {
                homeAdActivityInterface.onHomAdSuccess(response.body() as HomeAdResponse)
            }

            override fun onFailure(call: Call<HomeAdResponse>, t: Throwable) {
                homeAdActivityInterface.onHomAdFail(t.message ?: "통신 오류")
            }
        })

    }
}