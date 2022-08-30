package com.example.bunjang_clone.src.splash

import com.example.bunjang_clone.config.ApplicationClass
import com.example.bunjang_clone.src.login.models.LoginData
import com.example.bunjang_clone.src.login.models.LoginResponse
import com.example.bunjang_clone.src.login.models.SignUpResponse
import com.example.bunjang_clone.src.login.models.SignUpData
import com.example.bunjang_clone.src.splash.models.SplashResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashService(val splashActivityInterface : SplashActivityInterface) {

    fun getAutoLogin(){
        val splashRetrofitInterface = ApplicationClass.sRetrofit.create(SplashRetrofitInterface::class.java)
        splashRetrofitInterface.autoLogin().enqueue(object : Callback<SplashResponse> {
            override fun onResponse(call: Call<SplashResponse>, response: Response<SplashResponse>) {
                splashActivityInterface.onAutoLoginSuccess(response.body() as SplashResponse)
            }
            override fun onFailure(call: Call<SplashResponse>, t: Throwable) {
                splashActivityInterface.onAutoLoginFail(t.message ?: "통신오류")
            }

        })
    }

 }