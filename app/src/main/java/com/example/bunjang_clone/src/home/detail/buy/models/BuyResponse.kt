package com.example.bunjang_clone.src.home.detail.buy.models


data class BuyResponse(
    var isSuccess : Boolean,
    var code : Int,
    var message : String,
    var result : BuyResult
)
data class BuyResult(
    var productImgURL : String,
    var productPrice : Int,
    var productName : String,
    var shippingFee : String,
    var point : Int,
    var commission : Int
)
