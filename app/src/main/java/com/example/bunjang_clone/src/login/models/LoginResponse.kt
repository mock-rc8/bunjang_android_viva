package com.example.bunjang_clone.src.login.models

data class LoginResponse(
    var isSuccess : Boolean,
    var code : Int,
    var message : String,
    var result : LoginResult
)
data class LoginResult(
    var userIdx : Int,
    var status : String,
    var jwt : String
)
