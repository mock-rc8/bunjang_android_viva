package com.example.bunjang_clone.src.splash

import com.example.bunjang_clone.src.splash.models.SplashResponse

interface SplashActivityInterface {

    fun onAutoLoginSuccess(response : SplashResponse)

    fun onAutoLoginFail(message : String)
}