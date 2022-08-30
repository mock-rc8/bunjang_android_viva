package com.example.bunjang_clone.src.register

import com.example.bunjang_clone.src.register.models.RegisterResponse

interface RegisterActivityInterface {

    fun onRegisterSuccess(response : RegisterResponse)

    fun onRegisterFail(message : String)
}