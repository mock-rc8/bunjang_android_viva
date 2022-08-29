package com.example.bunjang_clone.src.home.models

data class ShopNameResponse(
    var isSuccess : Boolean,
    var code : Int,
    var message : String,
    var result : ShopResult
)
data class ShopResult(
    var storeName : String
)
