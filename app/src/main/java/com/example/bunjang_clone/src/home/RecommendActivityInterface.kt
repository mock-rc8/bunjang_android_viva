package com.example.bunjang_clone.src.home

import com.example.bunjang_clone.src.login.models.LoginResponse
import com.example.bunjang_clone.src.login.models.SignUpResponse

interface RecommendActivityInterface {

    fun onSingUpSuccess(response : SignUpResponse)

    fun onSingUpFail(message : String)

    fun onLoginSuccess(response : LoginResponse)

    fun onLoginFail(message : String)
}