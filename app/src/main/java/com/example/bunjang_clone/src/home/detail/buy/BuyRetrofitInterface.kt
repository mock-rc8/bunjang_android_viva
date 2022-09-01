package com.example.bunjang_clone.src.home.detail.buy

import com.example.bunjang_clone.src.home.detail.buy.models.BuyResponse
import com.example.bunjang_clone.src.home.detail.buy.models.PaymentData
import com.example.bunjang_clone.src.home.detail.buy.models.PaymentResponse
import com.example.bunjang_clone.src.home.detail.buy.models.UserIdx
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BuyRetrofitInterface {
    @GET("/bunjang/products/payments/productIdx/{productIdx}")
    fun getBuy(@Path ("productIdx") productIdx: Int) : Call<BuyResponse>

    @POST("/bunjang/products/payments")
    fun getPayment(@Body paymentData: PaymentData) : Call<PaymentResponse>

    @GET("/bunjang/stores/store-id")
    fun getUseIdx() : Call<UserIdx>
}