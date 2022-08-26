package com.example.bunjang_clone.src.home

import com.example.bunjang_clone.src.login.models.LoginData
import com.example.bunjang_clone.src.login.models.LoginResponse
import com.example.bunjang_clone.src.login.models.SignUpResponse
import com.example.bunjang_clone.src.login.models.SignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RecommendRetrofitInterface {
    @POST("/bunjang/products")
    fun signUp( @Body signUpData: SignUpData) : Call<SignUpResponse>

    @POST("/bunjang/users/log-in")
    fun loinIn(@Body loginData: LoginData) : Call<LoginResponse>
}