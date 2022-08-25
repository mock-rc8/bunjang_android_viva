package com.example.bunjang_clone.src.login.models

data class SignUpResponse(
    var isSuccess : Boolean,
    var code : Int,
    var message : String,
    var result : SignResult
)

data class SignResult(
    var id : Int,
    var name : String,
    var storeName : String,
    var jwt : String
)