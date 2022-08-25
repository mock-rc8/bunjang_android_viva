package com.example.bunjang_clone.src.login

import com.example.bunjang_clone.src.login.models.LoginData
import com.example.bunjang_clone.src.login.models.LoginResponse
import com.example.bunjang_clone.src.login.models.SignUpResponse
import com.example.bunjang_clone.src.login.models.SignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRetrofitInterface {
    @POST("/bunjang/users")
    fun signUp( @Body signUpData: SignUpData) : Call<SignUpResponse>

    @POST("/bunjang/users/log-in")
    fun loinIn(@Body loginData: LoginData) : Call<LoginResponse>
}