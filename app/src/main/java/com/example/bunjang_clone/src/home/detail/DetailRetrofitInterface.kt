package com.example.bunjang_clone.src.home.detail

import com.example.bunjang_clone.src.home.detail.models.DetailData
import com.example.bunjang_clone.src.home.detail.models.DetailResponse
import com.example.bunjang_clone.src.home.models.RecommendResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface DetailRetrofitInterface {
    @GET("/bunjang/products/detail")
    fun getDetail(@Body productIdx : DetailData) : Call<DetailResponse>

}