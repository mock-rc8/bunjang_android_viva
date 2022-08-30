package com.example.bunjang_clone.src.splash.models

data class SplashResponse(
    var isSuccess : Boolean,
    var code : Int,
    var message : String,
    var result : UserIdData
)
data class UserIdData(
    var userIdx : Int,
    var status : String
)
