package com.example.bunjang_clone.src.register

import com.example.bunjang_clone.config.ApplicationClass
import com.example.bunjang_clone.src.login.models.LoginData
import com.example.bunjang_clone.src.login.models.LoginResponse
import com.example.bunjang_clone.src.login.models.SignUpResponse
import com.example.bunjang_clone.src.login.models.SignUpData
import com.example.bunjang_clone.src.register.models.RegisterData
import com.example.bunjang_clone.src.register.models.RegisterResponse
import com.example.bunjang_clone.src.splash.models.SplashResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterService(val registerActivityInterface : RegisterActivityInterface) {

    fun tryRegisterData(registerData: RegisterData){
        val registerRetrofitInterface = ApplicationClass.sRetrofit.create(RegisterRetrofitInterface::class.java)
        registerRetrofitInterface.upRegister(registerData).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                registerActivityInterface.onRegisterSuccess(response.body() as RegisterResponse)
            }
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                registerActivityInterface.onRegisterFail(t.message ?: "통신오류")
            }

        })
    }

 }