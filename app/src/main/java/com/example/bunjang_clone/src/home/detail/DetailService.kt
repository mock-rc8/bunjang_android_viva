package com.example.bunjang_clone.src.home.detail

import com.example.bunjang_clone.config.ApplicationClass
import com.example.bunjang_clone.src.home.detail.models.DetailData
import com.example.bunjang_clone.src.home.detail.models.DetailResponse
import com.example.bunjang_clone.src.home.models.RecommendResponse
import com.example.bunjang_clone.src.login.models.LoginData
import com.example.bunjang_clone.src.login.models.LoginResponse
import com.example.bunjang_clone.src.login.models.SignUpResponse
import com.example.bunjang_clone.src.login.models.SignUpData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailService(val detailActivityInterface : DetailActivityInterface) {

    fun detailGetData(productIdx: Int) {
        val detailRetrofitInterface = ApplicationClass.sRetrofit.create(DetailRetrofitInterface::class.java)
        detailRetrofitInterface.getDetail(DetailData(productIdx)).enqueue(object : Callback<DetailResponse> {
                override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {
                    detailActivityInterface.onGetDetailDataSuccess(response.body() as DetailResponse)
                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    detailActivityInterface.onGetDetailDataFail(t.message ?: "통신 오류")
                }
            })
    }
}