package com.example.bunjang_clone.src.home.detail

import com.example.bunjang_clone.src.home.detail.models.DetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailRetrofitInterface {
    @GET("/bunjang/products/detail/productIdx/{productIdx}")
    fun getDetail(@Path ("productIdx") productIdx: Int) : Call<DetailResponse>
}