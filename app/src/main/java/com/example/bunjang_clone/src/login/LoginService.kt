package com.example.bunjang_clone.src.login

import android.content.Context
import com.example.bunjang_clone.config.ApplicationClass
import com.example.bunjang_clone.src.login.models.LoginData
import com.example.bunjang_clone.src.login.models.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class LoginService(val loginActivityInterface : LoginActivityInterface) {

    fun loginSignUp(userData : UserData){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.sigUp(userData).enqueue(object : Callback<LoginData> {
            override fun onResponse(call: Call<LoginData>, response: Response<LoginData>) {
                loginActivityInterface.onSingUpSuccess(response.body() as LoginData)

            }

            override fun onFailure(call: Call<LoginData>, t: Throwable) {
                loginActivityInterface.onSingUpFail(t.message ?: "통신 오류")
            }

        })
    }
}