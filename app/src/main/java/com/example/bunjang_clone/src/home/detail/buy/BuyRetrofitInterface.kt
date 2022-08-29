package com.example.bunjang_clone.src.home.detail.buy

import com.example.bunjang_clone.src.home.detail.buy.models.BuyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface BuyRetrofitInterface {
    @GET("/bunjang/products/payments/productIdx/{productIdx}")
    fun getBuy(@Path ("productIdx") productIdx: Int) : Call<BuyResponse>
}