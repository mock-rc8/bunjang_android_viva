package com.example.bunjang_clone.src.register

import com.example.bunjang_clone.src.login.models.LoginData
import com.example.bunjang_clone.src.login.models.LoginResponse
import com.example.bunjang_clone.src.login.models.SignUpResponse
import com.example.bunjang_clone.src.login.models.SignUpData
import com.example.bunjang_clone.src.register.models.RegisterData
import com.example.bunjang_clone.src.register.models.RegisterResponse
import com.example.bunjang_clone.src.splash.models.SplashResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RegisterRetrofitInterface {
    @POST("/bunjang/products/new")
    fun upRegister(@Body registerData: RegisterData) : Call<RegisterResponse>
}