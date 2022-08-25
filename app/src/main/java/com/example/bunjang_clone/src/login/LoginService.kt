package com.example.bunjang_clone.src.login

import com.example.bunjang_clone.config.ApplicationClass
import com.example.bunjang_clone.src.login.models.LoginData
import com.example.bunjang_clone.src.login.models.LoginResponse
import com.example.bunjang_clone.src.login.models.SignUpResponse
import com.example.bunjang_clone.src.login.models.SignUpData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val loginActivityInterface : LoginActivityInterface) {

    fun SignUp(name : String, residentNumLast : String, residentNumFirst : String, carrier : String, phoneNum : String, password : String, storeName : String){
        val signUpRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        signUpRetrofitInterface.signUp(SignUpData(name, residentNumLast, residentNumFirst, carrier, phoneNum, password, storeName)).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                loginActivityInterface.onSingUpSuccess(response.body() as SignUpResponse)
            }
            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                loginActivityInterface.onSingUpFail(t.message ?: "통신 오류")
            }
        })
    }

    fun Login(name : String, residentNumLast : String, residentNumFirst : String, carrier : String, phoneNum : String, password : String){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.loinIn(LoginData(name, residentNumLast, residentNumFirst, carrier, phoneNum, password)).enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                loginActivityInterface.onLoginSuccess(response.body() as LoginResponse)
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginActivityInterface.onLoginFail(t.message ?: "통신 오류")
            }

        })

    }
 }