package com.example.bunjang_clone.src.login

import com.example.bunjang_clone.config.ApplicationClass
import com.example.bunjang_clone.src.login.models.LoginData
import com.example.bunjang_clone.src.login.models.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val loginActivityInterface : LoginActivityInterface) {

    fun loginSignUp(name : String, birthDate : String, carrier : String, phoneNum : String, password : String, storeName : String){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.sigUp(UserData(name, birthDate, carrier, phoneNum, password, storeName)).enqueue(object : Callback<LoginData> {
            override fun onResponse(call: Call<LoginData>, response: Response<LoginData>) {
                loginActivityInterface.onSingUpSuccess(response.body() as LoginData)

            }

            override fun onFailure(call: Call<LoginData>, t: Throwable) {
                loginActivityInterface.onSingUpFail(t.message ?: "통신 오류")
            }

        })
    }
 }