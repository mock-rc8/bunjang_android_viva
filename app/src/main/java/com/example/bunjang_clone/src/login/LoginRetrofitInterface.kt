package com.example.bunjang_clone.src.login

import com.example.bunjang_clone.src.login.models.LoginData
import com.example.bunjang_clone.src.login.models.UserData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRetrofitInterface {
    @POST("/bunjang/users/log-in")
    fun sigUp( @Body userData: UserData) : Call<LoginData>
}