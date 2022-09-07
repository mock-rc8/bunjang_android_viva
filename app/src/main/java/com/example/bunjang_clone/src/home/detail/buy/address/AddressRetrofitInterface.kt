package com.example.bunjang_clone.src.home.detail.buy.address

import com.example.bunjang_clone.src.home.detail.buy.address.models.*
import com.example.bunjang_clone.src.home.detail.buy.models.BuyResponse
import com.example.bunjang_clone.src.home.detail.buy.models.PaymentData
import com.example.bunjang_clone.src.home.detail.buy.models.PaymentResponse
import com.example.bunjang_clone.src.home.detail.buy.models.UserIdx
import retrofit2.Call
import retrofit2.http.*

interface AddressRetrofitInterface {
    @GET("/bunjang/users/shipping")
    fun getAddress() : Call<GetAddressData>

    @POST("/bunjang/users/shipping")
    fun postAddress(@Body addAddressData: AddAddressData) : Call<AddressResponse>

    @PATCH("/bunjang/users/shipping/d")
    fun patchAddress(@Body deleteAddressData : DeleteAddressData) : Call<DeleteResponse>

}