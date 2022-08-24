package com.example.bunjang_clone.src.login

import com.example.bunjang_clone.src.login.models.LoginData

interface LoginActivityInterface {

    fun onSingUpSuccess(response : LoginData)

    fun onSingUpFail(message : String)
}