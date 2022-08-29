package com.example.bunjang_clone.src.home

import com.example.bunjang_clone.src.home.models.RecommendResponse
import com.example.bunjang_clone.src.home.models.ShopNameResponse
import retrofit2.Call
import retrofit2.http.GET

interface ShopNameRetrofitInterface {
    @GET("/bunjang/users/store-names")
    fun getShopName() : Call<ShopNameResponse>

}