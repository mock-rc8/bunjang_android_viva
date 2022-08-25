package com.example.bunjang_clone.src.login

import com.example.bunjang_clone.src.login.models.LoginResponse
import com.example.bunjang_clone.src.login.models.SignUpResponse

interface LoginActivityInterface {

    fun onSingUpSuccess(response : SignUpResponse)

    fun onSingUpFail(message : String)

    fun onLoginSuccess(response : LoginResponse)

    fun onLoginFail(message : String)
}