package com.example.bunjang_clone.src.login.models

data class LoginData(
    var isSuccess : Boolean,
    var code : Int,
    var message : String,
    var result : Result
)

data class Result(
    var userIdx : Int,
    var status : String,
    var jwt : String
)