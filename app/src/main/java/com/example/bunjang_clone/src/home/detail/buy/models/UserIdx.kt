package com.example.bunjang_clone.src.home.detail.buy.models

data class UserIdx(
    var isSuccess : Boolean,
    var code : Int,
    var message : String,
    var result : Userdata
)
data class Userdata(
    var userIdx : Int
)
