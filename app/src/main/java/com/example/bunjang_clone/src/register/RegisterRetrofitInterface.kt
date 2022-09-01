package com.example.bunjang_clone.src.register

import com.example.bunjang_clone.src.login.models.LoginData
import com.example.bunjang_clone.src.login.models.LoginResponse
import com.example.bunjang_clone.src.login.models.SignUpResponse
import com.example.bunjang_clone.src.login.models.SignUpData
import com.example.bunjang_clone.src.register.models.CategoryData
import com.example.bunjang_clone.src.register.models.RegisterData
import com.example.bunjang_clone.src.register.models.RegisterResponse
import com.example.bunjang_clone.src.splash.models.SplashResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RegisterRetrofitInterface {
    @POST("/bunjang/products/new")
    fun upRegister(@Body registerData: RegisterData) : Call<RegisterResponse>

    @GET("/bunjang/category/main-category")
    fun getMainCategory() : Call<CategoryData>

    @GET("/bunjang/category/main-category/{mainCategoryIdx}")
    fun getSubCategory(@Path("mainCategoryIdx") mainCategoryIdx: Int)  : Call<CategoryData>
}