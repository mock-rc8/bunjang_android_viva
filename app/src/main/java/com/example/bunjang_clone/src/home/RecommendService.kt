package com.example.bunjang_clone.src.home

import com.example.bunjang_clone.config.ApplicationClass
import com.example.bunjang_clone.src.home.models.RecommendResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecommendService(val recommendActivityInterface : RecommendActivityInterface) {

    fun pdGetData(){
        val recommendRetrofitInterface = ApplicationClass.sRetrofit.create(RecommendRetrofitInterface::class.java)
        recommendRetrofitInterface.getRecommendPd().enqueue(object : Callback<RecommendResponse> {
            override fun onResponse(call: Call<RecommendResponse>, response: Response<RecommendResponse>) {
                recommendActivityInterface.onGetDataSuccess(response.body() as RecommendResponse)
            }

            override fun onFailure(call: Call<RecommendResponse>, t: Throwable) {
                recommendActivityInterface.onGetDataFail(t.message ?: "통신 오류")
            }
        })
    }
 }