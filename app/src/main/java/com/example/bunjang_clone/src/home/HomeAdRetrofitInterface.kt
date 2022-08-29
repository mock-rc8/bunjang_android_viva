package com.example.bunjang_clone.src.home

import com.example.bunjang_clone.src.home.models.HomeAdResponse
import com.example.bunjang_clone.src.home.models.RecommendResponse
import com.example.bunjang_clone.src.home.models.ShopNameResponse
import retrofit2.Call
import retrofit2.http.GET

interface HomeAdRetrofitInterface {
    @GET("/bunjang/products/ad-image")
    fun getHomeAd() : Call<HomeAdResponse>

}