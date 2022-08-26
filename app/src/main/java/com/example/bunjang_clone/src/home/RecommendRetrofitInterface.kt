package com.example.bunjang_clone.src.home

import com.example.bunjang_clone.src.home.models.RecommendResponse
import retrofit2.Call
import retrofit2.http.GET

interface RecommendRetrofitInterface {
    @GET("/bunjang/products")
    fun getRecommendPd() : Call<RecommendResponse>

}