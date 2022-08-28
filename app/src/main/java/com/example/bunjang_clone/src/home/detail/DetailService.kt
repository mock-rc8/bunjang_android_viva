package com.example.bunjang_clone.src.home.detail

import android.util.Log
import com.example.bunjang_clone.config.ApplicationClass
import com.example.bunjang_clone.src.home.detail.models.DetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailService(val detailActivityInterface : DetailActivityInterface) {

    fun detailGetData(productIdx: Int) {
        val detailRetrofitInterface = ApplicationClass.sRetrofit.create(DetailRetrofitInterface::class.java)
        detailRetrofitInterface.getDetail(productIdx).enqueue(object : Callback<DetailResponse> {
                override fun onResponse(call: Call<DetailResponse>, response: Response<DetailResponse>) {
                    detailActivityInterface.onGetDetailDataSuccess(response.body() as DetailResponse)
                }
                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    detailActivityInterface.onGetDetailDataFail(t.message ?: "통신 오류")
                }
            })
    }
}