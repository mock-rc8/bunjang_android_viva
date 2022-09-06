package com.example.bunjang_clone.src.home.detail.buy.address

import com.example.bunjang_clone.src.home.detail.buy.address.models.AddAddressData
import com.example.bunjang_clone.src.home.detail.buy.address.models.AddressResponse
import com.example.bunjang_clone.src.home.detail.buy.models.BuyResponse
import com.example.bunjang_clone.src.home.detail.buy.models.PaymentData
import com.example.bunjang_clone.src.home.detail.buy.models.PaymentResponse
import com.example.bunjang_clone.src.home.detail.buy.models.UserIdx
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AddressRetrofitInterface {
//    @GET("/bunjang/products/payments/productIdx/{productIdx}")
//    fun getBuy(@Path ("productIdx") productIdx: Int) : Call<BuyResponse>

    @POST("/bunjang/users/shipping")
    fun postAddress(@Body addAddressData: AddAddressData) : Call<AddressResponse>

//    @GET("/bunjang/stores/store-id")
//    fun getUseIdx() : Call<UserIdx>
}