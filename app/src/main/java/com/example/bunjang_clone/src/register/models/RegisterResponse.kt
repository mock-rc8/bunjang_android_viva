package com.example.bunjang_clone.src.register.models

data class RegisterResponse(
    var isSuccess : Boolean,
    var code : Int,
    var message : String,
    var result : RegisterResult
)
data class RegisterResult(
    var userIdx : Int,
    var userName : String,
    var storeName : String
)
